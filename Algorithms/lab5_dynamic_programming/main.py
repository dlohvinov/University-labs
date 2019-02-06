import math

values = open('values.txt', 'r')
readable_values = values.readlines()
width = int(readable_values[0])
heights = list(map(int, readable_values[1].split()))


def calc_lenth(start, end):
    return math.sqrt(width ** 2 + (end - start) ** 2)


solutions = []
last_on_top = [calc_lenth(1, heights[1])]
last_on_bot = [calc_lenth(heights[0], 1)]
for i in range(2, len(heights)):
    top_to_top = last_on_top[last_on_top.__len__() - 1] + calc_lenth(heights[i - 1], heights[i])
    top_to_bot = last_on_top[last_on_top.__len__() - 1] + calc_lenth(heights[i - 1], 1)
    bot_to_top = last_on_bot[last_on_bot.__len__() - 1] + calc_lenth(1, heights[i])
    bot_to_bot = last_on_bot[last_on_bot.__len__() - 1] + calc_lenth(1, 1)

    last_on_bot.append(max(top_to_bot, bot_to_bot))
    last_on_top.append(max(bot_to_top, top_to_top))

    solutions.insert(i - 2, max(last_on_bot[last_on_bot.__len__() - 1], last_on_top[last_on_top.__len__() - 1]))

print(round(solutions.pop(), 2))
