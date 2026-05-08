//QueueIterator 파일

#ifndef QUEUEITERATOR_H_
#define QUEUEITERATOR_H_
#include <iterator>

// 반복자를 template을 이용하여 정의한 이유:
// - 일반 반복자와 상수 반복자를 위한 클래스를 별도 정의하지 않고 하나만 정의하기 위함
template <typename U>
class QueueIterator {
public:
	using iterator_category = std::forward_iterator_tag;
	using value_type = U;
	using difference_type = std::ptrdiff_t;
	using pointer = U*;
	using reference = U&;
	explicit QueueIterator(pointer p): p{p} {}
	const QueueIterator& operator++() noexcept { ++p; return *this; } //수정완료
	QueueIterator operator++(int) noexcept { auto retval{*this}; ++p; return retval; } //수정완료
	bool operator==(const QueueIterator& other) const noexcept { return p == other.p; }
	bool operator!=(const QueueIterator& other) const noexcept { return p != other.p; }
	reference operator*() noexcept { return *p; }
	reference operator*() const noexcept { return *p; }
private:
	pointer p;
};

#endif /* QueueITERATOR_H_ */
