package BB;

//Imports
import javax.swing.JFrame;

public class TheFrame extends JFrame {    
    //Fields
    public GamePanel thePanel = new GamePanel();        // we create an object from the class GamePanle.
    
    //constructor
    TheFrame(){                                         //here we give the constructor its valuables.
        this.setSize(BBMain.WIDTH, BBMain.HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.add(thePanel);                         // we add the object of the class GamePanle that we made to the frame.

        thePanel.playGame();                            // we call this method of the object thats start our game.
    }
}
