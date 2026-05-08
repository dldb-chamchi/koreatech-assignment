//정렬 정수 리스트 구현

#ifndef SORTEDARRAYLIST_H_
#define SORTEDARRAYLIST_H_
#include <cstddef>
#include <algorithm>
#include <stdexcept>
#include <initializer_list>
#include "DynamicArrayIterator.h"

class SortedArrayList {
private:
	// 검사 목적으로 용량을 작게 설정
	size_t capacity;
	int* items;
	size_t numItems{0};

	void shiftLeft(size_t startIdx) {
		for(size_t i{startIdx}; i<numItems - 1; ++i)
			items[i] = items[i + 1];
		--numItems;
	}

	void shiftRight(size_t startIdx) {
		for(size_t i{numItems}; i > startIdx; --i)
			items[i] = items[i - 1];
		++numItems;
	}

	// 이진 탐색
	// @return 있으면 해당 요소가 있는 색인, 없으면 해당 요소를 삽입할 위치
	size_t search(int item) const noexcept {
		int lo{0}, mid;
		int hi = numItems-1;
		int result{-1};

		while(lo <= hi){
			mid = lo + (hi - lo) / 2;
			if(items[mid] == item){
				result = mid;
			    hi = mid - 1; //return 안하고 맨 첫번째 인덱스를 찾는 중
			}
			else if(item > items[mid]) lo = mid + 1;
			else hi = mid - 1;
		}

		if(result == -1) return lo; //삽입 위치
		return result; //가장 첫번째 인덱스 반환
	}

	void increaseCapacity() {
		capacity *= 2;
		int* tmp{new int[capacity]};
		std::copy(items, items + numItems, tmp);
		delete [] items;
		items = tmp;
	}

public:
	explicit SortedArrayList(size_t capacity = 5):
		capacity{capacity != 0? capacity: 5},
		items{new int[capacity]} {}

	explicit SortedArrayList(const std::initializer_list<int>& initList):
		SortedArrayList(initList.size()) {
		for(auto n: initList) add(n);
	}

	virtual ~SortedArrayList() {
		delete [] items;
	}

	SortedArrayList(const SortedArrayList& other):
		SortedArrayList(other.capacity) {
		numItems = other.numItems;
		std::copy(other.items, other.items + numItems, items);
	}

	SortedArrayList(SortedArrayList&& tmp):
		capacity{tmp.capacity}, numItems{tmp.numItems} {
		items = tmp.items;
		tmp.items = nullptr;
	}

	const SortedArrayList& operator=(const SortedArrayList& other) {
		if(this != &other) {
			SortedArrayList tmp(other);
			swap(tmp);
		}
		return *this;
	}

	const SortedArrayList& operator=(SortedArrayList&& tmp) {
		swap(tmp);
		return *this;
	}

	void swap(SortedArrayList& other) {
		std::swap(capacity, other.capacity);
		std::swap(numItems, other.numItems);
		std::swap(items, other.items);
	}

	bool isEmpty() const noexcept {
		return numItems == 0;
	}

	bool isFull() const noexcept {
		return false;
	}

	size_t size() const noexcept {
		return numItems;
	}

	void clear() noexcept {
		numItems = 0;
	}

	int operator[](size_t index) const {
		if(index < numItems) return items[index];
		else throw std::out_of_range("Index ERROR: [] const");
	}

	int popBack() {
		if(isEmpty()) throw std::logic_error("popBack: empty state");
		--numItems;
		return items[numItems];
	}

	int popFront() {
		if(isEmpty()) throw std::logic_error("popFront: empty state");
		int ret{items[0]};
		shiftLeft(0);
		return ret;
	}

	void add(int item) {
	    if (numItems == capacity) increaseCapacity();
	    size_t insertIdx = search(item); //삽입 인덱스
	    shiftRight(insertIdx); //++numItems;
	    items[insertIdx] = item; //삽입
	}

	int peekFront() const {
		if(isEmpty()) throw std::logic_error("peekFront: empty state");
		return items[0];
	}

	int peekBack() const {
		if(isEmpty()) throw std::logic_error("peekBack: empty state");
		return items[numItems - 1];
	}

	bool find(int item) const noexcept { //확인완료
		int idx = search(item);
		if(items[idx] != item) return false; //search가 반환한 인덱스에 찾으려는 값이 있는지 확인(삽입 인덱스도 주기 때문)
		return true;
	}

	void removeFirst(int item) noexcept {
		int idx = search(item);
		shiftLeft(idx); //--numItems;
	}

	void removeAll(int item) {
		if(find(item)){
			int firstIdx = search(item);
			int lastIdx = search(item+1);
			int j{firstIdx};
			for(auto i{lastIdx}; i<numItems; ++i){
				items[j] = items[i];
				++j;
			}
			numItems -= lastIdx-firstIdx;
		}//지울 아이템이 존재하는지 파악
		else throw std::logic_error("remove items no exist");

		//1 1 1 3 3 4 4 7 9 9
		//1 1
		//first = 3
		//last = 5
		//numItems = 10
		//5부터 9까지

	}

	auto cbegin() const { return DynamicArrayIterator<const int>(items); }
	auto cend() const { return DynamicArrayIterator<const int>(items + numItems); }
	auto begin() const { return cbegin(); }
	auto end() const { return cend(); }
};

#endif /* SORTEDARRAYLIST_H_ */
