#실습과제 06

import math
import random
import time
from typing import List, Tuple

Point = Tuple[int, int]


def distance(p1: Point, p2: Point) -> float:
    # sqrt((dx)^2 + (dy)^2)
    dx = p1[0] - p2[0]
    dy = p1[1] - p2[1]
    return math.sqrt(dx * dx + dy * dy)


# -------------------------
# 1) O(n^2) 완전탐색
# -------------------------
def bruteforce(P: List[Point]) -> float:
    n = len(P)
    mindist = float("inf")
    for i in range(n - 1):
        for j in range(i + 1, n):
            d = distance(P[i], P[j])
            if d < mindist:
                mindist = d
    return mindist


# -------------------------
# 2) O(n log^2 n)
#   - 분할정복 + strip에서 매번 y정렬
# -------------------------
def nlogn2(Px: List[Point]) -> float:
    # Px: x로 정렬된 리스트를 받는다고 가정
    def brute_small(points: List[Point]) -> float:
        return bruteforce(points)

    def strip_closest(strip: List[Point], d: float) -> float:
        # 매번 y정렬 => log n 비용이 재귀마다 발생 => O(n log^2 n)
        strip.sort(key=lambda p: p[1])
        best = d
        m = len(strip)
        for i in range(m):
            j = i + 1
            while j < m and (strip[j][1] - strip[i][1]) < best:
                best = min(best, distance(strip[i], strip[j]))
                j += 1
        return best

    def rec(points_x: List[Point]) -> float:
        n = len(points_x)
        if n <= 3:
            return brute_small(points_x)

        mid = n // 2
        mid_x = points_x[mid][0]

        d_left = rec(points_x[:mid])
        d_right = rec(points_x[mid:])
        d = min(d_left, d_right)

        strip = [p for p in points_x if abs(p[0] - mid_x) < d]
        d_strip = strip_closest(strip, d)

        return min(d, d_strip)

    return rec(Px)


# -------------------------
# 3) O(n log n)
#   - Px( x정렬 ) + Py( y정렬 )를 함께 유지
# -------------------------
def nlogn(Px: List[Point]) -> float:
    Py = sorted(Px, key=lambda p: p[1])  # y 정렬

    def brute_small(points: List[Point]) -> float:
        return bruteforce(points)

    def strip_closest(strip_y: List[Point], d: float) -> float:
        # strip_y는 이미 y정렬 상태
        best = d
        m = len(strip_y)
        for i in range(m):
            # 이론상 최대 7개 정도만 보면 충분하지만,
            # 조건 (y차이 < best)로 끊어도 O(n) 유지됨
            j = i + 1
            while j < m and (strip_y[j][1] - strip_y[i][1]) < best:
                best = min(best, distance(strip_y[i], strip_y[j]))
                j += 1
        return best

    def rec(points_x: List[Point], points_y: List[Point]) -> float:
        n = len(points_x)
        if n <= 3:
            return brute_small(points_x)

        mid = n // 2
        mid_x = points_x[mid][0]

        left_x = points_x[:mid]
        right_x = points_x[mid:]

        # y정렬 리스트를 좌/우로 분할 (O(n))
        left_y = []
        right_y = []
        for p in points_y:
            if p[0] < mid_x or (p[0] == mid_x and p in left_x):
                left_y.append(p)
            else:
                right_y.append(p)

        d_left = rec(left_x, left_y)
        d_right = rec(right_x, right_y)
        d = min(d_left, d_right)

        # strip도 points_y를 이용해서 자연스럽게 y정렬 유지
        strip_y = [p for p in points_y if abs(p[0] - mid_x) < d]
        d_strip = strip_closest(strip_y, d)

        return min(d, d_strip)

    return rec(Px, Py)


if __name__ == "__main__":
    # 점 개수
    n = 10
    data: List[Point] = [(random.randint(1, 10000), random.randint(1, 10000)) for _ in range(n)]

    # 1) 완전탐색
    start = time.perf_counter()
    bf_dist = bruteforce(data)
    t_bf = time.perf_counter() - start
    print("완전탐색 :", bf_dist)
    print(f"완전탐색 시간 : {t_bf:.6f}초")

    # 2) nlogn^2
    data_x = sorted(data, key=lambda p: p[0])
    start = time.perf_counter()
    nlogn2_dist = nlogn2(data_x)
    t_nlogn2 = time.perf_counter() - start
    print("nlogn^2 :", nlogn2_dist)
    print(f"nlogn² 시간 : {t_nlogn2:.6f}초")

    # 3) nlogn
    data_x = sorted(data, key=lambda p: p[0])
    start = time.perf_counter()
    nlogn_dist = nlogn(data_x)
    t_nlogn = time.perf_counter() - start
    print("nlogn :", nlogn_dist)
    print(f"nlogn 시간 : {t_nlogn:.6f}초")