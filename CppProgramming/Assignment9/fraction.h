#ifndef FRACTION_H
#define FRACTION_H

#include <iostream>
#include <stdexcept>
#include <string>
#include <cmath>

class Fraction {
private:
    int n{0}; // 분자
    int d{1}; // 분모

public:
    // 기본 생성자
    explicit Fraction() = default;
    
    // 분자와 분모를 받는 생성자
    explicit Fraction(int n, int d = 1);
    
    // 소멸자
    virtual ~Fraction() = default;
    
    // 분자 반환
    int numerator() const noexcept;
    
    // 분모 반환
    int denominator() const noexcept;
    
    // std::string 타입 변환 연산자
    explicit operator std::string() const noexcept;
    
    // double 타입 변환 연산자
    explicit operator double() const noexcept;

    // 단항 마이너스 연산자
    Fraction operator-() const;
    
    // 덧셈 연산자
    Fraction operator+(const Fraction& other) const;
    
    // 뺄셈 연산자
    Fraction operator-(const Fraction& other) const;
    
    // 곱셈 연산자
    Fraction operator*(const Fraction& other) const;
    
    // 나눗셈 연산자
    Fraction operator/(const Fraction& other) const;
    
    // <=> 연산자
    auto operator<=>(const Fraction& other) const;
    
    // == 연산자
    bool operator==(const Fraction& other) const;
    
private:
    // 최대공약수 계산
    int gcd(int a, int b) const noexcept;
    
    // 분수 약분
    void reduction() noexcept;
    
    // 입출력 연산자 친구 함수 선언
    friend std::ostream& operator<<(std::ostream&, const Fraction&);
    friend std::istream& operator>>(std::istream&, Fraction&);
};

// 출력 연산자
std::ostream& operator<<(std::ostream& os, const Fraction& o);

// 입력 연산자
std::istream& operator>>(std::istream& is, Fraction& f);

#endif // FRACTION_H
