package BB;

//Imports
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
    //Fields
    private int[][] theMap;
    private int brickHight, brickWidth;
    public final int HOR_PAD = 80, VERT_PAD = 90;
    
    //constructor
    public Map(int row, int colom){                                     // we made the constructor of the class and give it valuables.
        initMap(row, colom);

        brickWidth = (BBMain.WIDTH - 2 * HOR_PAD) / colom;
        brickHight = (BBMain.HEIGHT / 2 - VERT_PAD * 2) / row;
    }

    public void initMap(int row, int colom){
        theMap = new int[row][colom];
        
        for(int i = 0;i < theMap.length; i++){
            for(int j = 0; j < theMap[0].length; j++){
                int r = (int)(Math.random() * 3 + 1);                   // to randomize the brick in the map (weak brick - normal brick - strong brick).
                theMap[i][j] = r;               
            }
        }
        
        int x5 =(int)(Math.random() * (row - 0));                       // to randomize the location of the perks.
        int y5 = (int)(Math.random() * (colom - 0));
        int x4 =(int)(Math.random() * (row - 0));                   
        int y4 = (int)(Math.random() * (colom - 0));
        if(x4 == x5){
            x4 = (int)(Math.random() * (row - 0));
        }
        theMap[x5][y5] = 5;
        theMap[x4][y4] = 4;
    }

    public void draw(Graphics2D g){                                         
        for(int row = 0; row < theMap.length; row++){                   // this method draws and gives every brick its color based on its level.
            for(int colom = 0; colom < theMap[0].length; colom++){
                if(theMap[row][colom] > 0){

                    if(theMap[row][colom] == 1) {
                        g.setColor(new Color(200, 200, 200));
                    }
                    if(theMap[row][colom] == 2) {
                        g.setColor(new Color(130, 130, 130));
                    }
                    if(theMap[row][colom] == 3) {
                        g.setColor(new Color(80, 80, 80));
                    }
                    if(theMap[row][colom] == Perk.WIDEPADDLE) {
                        g.setColor(Perk.WIDECOLOR);
                    }
                    if(theMap[row][colom] == Perk.FASTBALL) {
                        g.setColor(Perk.FASTCOLOR);
                    }

                    g.fillRect(colom * brickWidth + HOR_PAD, row * brickHight + VERT_PAD, brickWidth, brickHight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.WHITE);
                    g.drawRect(colom * brickWidth + HOR_PAD, row * brickHight + VERT_PAD, brickWidth, brickHight);
                }
            }
        }
    }

    public boolean win() {                                          //this method checks if all the bricks are destroyed then the player win.
        boolean aWin = false;

        int brickRemaning = 0;

        for(int row = 0; row < theMap.length; row++){
            for(int colom = 0; colom < theMap[0].length; colom++){
                brickRemaning += theMap[row][colom];
            }
        }

        if (brickRemaning == 0){
            aWin = true;
        }
        return aWin;
    }
    
    public void hitBrick(int row, int colom) {                          //this method detect if a brick gets hit.
        theMap[row][colom] = theMap[row][colom] - 1;
    }
    
    //Sets and Gets
    public int[][] getMapArray() {
        return theMap;
    }

    public void setBrick(int row, int colom, int value) {
        theMap[row][colom] = value;
    }

    public int getBrickWidth(){
        return brickWidth;
    }
    public int getBrickHight(){
        return brickHight;
    }


}