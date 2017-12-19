/**
 * Josh Hascall
 * 9/18/17
 * Creates and sets up animals. Their size, energy (hunger/food) amount, color, and speed.
 */
import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class JDriver extends JPanel {
    private static final int INITAL_ANIMALS = 50;
    private Enviorment enviorment;

    public JDriver() {
        this.enviorment = new Enviorment(INITAL_ANIMALS);
    } //sets up the enviroment

    public void paint(Graphics g) {
        Random r = new Random();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        enviorment.moveAnimals(); //every time through, move and eat all the animals.
        enviorment.eat();


        for (int i = 0; i < enviorment.getFoodRects().size(); i++) { //loops through all squares and draws each one
            Rectangle curr = enviorment.getFoodRects().get(i);

            FoodSquare f = enviorment.getFoodSquares().get(i);

            g2d.draw(curr);
            g2d.setColor(f.getColor());
            g2d.fillRect((int) curr.getX(), (int) curr.getY(), curr.width, curr.height);
            f.regain(); //get more food per turn regains the foodsquares
        }




        for (int i = 0; i < enviorment.getAnimals().size(); i++) { //loops through all the animals and if theyre not dead, draw them

            int x = (int) enviorment.getAnimalPoint(i).getX();
            int y = (int) enviorment.getAnimalPoint(i).getY();
            int xSize = enviorment.getAnimal(i).getSize();
            int ySize = enviorment.getAnimal(i).getSize();
            if (!enviorment.getAnimal(i).die()) {
                g2d.drawOval(x, y, xSize, ySize); //they are beautiful circles
                g2d.setColor(enviorment.getAnimals().get(i).getC());
                g2d.fillOval(x, y, xSize, ySize);

            }else{
                enviorment.getAnimals().remove(i); //if they are dead, they go away
                enviorment.getAnimalPoints().remove(i);
                i--;
            }



        }



    }
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Animals");
        JDriver jDriver = new JDriver();
        frame.add(jDriver);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            jDriver.repaint();
            Thread.sleep(10); //waits a bit before re-running


        }
    }
    }

