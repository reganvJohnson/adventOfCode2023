# this has a bug. it only finds thefirst occurrence of a word, not the last. That means
# if a line has 'eight...eight', the parser will only find the first one. 
# not bothering to fix it...
f = open("dec1a.txt", "r")

numbers = ['xxx', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
sum = 0
for line in f:
	first_pos = 999
	first = 0
	last_pos = -1
	last = 0
	i = -1
	for char in line:
		i += 1
		if char.isnumeric():
			if first == 0:
				first = char
				first_pos = i
			last = char
			last_pos = i
	i = -1	
	for number in numbers:
		i += 1
		pos = line.find(number)
		if pos >= 0:
			print("found " + str(i) + " at " + str(pos))
			if pos < first_pos:
				first_pos = pos
				first = i
			if pos > last_pos:
				last_pos = pos
				last = i
	
	value = int(first)*10 + int(last)
	print (value, line)
	sum += value
print ("finally ", sum)		
