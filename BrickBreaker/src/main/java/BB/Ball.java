package BB;
//Imports

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

    //Fields
    private double x, y, dx, dy, startDY;
    private int ballSize = 20;
    private long speedTimer;
    private Color normColor;

    public Ball(double theX, double theY, double speed) {       // we made the constructor of the class and give it is valuables.
        x = theX;
        y = theY;
        dx = 1;
        startDY = speed;
    }

    public void update() {                                      // the update method is used to update the ball in the GamePanel.
        setPosition();

        if ((System.nanoTime() - speedTimer) / 1000 > 3000000) {
            dy = startDY;
        }
        if (getDY() != 6.5) {
            normColor = new Color(255, 130, 26);
        }
        if (getDY() == 6.5 || getDY() == -6.5) {
            normColor = Color.DARK_GRAY;
        }
    }

    public void draw(Graphics2D g) {                            //the draw method is responsable to draw the ball when used in the GamePanel.
        g.setColor(normColor);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int) x, (int) y, ballSize, ballSize);
    }

    public void setPosition() {                                 // the method that is responsible of the ball position.
        x += dx;
        y += dy;

        if (x < 0) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        if (x > BBMain.WIDTH - ballSize) {
            dx = -dx;
        }
        if (y > BBMain.HEIGHT - ballSize) {
            dy = -dy;
        }
    }

    public boolean lose() {                                         //the lose method is the meethod that checks if the ball fell out of the map if so the game ends.
        boolean alose = false;
        if (y > BBMain.HEIGHT - ballSize * 2) {
            alose = true;
        }
        return alose;
    }

    //the SET's and GET's.
    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, ballSize, ballSize);
    }

    public void setDY(double theDY) {
        dy = theDY;
        setSpeedTimer();
    }

    public double getDY() {
        return dy;
    }

    public void setDX(double theDX) {
        dx = theDX;
    }

    public double getDX() {
        return dx;
    }

    public double getX() {
        return x;
    }

    public void setY(double theY) {
        y = theY;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return ballSize;
    }

    public void setSpeedTimer() {
        speedTimer = System.nanoTime();
    }

}
