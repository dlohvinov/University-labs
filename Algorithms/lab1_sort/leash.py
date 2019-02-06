import time

class leash():
    def __init__(self, manufacturer='null', max_lenth=0.0, max_weight=0.0, color='null'):
        self.manufacturer = manufacturer
        self.max_lenth = max_lenth
        self.max_weight = max_weight
        self.color = color


leashes = list()

values = open('values.txt', 'r')
readable_values = values.readlines()
i = 0
for line in readable_values:
    values_array = line.split(',')
    leashes.append(leash(values_array[0], int(values_array[1]), int(values_array[2]), values_array[3]))
    i += 1


def print_by_lenth(list):
    for leash in list:
        print(leash.max_lenth)
    print(' ----------- ')
def print_by_weight(list):
    for leash in list:
        print(leash.max_weight)
    print(' -----------')
def print_by_manufacturer(list):
    print('list of manufacturers: \n')
    for leash in list:
        print(leash.manufacturer)
    print(' -----------')


print_by_manufacturer(leashes)

def bubble_sort(list):
    bubble_time = time.time()
    counter_comparasion = 0
    counter_swap = 0
    print("Bubble sort by weight(high to low)")
    is_sorted = True
    while (is_sorted):
        is_sorted = False
        for i in range(list.__len__() - 1):
            if (list[i].max_weight < list[i + 1].max_weight):
                tmp = list[i].max_weight
                list[i].max_weight = list[i + 1].max_weight
                list[i + 1].max_weight = tmp
                is_sorted = True

                counter_swap+=1
            counter_comparasion+=1

    print("comparasions: ",counter_comparasion, '\n swaps: ', counter_swap)
    print("time of execution: ", (time.time() - bubble_time))


bubble_sort(leashes)
print_by_weight(leashes)


counter_comparasion = 0
counter_swap = 0
def merge_sort(main_list):

    global counter_comparasion
    global counter_swap


    def split(list):
        if (list.__len__() > 1):
            middle = int((list.__len__()) / 2)
            last = int((list.__len__()))
            left_part = []
            right_part = []
            for i in range(middle):
                left_part.append(list[i])
            for j in range(last - middle):
                right_part.append(list[middle + j])
            split(left_part)
            split(right_part)
            merge(left_part, right_part)

    def merge(left_part, right_part):
        left_iterator = 0
        right_iterator = 0
        main_iterator = 0
        while (left_iterator < left_part.__len__()) & (right_iterator < right_part.__len__()):
            if left_part[left_iterator].max_lenth < right_part[right_iterator].max_lenth:
                main_list[main_iterator] = left_part[left_iterator]
                left_iterator += 1

                # counter_swap +=1
            else:
                main_list[main_iterator] = right_part[right_iterator]
                right_iterator += 1

                # counter_swap +=1
            main_iterator += 1

            # counter_comparasion+=1

        while left_iterator < left_part.__len__():
                main_list[main_iterator] = left_part[left_iterator]
                left_iterator += 1
                main_iterator += 1

                # counter_swap +=1

        while right_iterator < right_part.__len__():
                main_list[main_iterator] = right_part[right_iterator]
                right_iterator += 1
                main_iterator += 1

                # counter_swap +=1
    split(main_list)

print("Merge sort by lenth(low to high)")
merge_time = time.time()
merge_sort(leashes)
print("comparasions: ",counter_comparasion, '\n swaps: ', counter_swap)
print("time of execution: ", (time.time() - merge_time))
print_by_lenth(leashes)


# start = time.time()
# a = range(100000)
# b = [i*2 for i in a]
# end = time.time()
# print(end - start)