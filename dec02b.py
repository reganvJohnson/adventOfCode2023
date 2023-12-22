f = open("dec02.txt", "r")
#f = open("dec02_demo.txt", "r")




def calculate_power(rounds):
	powers = {"red":0, "green":0, "blue":0}

	draws = game_label[1].split(";")
	for draw in draws:
		colours = draw.split(",")
		for colour in colours:
			detail = colour.strip().split(" ")
			number = int(detail[0].strip())
			this_colour = detail[1].strip()
			if number > powers[this_colour]:
				powers[this_colour] = number
	return powers["red"] * powers['green'] * powers["blue"]


def valid_game(rounds): 
	draws = game_label[1].split(";")
	for draw in draws:
		colours = draw.split(",")
		for colour in colours:
			detail = colour.strip().split(" ")
			if int(detail[0].strip()) > maxes[detail[1].strip()]:
				return False
	return True


maxes = {"red":12, "green":13, "blue":14}
red_limit = 12
green_limit = 13
blue_limit = 14
sum  = 0

for line in f:
	game_label = line.split(":")
	games = game_label[0].split()
	game = games[1]

	sum += calculate_power(game.strip())

print (sum)
 