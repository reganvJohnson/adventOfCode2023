# read input file and create list of input lines
file = open("dec1a.txt", "r")
input = file.read()
input_list = input.split('\n')

# part 1
sum = 0 # create our sum integer
for inp in input_list:
    nums = [] # craate empty list that we will use to track all integers in list
    for letter in inp: # loop through all letters in input line
        if ord(letter) <= 57: # if unicode value of character is <=57, we know it's an integer
            nums.append(letter) # append to our list of integer characters

    # concat the first and last characters of our list and add their integer representation to our sum
    #print (inp, len(nums))
    sum += int(nums[0] + nums[-1])
    #print (sum)

#print(sum) # print solution to part 1


# part 2
sum = 0 # create our sum integer
# create list of integers spelled-out in english
integer_names = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
for inp in input_list:
    nums = [] # craate empty list that we will use to track all integers in list
    for i, letter in enumerate(inp): # loop through all letters in input line
        for val, name in enumerate(integer_names): # loop through all spelled-out number options
            if name in inp[i:i+len(name)]: # if the current spelled-out number we are looking for is found starting at index i, add it to our nums list
                nums.append(str(val)) # append to our list of integer characters
        if ord(letter) <= 57: # if unicode value of character is <=57, we know it's an integer
            nums.append(letter) # append to our list of integer characters

    # concat the first and last characters of our list and add their integer representation to our sum
    print (int(nums[0] + nums[-1]), inp)
    sum += int(nums[0] + nums[-1])

print(sum) # print solution to part 2