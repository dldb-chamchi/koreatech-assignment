//Student 파일


#ifndef STUDENT_H_
#define STUDENT_H_
#include <iostream>
#include <string>

class Student {
private:
	std::string name;
	std::string number;
	int year{1};
public:
	Student() = default;
	Student(std::string_view name, std::string_view number, int year):
		name(name), number(number), year{year} {}
	virtual ~Student() = default;
	Student(const Student& other) = default;
	Student(Student&& tmp) = default;
	Student& operator=(const Student& other) = default;
	Student& operator=(Student&& tmp) = default;

	std::string_view getName() const {
		return name;
	}

	std::string_view getNumber() const {
		return number;
	}

	int getYear() const {
		return year;
	}

	friend std::ostream& operator<<(std::ostream& os, const Student& s);
};

#endif /* STUDENT_H_ */
