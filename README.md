# EvolutionSimulator

## Description:
Hello! This is the product of my 20% evolution simulator for the first half of the year. The purpose was to create a program of living creatures that adapt to survive to their enviroment. Each of the Classes listed below assists this purpose.

### JDriver.java
The driver of my program. The driver creates the desired amount of Animals, and causes them to move, eat, and exist. The program must be run with the driver.

### Animal.java
The actual class where animals are given traits and what happens when they move. Also can determine if they have life.

### FoodSquares.java
Creates a square with a set amount of food and a color, which corresponds to food. Over time, the class will give food back to the Square and cause it to regenerate.

### Enviroment.java
The bedrock of the program with many different functions. First, Enviroment creates an enviroment of foodsquares, and corresponds them to physical rectangles that are related to the chosen size that the user decides on. Enviroment also controls the number of squares. Next, Enviroment holds the move function, as well as the eat function. On top of this, Enviroment also contains the MWP, or move with purpose function. It also contains unused functions on pregnancy and reproduction.

## In order to run this program:
You need a computer that can run .java files, preferably on intelliJ. To run the program, run the driver and the rest should work. If you do not understand, here is a listed instruction:
1. Download and/or open intelliJ.
2. Create a new project with any name and import the files in this repository into the src.
3. Open the JDriver.java class and run it.
4. When you are satisfied or there are no more organism's, you may quit the program.

### Notes for future collaborators
There are many things uncompleted with this program that can be easily done. Such things include:
⋅⋅* Disease, famine, events of change in the biome
⋅⋅* More complicated chain of energy flow, add more to the chain, decomposers, stationary animals
..* more advanced movement and intelligence in the creatures (possibly add neural networks for decision making)
⋅⋅* Random maps and biomes
For those who are interested, here is a great youtube video containing some of the things that you might want to see in your program.
[By Carykh](https://www.youtube.com/watch?v=jAQNiL3o5lU)
