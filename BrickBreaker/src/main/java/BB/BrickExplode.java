package BB;

//Imports
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BrickExplode {
    
    //Fields
    private ArrayList<BrickPiece> pieces;
    private int x, y;
    private Map theMap;
    private boolean isActive;
    private long starTime;
    
    
    //constructor
    public BrickExplode(int theX, int theY, Map theMap) {                   // we made the constructor of the class and give it is valuables.
        x = theX;
        y = theY;
        this.theMap = theMap;
        isActive = true;
        starTime = System.nanoTime();
        pieces = new ArrayList<BrickPiece>();
        init();
        
    }
    public void init() {    
        int randNum = (int)(Math.random() * 20 + 5);                        // to randomize how many brick pieces will fall when the brick explode.
        
        for(int i = 0; i < randNum; i++) {
            pieces.add(new BrickPiece(x, y, theMap));
        }   
    }
    public void update() {                                                   // the update method is used to update every element in the arry list pieces.
        for(BrickPiece bp : pieces) {
        bp.update();
        }
        
        if((System.nanoTime() - starTime)/ 1000000 > 2000 && isActive) {
            isActive = false;
        }
    }
    public void draw(Graphics2D g) {                                         //the draw method is responsable to draw the brick explosion.
        for(BrickPiece bp : pieces){
            bp.draw(g);
        }
    }
    
    public boolean getIsActive() {
        return isActive;
    }
}
