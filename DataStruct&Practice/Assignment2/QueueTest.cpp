//Queue 테스트 파일

#include <string>
#include <sstream>
#include "Queue.h"
#include "Student.h"
#include "gtest/gtest.h"

std::ostream& operator<<(std::ostream& os, const Student& s) {
	os << '(' << s.name << ", " << s.number << ", " << s.year << ')';
	return os;
}


TEST(Queue, listEmptyInitializationTest)
{
	Queue<int> queue;
	ASSERT_EQ(queue.size(), 0);
	ASSERT_TRUE(queue.isEmpty());
}

//int타입 테스트
TEST(Queue, IntPushAndPop)
{
	Queue<int> queue;
	queue.push(3);
	ASSERT_EQ(queue.peek(), 3);
	queue.push(4);
	queue.push(5);
	queue.push(6);
	queue.push(7);
	queue.push(8);
	queue.push(9);
	ASSERT_EQ(queue.size(),7);
	std::string output = "";
	while(!queue.isEmpty())
		output += std::to_string(queue.pop())+",";
	ASSERT_EQ(output,"3,4,5,6,7,8,9,"); //수정완료
}

//객체 테스트
TEST(Queue, ObjectPushAndPop1)
{
	Queue<Student> queue(3);
	queue.push(Student("abc1", "202401", 1));
	queue.push(Student("abc2", "202301", 2));
	queue.push(Student("abc3", "202402", 1));
	queue.push(Student("abc4", "202402", 2));
	ASSERT_EQ(queue.size(),4);
	std::stringstream ss;
	while(!queue.isEmpty())
		ss << queue.pop().getName() << ',';
	ASSERT_EQ(ss.str(),"abc1,abc2,abc3,abc4,"); //수정완료
}

//객체 테스트 2
TEST(Queue, ObjectPushAndPop2)
{
	Queue<Student> queue(5);
	queue.emplace("xyz1", "202401", 1);
	queue.emplace("xyz2", "202301", 2);
	queue.emplace("xyz3", "202402", 3);
	queue.emplace("xyz4", "202403", 4);
	ASSERT_EQ(queue.size(),4);
	std::stringstream ss;
	while(!queue.isEmpty())
		ss << queue.pop().getNumber() << ',';
	ASSERT_EQ(ss.str(),"202401,202301,202402,202403,"); //수정완료
}

//int타입 queue peakTest
TEST(Queue, PeakTest)
{
	Queue<int> queue;
		queue.push(3);
		ASSERT_EQ(queue.peek(), 3);
		queue.push(4);
		queue.push(5);
		queue.push(6);
		ASSERT_EQ(queue.peek(), 3);
		queue.pop();
		ASSERT_EQ(queue.peek(), 4);
		queue.push(7);
		queue.push(8);
		queue.push(9);
		queue.pop();
		ASSERT_EQ(queue.peek(), 5);
}

//queue 초기화 테스트
TEST(Queue, InitializerTest)
{
	Queue<int> queue{9, 8, 7, 6, 5, 4, 3};
	ASSERT_EQ(queue.size(),7);
	std::string output = "";
	while(!queue.isEmpty())
		output += std::to_string(queue.pop())+",";
	ASSERT_EQ(output,"9,8,7,6,5,4,3,");
}


//반복자 테스트
TEST(Queue, testIterator)
{
	Queue<int> queue{1, 2, 3, 4, 5, 6};
	std::string output = "";
	for (auto it = queue.begin(); it != queue.end(); ++it) {
		output += std::to_string(*it)+",";
	    }
	ASSERT_EQ(output,"1,2,3,4,5,6,");

}


TEST(Queue, Big5)
{
	Queue<int> queue1{1, 2, 3, 4, 5};
	Queue<int> queue2{6, 7, 8, 9, 10};
	Queue<int> queue3;
	Queue<int> queue4(queue2);
	// copy constructor test
	std::string output1 = "";
	for(auto n: queue2) output1 += std::to_string(n)+",";
	std::string output2 = "";
	for(auto n: queue4) output2 += std::to_string(n)+",";
	ASSERT_EQ(output1, output2);
	queue3 = queue1;
	// copy assignment test
	output1 = "";
	for(auto n: queue1) output1 += std::to_string(n)+",";
	output2 = "";
	for(auto n: queue3) output2 += std::to_string(n)+",";
	ASSERT_EQ(output1, output2);
	// move constructor test
	Queue<int> queue5(std::move(queue3));
	output1 = "";
	for(auto n: queue5) output1 += std::to_string(n)+",";
	output2 = "";
	for(auto n: queue1) output2 += std::to_string(n)+",";
	ASSERT_EQ(output1, output2);
	// move assignment test
	queue3 = std::move(queue4);
	output1 = "";
	for(auto n: queue3) output1 += std::to_string(n)+",";
	output2 = "";
	for(auto n: queue2) output2 += std::to_string(n)+",";
	ASSERT_EQ(output1, output2);

	using namespace std::string_literals;

	Queue<std::string> squeue1{"apple"s, "banana"s, "grape"s, "lemon"s, "kiwi"s};
	Queue<std::string> squeue2{"mango"s, "melon"s, "peach"s, "rasberry"s, "strawberry"s};
	Queue<std::string> squeue3;
	Queue<std::string> squeue4(squeue2);
	output1 = "";
	for(auto n: squeue2) output1 += n+",";
	output2 = "";
	for(auto n: squeue4) output2 += n+",";
	ASSERT_EQ(output1, output2);
	squeue3 = squeue1;
	// copy assignment test
	output1 = "";
	for(auto n: squeue1) output1 += n+",";
	output2 = "";
	for(auto n: squeue3) output2 += n+",";
	ASSERT_EQ(output1, output2);
	// move constructor test
	Queue<std::string> squeue5(std::move(squeue3));
	output1 = "";
	for(auto n: squeue5) output1 += n+",";
	output2 = "";
	for(auto n: squeue1) output2 += n+",";
	ASSERT_EQ(output1, output2);
	// move assignment test
	squeue3 = std::move(squeue4);
	output1 = "";
	for(auto n: squeue3) output1 += n+",";
	output2 = "";
	for(auto n: squeue2) output2 += n+",";
	ASSERT_EQ(output1, output2);

}


