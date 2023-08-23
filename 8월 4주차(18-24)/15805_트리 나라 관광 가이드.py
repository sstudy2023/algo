N = int(input())
route = list(map(int, input().split()))
parent_cities = [-1] * (max(route) + 1)
stack = []

stack.append(route[0])

for i in range(1, N):
    if len(stack) < 2:
        stack.append(route[i])
    else:
        if route[i] == stack[-2]:
            parent_cities[stack.pop()] = route[i]
        else:
            stack.append(route[i])

print(len(parent_cities))
print(' '.join(list(map(str, parent_cities))))