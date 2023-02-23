package BB;

//Imports
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickPiece {

    //Fields
    private int x, y;
    private double dx, dy, gravity;
    private Color color;
    private Map theMap;
    private int size;

    //constructor
    public BrickPiece(int brickx, int bricky, Map theGameMap) {             // we made the constructor of the class and give it is valuables.

        theMap = theGameMap;
        x = brickx + (theMap.getBrickWidth() / 2);
        y = bricky + (theMap.getBrickHight() / 2);
        dx = (Math.random() * 30 - 15);
        dy = (Math.random() * 30 - 15);
        size = (int) (Math.random() * 15 + 2);
        color = new Color(200, 200, 200);
        gravity = 0.8;
    }

    public void update() {                                                   // the update method is used to update the pieces in the GamePanel.
        x += dx;
        y += dy;
        dy += gravity;
    }

    public void draw(Graphics2D g) {                                        //the draw method is responsable to draw the brick pieces when used in the GamePanel.
        g.setColor(color);
        g.fillRect((int) x, (int) y, size, size);
    }

}
