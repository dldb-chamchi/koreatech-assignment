#include "fraction.h"

// 분자와 분모를 받는 생성자
Fraction::Fraction(int n, int d) {
    if(d == 0) throw std::invalid_argument("분모 = 0");
    if((n >= 0 && d < 0) || (n < 0 && d < 0)) {
        this->n = -n;
        this->d = -d;
    } else {
        this->n = n;
        this->d = d;
    }
}

// 분자 반환
int Fraction::numerator() const noexcept { return n; }

// 분모 반환
int Fraction::denominator() const noexcept { return d; }

// std::string 타입 변환 연산자
Fraction::operator std::string() const noexcept {
    if(n % d == 0) return std::to_string(n / d);
    else return std::to_string(n) + "/" + std::to_string(d);
}

// double 타입 변환 연산자
Fraction::operator double() const noexcept {
    return static_cast<double>(n) / d;
}

// 마이너스 연산자
Fraction Fraction::operator-() const {
    return Fraction(-n, d);
}

// 덧셈 연산자
Fraction Fraction::operator+(const Fraction& other) const {
    int new_n = n * other.d + other.n * d;
    int new_d = d * other.d;
    Fraction result(new_n, new_d);
    result.reduction();
    return result;
}

// 뺄셈 연산자
Fraction Fraction::operator-(const Fraction& other) const {
    return operator+(Fraction(-other.n, other.d));
}

// 곱셈 연산자
Fraction Fraction::operator*(const Fraction& other) const {
    int new_n = n * other.n;
    int new_d = d * other.d;
    Fraction result(new_n, new_d);
    result.reduction();
    return result;
}

// 나눗셈 연산자
Fraction Fraction::operator/(const Fraction& other) const {
    return operator*(Fraction(other.d, other.n));
}

// <=> 연산자
auto Fraction::operator<=>(const Fraction& other) const {
    return (n * other.d) <=> (other.n * d);
}

// == 비교 연산자
bool Fraction::operator==(const Fraction& other) const {
    return operator<=>(other) == 0;
}

// 최대공약수 계산
int Fraction::gcd(int a, int b) const noexcept {
    return (b == 0) ? a : gcd(b, a % b);
}

// 분수 약분
void Fraction::reduction() noexcept {
    int g = gcd(std::abs(n), d);
    n /= g;
    d /= g;
}

// 출력 연산자
std::ostream& operator<<(std::ostream& os, const Fraction& o) {
    os << static_cast<std::string>(o);
    return os;
}

// 입력 연산자
std::istream& operator>>(std::istream& is, Fraction& f) {
    std::string input;
    is >> input;
    
    size_t pos = input.find('/');
    if (pos != std::string::npos) {
        int num = std::stoi(input.substr(0, pos));
        int denom = std::stoi(input.substr(pos + 1));
        f = Fraction(num, denom);
    } else {
        int num = std::stoi(input);
        f = Fraction(num);
    }
    
    return is;
}
