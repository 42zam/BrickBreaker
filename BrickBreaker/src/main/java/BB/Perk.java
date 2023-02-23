package BB;

//Imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Perk {

    //Fields
    private int x, y, dy, type, width, height;
    private boolean isOnScreen;                                 //to check if the perk sill on the screen or it fall out of the screen.
    private boolean wasUsed;                                    //to check if the perk is used or not so we can disable it if its used.
    private Color color;

    public final static int WIDEPADDLE = 4;
    public final static int FASTBALL = 5;
    public final static Color WIDECOLOR = new Color(10, 250, 170);
    public final static Color FASTCOLOR = new Color(255, 60, 70);
    //Constructor

    public Perk(int xStart, int yStart, int theType, int theWidth, int theHight) {  // we made the constructor of the class and give it is valuables.
        x = xStart;
        y = yStart;
        type = theType;                                                 //the color and motion of the perk brick.
        height = theHight;
        width = theWidth;

        if (type < 4) {
            type = 4;
        }
        if (type > 5) {
            type = 5;
        }
        if (type == WIDEPADDLE) {
            color = WIDECOLOR;
        }
        if (type == FASTBALL) {
            color = FASTCOLOR;
        }

        dy = (int) (Math.random() * 6 + 1);                              // randomize the falling speeed of the perk brick.

        wasUsed = false;
    }

    public void draw(Graphics2D g) {                                    //the draw method is responsable to draw the perk brick in the GamePanel.
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void update() {                                              // the update method is used to update the perk in the GamePanel.
        y += dy;

        if (y > BBMain.HEIGHT) {
            isOnScreen = false;
        }
    }

    //Sets and Gets
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setDY(int dy) {
        this.dy = dy;
    }

    public int getDY() {
        return dy;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setOnScreen(boolean isOnScreen) {
        this.isOnScreen = isOnScreen;
    }

    public boolean getOnScreen() {
        return isOnScreen;
    }

    public boolean getWasUsed() {
        return wasUsed;
    }

    public void setWasUsed(boolean used) {
        wasUsed = used;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

}
