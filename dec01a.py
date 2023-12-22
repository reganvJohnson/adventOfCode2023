f = open("dec1a.txt", "r")
sum = 0
for line in f:
	first = 0
	last = 0
	for char in line:
		if char.isnumeric():
			if first == 0:
				first = char
			last = char
	value = int(first)*10 + int(last)
	print (value)
	sum += value
print (sum)		
