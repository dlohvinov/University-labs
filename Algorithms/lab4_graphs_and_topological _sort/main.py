import math


class Vertex:
    def __init__(self, vertex):
        self.vertex = vertex
        self.connection = []
        self.salary = 0


class Graph:
    def __init__(self):
        self.verticles = []

    def add_edge(self, vertex1, vertex2):

        counter = 0
        for i in self.verticles:
            if i.vertex == vertex1.vertex:
                vertex1 = self.verticles.pop(counter)
                break
            counter += 1

        counter = 0
        for i in self.verticles:
            if i.vertex == vertex2.vertex:
                vertex2 = self.verticles.pop(counter)
                break
            counter += 1

        vertex1.connection.append(vertex2)
        self.verticles.append(vertex1)
        self.verticles.append(vertex2)

    def get_verticles(self):
        return self.verticles

    def add_verticle(self, new_verticle):
        self.verticles.append(new_verticle)

    def topological_sort(self):
        visited = {vertex: False for vertex in self.verticles}
        for i in visited:
            if not visited[i]:
                self.dfs(i, visited)
                visited[i] = True

    def dfs(self, current, visited):
        for i in current.connection:
            if not visited.get(i):
                self.dfs(i, visited)
                visited[i] = True

        if not current.connection:
            current.salary = 1
        else:
            for i in current.connection:
                current.salary += i.salary


def is_square(val):
    if (math.sqrt(val))**2 == (math.floor(math.sqrt(val)))**2:
        return True
    else:
        return False

graph = Graph()

values = open('values.txt', 'r')
readable_values = values.readlines()
for line in readable_values:
    if not is_square(len(line)):
        print("wrong input")
        break
    else:
        worker_len = int(math.sqrt(len(line)))
        workers = [line[i:i + worker_len] for i in range(0, len(line), worker_len)]
        for worker in workers:
            graph.add_verticle(Vertex(worker))

        for worker in workers:
            counter = 0
            for char in worker:
                if char == "Y":
                    graph.add_edge(Vertex(worker), Vertex(workers[counter]))

                counter += 1

    graph.topological_sort()

    summ = 0
    for verticle in graph.get_verticles():
        summ += verticle.salary
    print(summ)
