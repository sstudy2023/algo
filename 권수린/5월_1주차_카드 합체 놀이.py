n, m = map(int, input().split())

cards = list(map(int, input().split()))

for _ in range(m):
    cards.sort()
    new_num = cards[0] + cards[1]
    cards[0] = new_num
    cards[1] = new_num

print(sum(cards))