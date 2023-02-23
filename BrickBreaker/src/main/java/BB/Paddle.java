package BB;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
    //Fields
    private double x;
    private int width, height, startWidth, startHight;
    private long widthTimer;
    private boolean altWidth;
    private double targetx;

    public static final int YPOS = BBMain.HEIGHT - 100;
    //Constructor
    public Paddle(int theWidth, int theHight) {                            // we made the constructor of the class and give it valuables.
        altWidth = false;
        startWidth = theWidth;
        startHight = height;
        width = theWidth;
        height = theHight;
        x = BBMain.WIDTH /2 - width / 2;
    }

    //Update
    public void Update() {
        if((System.nanoTime() - widthTimer) / 1000 > 9000000) {             // the update method is used to update the paddle in the GamePanel.
            width = startWidth;
            altWidth = false;
        }
        x += (targetx - x) * 0.3;
    }

    //Draw
    public void draw(Graphics2D g) {                                        //the draw method is responsable to draw the paddle when used in the GamePanel.
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x, YPOS, width, height);

        if( altWidth == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier new", Font.BOLD, 18));
            g.drawString("   Shrinking in " + (9 - (System.nanoTime() - widthTimer) / 1000000000), (int)x, YPOS + 18);
        }
    }

    public void mouseMoved(int mouseXPos){                              //this method detect the mouse location on the screen so that the paddle moves with the mouse.
        targetx = mouseXPos - width / 2;

        if(targetx > BBMain.WIDTH - width) {
            targetx = BBMain.WIDTH - width;
        }
        if(targetx < 0){
        targetx = 0;
        }
    }

    // SETs and GETs
    public Rectangle getRect() {
        return new Rectangle((int)x, YPOS, width, height);
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int newWidth) {
        altWidth = true;
        width = newWidth;
        setWidthTimer();
    }

    public void setWidthTimer() {
        widthTimer = System.nanoTime();
    }
}
