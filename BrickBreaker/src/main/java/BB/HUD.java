package BB;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {

    //Fields
    private int score;

    //Constructor
    public HUD() {                                              // we made the constructor of the class and give it valuables.
        init();
    }

    public void init() {
        score = 0;
    }

    public void draw(Graphics2D g) {                            // the update method is used to update the HUD in the GamePanel.
        g.setFont(new Font("Courier New", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("SCORE : " + score, 20, 20);
    }

    public void addScore(int scoreToAdd) {                       //thos method adds to the score when needed.
        score = score + scoreToAdd;
    }

    public int getScore() {
        return score;
    }

}
