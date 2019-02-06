
class Vertex:
    def __init__(self, vertex):
        self.vertex = vertex
        self.connection = []


class Graph:
    def __init__(self):
        self.verticles = []

    def add_connection(self, vertex1, vertex2):

        counter = 0
        for i in self.verticles:
            if i.vertex == vertex1.vertex:
                vertex1 = self.verticles.pop(counter)
            counter+=1

        counter = 0
        for i in self.verticles:
            if i.vertex == vertex2.vertex:
                vertex2 = self.verticles.pop(counter)
            counter+=1

        vertex1.connection.append(vertex2)
        vertex2.connection.append(vertex1)
        self.verticles.append(vertex1)
        self.verticles.append(vertex2)

    def get_verticles(self):
        return self.verticles

    def get_man_list(self):
        list = []
        for vertex in self.verticles:
            if int(vertex.vertex) % 2 == 1:
                list.append(vertex)
        return list

    def get_woman_list(self):
        list = []
        for vertex in self.verticles:
            if int(vertex.vertex) % 2 == 0:
                list.append(vertex)
        return list

    def is_connected(self, start_v, search_v):
        visited = {vertex: False for vertex in self.verticles}
        queue = []
        queue.append(start_v)
        visited[start_v] = True

        while queue:
            current = queue.pop(0)

            if (current.vertex == search_v.vertex):
                # print("Yay")
                return True
            else:
                for i in current.connection:
                    if visited.get(i) == False:
                        queue.append(i)
                        visited[i] = True

        # print("Nope :c")
        return False


graph = Graph()

values = open('values.txt', 'r')
readable_values = values.readlines()
for line in readable_values:
    line = line.strip('\n')
    values_array = line.split(',')
    graph.add_connection(Vertex(values_array[0]), Vertex(values_array[1]))


# graph.add_connection(Vertex("1"), Vertex("2"))
# graph.add_connection(Vertex("2"), Vertex("4"))
# graph.add_connection(Vertex("3"), Vertex("5"))
# graph.add_connection(Vertex("5"), Vertex("6"))
for i in graph.get_verticles():
    graph.is_connected()
# wedd_couples = set()
# for vertex1 in graph.get_man_list():
#     for vertex2 in graph.get_woman_list():
#         if graph.is_connected(vertex1, vertex2):
#             continue
#         else:
#             # print(vertex1.vertex, '/', vertex2.vertex)
#             str = vertex1.vertex + "/" + vertex2.vertex
#             wedd_couples.add(str)
# print(wedd_couples.__len__())
# print(wedd_couples)
