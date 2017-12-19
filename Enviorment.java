import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Josh Hascall
 * 9/18/17
 * The place where the squares are created and assigned to a foodsquare. Where animals learn how to move and eat. Sets up and initializes animals and points. Also has move with purpose (MWP)
 */
import java.applet.*;
public class Enviorment extends Applet {
    public static final int WIDTH =  800; // must be a square
    public static final int HEIGHT = 800;
    private static final int NUM_FOODSQUARES_PER_ROW = 10; //because its a square, will be the square of this for total
    private static final double PROBABILITY = .2; //probility that the animal will MWP if hungry.

    private ArrayList<Animal> animals;
    private ArrayList<Point> animalPoints;
    private ArrayList<Color> colors;

    private ArrayList<FoodSquare> foodSquares;
    private ArrayList<Rectangle> foodRects;

    private int squareWidth;
    private int squareHeight;


    public ArrayList<Rectangle> getFoodRects() {
        return foodRects;
    }

    public Enviorment(int numAnimal){
        Random r = new Random();
        animals = new ArrayList<>();
        animalPoints = new ArrayList<>();
        colors = new ArrayList<>();
        foodRects = new ArrayList<>();
        foodSquares = new ArrayList<>();



        this.squareHeight = getHEIGHT()/NUM_FOODSQUARES_PER_ROW;
        this.squareWidth = getWIDTH()/NUM_FOODSQUARES_PER_ROW;

        for (int i = 0; i < numAnimal; i++){

            animals.add(new Animal());
            animalPoints.add(new Point(r.nextInt(WIDTH)+1, r.nextInt(HEIGHT)+1 ));

        }
        setSquares();


    }

    public void moveAnimals() {
        Random r = new Random();


        for (int p = 0; p < animals.size(); p++) { //loops through every animal in the array, will update location then compare to EVERY SQUARE

            int desperation = animals.get(p).getEnergy(); //animals.get(p).getEnergy();
            double probility = 0;
            if (desperation < (animals.get(p).getEnergyMax() / 1.5)) {
                probility = r.nextDouble();
            }
                if (probility > PROBABILITY) {


                    Point purpose = moveWithPurpose(animals.get(p), animalPoints.get(p));

                    double purposeX = purpose.getX();
                    double purposeY = purpose.getY();
                    if (purposeX > WIDTH || purposeX < 0) {
                        //loops around to the left
                        purposeX = 0;
                    }
                    if (purposeY > HEIGHT || purposeY < 0) {
                        purposeY = 0;
                    }
                    animalPoints.get(p).setLocation((int) purposeX , (int) purposeY ); //sets location for the purpose

                    animals.get(p).useEnergy();

            } else {


                int oldX = (int) animalPoints.get(p).getX();
                int oldY = (int) animalPoints.get(p).getY();

                int scalex = r.nextInt(2 + 1) - 1;
                int scaley = r.nextInt(2 + 1) - 1;

                int newX = oldX + scalex * (animals.get(p).getSpeed());
                int newY = oldY + scaley * (animals.get(p).getSpeed());


                if (newX >= WIDTH || newX < 0) {
                    //loops around to the left
                    newX = WIDTH;
                }
                if (newY >= HEIGHT || newY < 0) {
                    newY = HEIGHT;
                }

                animalPoints.get(p).setLocation((int) newX, (int) newY); //sets location not for purpose, random

                animals.get(p).useEnergy(); //burns energy

            }
        }
    }






public void eat(){// gives food based on size
    for (int p = 0; p < animals.size(); p++){
            int i = locateFoodSquare((int)(animalPoints.get(p).getX()), (int)(animalPoints.get(p).getY()) );

                int eatBonus = 1;
                int size = animals.get(p).getSize();
                switch (size) {
                    case 1:
                        eatBonus = 5;
                        break;
                    case 2:
                        eatBonus = 6;
                        break;
                    case 3:
                        eatBonus = 7;
                        break;
                    case 4:
                        eatBonus = 7;
                        break;
                    case 5:
                        eatBonus = 8;
                        break;
                    case 6:
                        eatBonus = 8;
                        break;
                    case 7:
                        eatBonus = 9;
                        break;
                    case 8:
                        eatBonus = 16;
                        break;
                    case 9:
                        eatBonus = 18;
                        break;
                    case 10:
                        eatBonus = 20;
                        break;

                }

        if (i < foodSquares.size()) {
            double newAmt = foodSquares.get(i).getFoodAmt() - (eatBonus / 5); //makes them use less food
            if (newAmt >= foodSquares.get(i).getMinFood()) {
                if (newAmt > foodSquares.get(i).getFoodAmt()) {
                    animals.get(p).setEnergy(animals.get(p).getEnergy() + (int) foodSquares.get(i).getFoodAmt());
                    foodSquares.get(i).setFoodAmt(foodSquares.get(i).getMinFood());
                    foodSquares.get(i).setColor();
                } else {

                    animals.get(p).setEnergy(animals.get(p).getEnergy() + (eatBonus * 3));
//                    Random r = new Random();
//                    double prob = r.nextDouble();
//                    if (prob > .8) {
//                        animals.get(p).setSize(animals.get(p).getSize()+1); this makes babies.... doesnt work but something to do later. should make babies on a very small probability.
//                    }


                    foodSquares.get(i).setFoodAmt(newAmt);
                    foodSquares.get(i).setColor();
                }
            }
        }

            }

        }


private Point moveWithPurpose(Animal a, Point point) { // looks for all the squares and finds the one with the most food and goes to it.
    Point p = point;
    int location = locateFoodSquare((int) p.getX(), (int) p.getY());

    int indexOfFoodRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY());
    int indexOfFoodLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY());
    int indexOfFoodUp = locateFoodSquare((int) p.getX(), (int) p.getY() + squareHeight);
    int indexOfFoodDown = locateFoodSquare((int) p.getX(), (int) p.getY() - squareHeight);
    int indexOfFoodUpLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY() + squareHeight);
    int indexOfFoodUpRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY() + squareHeight);
    int indexOfFoodDownLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY() - squareHeight);
    int indexOfFoodDownRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY() + squareHeight);

    ArrayList<Integer> storage = new ArrayList(); //holds all squares adjacent
    storage.add(location);
    storage.add(indexOfFoodRight);
    storage.add(indexOfFoodLeft);
    storage.add(indexOfFoodUp);
    storage.add(indexOfFoodUpLeft);
    storage.add(indexOfFoodUpRight);
    storage.add(indexOfFoodDown);
    storage.add(indexOfFoodDownLeft);
    storage.add(indexOfFoodDownRight);
    double max = FoodSquare.getMinFood(); //
    int bestSquare = 0;
    for (int j = 0; j < storage.size(); j++) { //finds best square
        if (storage.get(j) < foodSquares.size()) {



                if (getFoodSquares().get(storage.get(j)).getFoodAmt() > max) {
                    max = getFoodSquares().get(storage.get(j)).getFoodAmt();
                    bestSquare = storage.get(j);
                    if (bestSquare >= NUM_FOODSQUARES_PER_ROW*NUM_FOODSQUARES_PER_ROW){
                        bestSquare = storage.get(0); //if its too big, dont go
                    }
                }
        }
    }

        int moveX = (int) getFoodRects().get(bestSquare).getX();

        int moveY = (int) getFoodRects().get(bestSquare).getY();
        int diffX = moveX - (int) point.getX()+1; //fixed order of x
        int diffY = moveY - (int) point.getY()+1 ;

        //I know the error, the animals are moving to non-existant places! Need to // FIXME: 10/22/17


        if (diffX < 0 && Math.abs(diffX) > a.getSpeed()) {
            diffX =   -1 * a.getSpeed(); //should be -1 *
        } else if (diffX > 0 && Math.abs(diffX) > a.getSpeed()) {
            diffX =  a.getSpeed();
        }

        if (diffY < 0 && Math.abs(diffY) > a.getSpeed()) {
            diffY =   -1 * a.getSpeed(); //should be -1 *
        } else if (diffY > 0 && Math.abs(diffY) > a.getSpeed()) {
            diffY =  a.getSpeed(); //-1 should be up
        }


        a.useEnergy();

    if(foodSquares.get(locateFoodSquare((int) p.getX(), (int) p.getY())).getFoodAmt() >= FoodSquare.MAX_FOOD*.25){
        return p;
    }else {
        return new Point(diffX + (int) p.getX(), diffY + (int) p.getY()); //you need to add the animals x and y to diffx and diffy
    }

}



    private void setSquares(){

        //we build the array here with a correct double loop.
        for (int row = 0; row < getWIDTH()/squareWidth; row++ ){
            for (int cols = 0; cols < getHEIGHT()/this.squareHeight; cols++){
                foodSquares.add(new FoodSquare(FoodSquare.MAX_FOOD));
                Rectangle r = new Rectangle(cols*squareWidth, row*squareHeight, squareWidth, squareHeight);
                foodRects.add(r);
            }

        }


            }
            //to return the integer index that the animal is inside
            public int locateFoodSquare(int x, int y){


              int colPos =  (x-1)/squareWidth; //x = 50, y = 50, squareWidth = 10 -> col pos = 5 -1
                int rowPos = (y-1)/squareWidth;
                if(colPos < 0){
                    colPos = 0;
                }
                if (rowPos < 0){
                    rowPos = 0;
                }

                return colPos + NUM_FOODSQUARES_PER_ROW * rowPos;

            }

          public void makeBabies(Animal q){ //makes babies but it doesn't work. theres good stuff here though. When it does, favorable traits will avg. out
              Random r = new Random();

                  Animal potentialParent = q;
                  if (potentialParent.getEnergy() >= potentialParent.getEnergyMax()*.75){
                     double rand = r.nextDouble();
                      if (rand > .99){
                          int mutateSpeed;
                          int mutateSize;
                          Animal baby = new Animal();
                          double probSpeed = r.nextDouble();
                          if (probSpeed <= .33){ //the mutation probabilities... makes everything different
                              mutateSpeed = potentialParent.getSpeed()-1;
                          }else if (probSpeed > .33 && probSpeed <= .66){
                              mutateSpeed = potentialParent.getSpeed();
                          }else {
                              mutateSpeed = potentialParent.getSpeed()+1;
                          }

                          double probSize = r.nextDouble();
                          if (probSize <= .33){
                              mutateSize = potentialParent.getSize()-1;
                          }else if (probSize > .33 && probSize <= .66){
                              mutateSize = potentialParent.getSize();
                          }else {
                              mutateSize = potentialParent.getSize()+1;
                          }
                          animals.add(new Animal(mutateSize, mutateSpeed));
                          //System.out.println("NEW ANIMAL");
                      }
                  //System.out.println("looped");

              }
          }









//getters!!!
    public Animal getAnimal(int index) {
            return animals.get(index);
    }

    public Point getAnimalPoint(int index){
        return animalPoints.get(index);
    }

    public Color getColors(int index) {
        return colors.get(index);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public ArrayList<FoodSquare> getFoodSquares() {
        return foodSquares;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Point> getAnimalPoints() {
        return animalPoints;
    }
}

