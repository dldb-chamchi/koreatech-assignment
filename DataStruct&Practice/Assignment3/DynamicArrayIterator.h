//정렬 정수 리스트 이터레이터 구현


#ifndef DYNAMICARRAYITERATOR_H_
#define DYNAMICARRAYITERATOR_H_
#include <cstddef>
#include <iterator>

template <typename U>
class DynamicArrayIterator {
public:
	using iterator_category = std::forward_iterator_tag;
	using value_type = U;
	using difference_type = std::ptrdiff_t;
	using pointer = U*;
	using reference = U&;
    explicit DynamicArrayIterator(pointer p): p{p} {}
    const DynamicArrayIterator& operator++() noexcept { ++p; return *this; }
    DynamicArrayIterator operator++(int) noexcept { auto retval{*this}; ++p; return retval; }
    bool operator==(const DynamicArrayIterator& other) const noexcept { return p == other.p; }
    bool operator!=(const DynamicArrayIterator& other) const noexcept { return p != other.p; }
    reference operator*() noexcept { return *p; }
    reference operator*() const noexcept { return *p; }
private:
    pointer p;
};

#endif
