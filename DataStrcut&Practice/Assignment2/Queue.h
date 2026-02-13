//Queue 헤더파일

#ifndef QUEUE_H_
#define QUEUE_H_
#include <iostream>
#include <cstddef>
#include <algorithm>
#include <utility>
#include <stdexcept>
#include <initializer_list>
#include "QueueIterator.h"

template <typename T>
class Queue {
	size_t capacity;
	size_t top{0};
	T* items{nullptr};

	void increaseCapacity() {
		capacity *= 2;
		T* tmp{new T[capacity]{}};
		std::copy(items, items + top, tmp);
		delete [] items;
		items = tmp;
	}

public:
	Queue(size_t capacity = 10):
		capacity{capacity == 0? 10: capacity},
		items{new T[this->capacity]} {}

	Queue(const std::initializer_list<T>& initList):
		capacity{initList.size()}, top{capacity}, items{new T[capacity]} {
		std::copy(initList.begin(), initList.end(), items);
	}

	virtual ~Queue() {
		delete [] items;
	}

	Queue(const Queue& other):
		capacity{other.capacity}, top{other.top}, items{new T[capacity]} {
		std::copy(other.items, other.items + capacity, items);
	}

	Queue(Queue&& tmp) noexcept: Queue(0) {
		swap(tmp);
	}

	const Queue& operator=(const Queue& other) {
		if(this != &other) {
			Queue tmp(other);
			swap(tmp);
		}
		return *this;
	}

	const Queue& operator=(Queue&& tmp) noexcept {
		swap(tmp);
		return *this;
	}

	void swap(Queue& other) noexcept {
		std::swap(capacity, other.capacity);
		std::swap(top, other.top);
		std::swap(items, other.items);
	}

	bool isEmpty() const noexcept {
		return top == 0;
	}

	size_t size() const noexcept {
		return top;
	}

	void clear() noexcept {
		top = 0;
	}

	//수정완료
	const T& peek() const {
		if(isEmpty()) throw std::out_of_range("Index out of range");
		return items[0];
	}

	template <typename... Ts>
	void emplace(Ts&&... args) {
		if(top == capacity) increaseCapacity();
		new (&items[top++]) T(std::forward<Ts>(args)...);
	}

	void push(const T& item) {
		if(top == capacity) increaseCapacity();
		items[top++] = item;
	}

	void push(T&& item) {
		if(top == capacity) increaseCapacity();
		items[top++] = std::move(item);
	}

	//수정완료
	T pop() {
		if(isEmpty()) throw std::out_of_range("Index out of range");
		T ret{std::move(items[0])};
		for(size_t i{0}; i<top-1; ++i){
			items[i] = items[i+1];
		}
		--top;
		return ret;
	}

	//auto begin() { return StackIterator<T>(items + top -1); } 배열의 마지막 인덱스를 가르킴
	//auto end() { return StackIterator<T>(items - 1); } 배열의 0번째 인덱스보다 앞을 가르킴(종료 조건)

	//수정완료
	auto begin() { return QueueIterator<T>(items); } //배열의 0번쨰 인덱스
	auto end() { return QueueIterator<T>(items + top); } //배열의 마지막 인덱스 + 1 (종료 조건)
	auto cbegin() const { return QueueIterator<const T>(items); }
	auto cend() const { return QueueIterator<const T>(items + top); }
	auto begin() const { return cbegin(); }
	auto end() const { return cend(); }
};


#endif /* INTEGERSTACK_H_ */
