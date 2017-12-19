import java.awt.*;
import java.util.Random;

/**
 * Josh Hascall
 * 9/18/17
 * Creates and sets up Foodsquares, the places where the food exists. They can regain health and have a color between white and black through grey.
 */
public class FoodSquare {
    private Color color;
    private double foodAmt;
    public static final double MAX_FOOD = 255; //255
    public static final double MIN_FOOD = 1;
    public static final double REGAIN_AMT = .08;


    public FoodSquare(double foodAmt){ //every square starts with full food and max color
        this.foodAmt = foodAmt;
        int colorAmt = (int)foodAmt;
        this.color = new Color((int)colorAmt,(int)colorAmt,(int)colorAmt);
        if (colorAmt <= MIN_FOOD){
            this.color = new Color((int)MIN_FOOD,(int)MIN_FOOD,(int)MIN_FOOD);
        }
    }

    public static double getMinFood() {
        return MIN_FOOD;
    }

    public void setFoodAmt(double foodAmt) {
        this.foodAmt = foodAmt;
        if (foodAmt <= MIN_FOOD){
            this.foodAmt = MIN_FOOD;
        }

    }

    public void regain(){ //gets more food over time.. the squares will slowly get more food
        setFoodAmt(foodAmt+REGAIN_AMT);
        setColor();
    }
//sets color respective to the amount of food that is in the square. Must be within index range. Most food white, middle-grey. No food black
    public void setColor() {
        double food = getFoodAmt();
        if (food > getMinFood() && food <= getMaxFood()) {
            this.color = new Color((int) food, (int) food, (int) food);
        }
    }
    //getters and setters

    public double getFoodAmt() {

        return foodAmt;
    }

    public Color getColor() {

        return color;
    }

    public static double getMaxFood() {
        return MAX_FOOD;
    }
}

