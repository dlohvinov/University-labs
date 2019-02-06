import math

values = open('values.txt', 'r')
readable_values = values.readlines()
width = 0
height = 0
amount = 0
for line in readable_values:
    values_array = line.split(';')
    width = int(values_array[0])
    height = int(values_array[1])
    amount = int(values_array[2])

paper_square = width * height * amount
min_side = math.ceil(math.sqrt(paper_square))

i = 1

while (True):
    side_w = width * i
    if side_w < min_side:
        i += 1
        continue
    else:
        width_amount = math.floor(side_w / width)
        height_amount = math.floor(side_w / height)
        if width_amount * height_amount < amount:
            i += 1
            continue
        else:
            break

i = 1
while (True):
    side_h = height * i
    if side_h < min_side:
        i += 1
        continue
    else:
        width_amount = math.floor(side_h / width)
        height_amount = math.floor(side_h / height)
        if width_amount * height_amount < amount:
            i += 1
            continue
        else:
            break

if side_w > side_h:
    side = side_h
else:
    side = side_w

print('result: ', side)
print(side_w, '  ', side_h)
