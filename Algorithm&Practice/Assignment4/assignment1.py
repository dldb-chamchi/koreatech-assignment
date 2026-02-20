#실습과제 04 [문제 1]

def dfs(graph, visit, start, group):
    visit[start] = group
    for v in graph[start]:
        if visit[v] == 0:
            result = dfs(graph, visit, v, -group)
            if not result:
                return False
        elif visit[v] == group:
            return False
    return True

graph = {
    'A': ['B', 'D', 'F'],
    'B': ['A', 'C', 'E'],
    'C': ['B', 'F', 'D'],
    'D': ['C', 'E', 'G'],
    'E': ['B', 'D', 'H'],
    'F': ['A', 'C', 'G'],
    'G': ['D', 'F', 'H'],
    'H': ['C', 'E', 'G']
}

graph2 = {'A' : ['D', 'C', 'B'],
         'B' : ['A', 'C'],
         'C' : ['A', 'D', 'B'],
         'D' : ['A', 'C']}

graph3 = {
    'A': ['B', 'D'],
    'B': ['A', 'C'],
    'C': ['B', 'D'],
    'D': ['A', 'C']
}

graph4 = {
    'A': ['B', 'C'],
    'B': ['A', 'C', 'D'],
    'C': ['A', 'B', 'D'],
    'D': ['B', 'C']
}

visit = {node: 0 for node in graph4}

result = True
for node in graph4:
    if visit[node] == 0:
        result = dfs(graph4, visit, node, 1)
        if not result:
            break

def print_graph(graph4):
    for node, neighbors in graph4.items():
        print(f"{node} : {', '.join(neighbors)}")

print_graph(graph4)

if result:
    print("이분 그래프")
else:
    print("이분 그래프 아님")
