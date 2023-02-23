package BB;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    // fields
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMotionListener;
    private int moudeX;
    
    //object
    private Ball theBall;
    private Paddle thePaddle;
    private Map theMap;
    private HUD theHud;
    private ArrayList<Perk> perks;
    private ArrayList<BrickExplode> brickExplodes;
    
    //constructor
    public GamePanel(){
        init();
    }

    public void init(){
        moudeX = 0;                                                 
        theMap = new Map(4, 4);
        theBall = new Ball(300, 400, 6.5);
        thePaddle = new Paddle(100, 10);
        theHud = new HUD();
        perks = new ArrayList<Perk>();
        brickExplodes = new ArrayList<BrickExplode>();

        theMotionListener = new MyMouseMotionListener();
        addMouseMotionListener(theMotionListener);

        running = true;

        image = new BufferedImage(BBMain.WIDTH, BBMain.HEIGHT, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void playGame(){                                              //in this method we make the game alive by using the three method(updare, draw, repaint).
        //the Game Loop
        while(running){
            //update
            update();

            //draw
            draw();

            //display
            repaint();

            try {
                Thread.sleep(10);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCollisions(){                                      //in this big method me check of collisions
        
        Rectangle ballRect = theBall.getRect();
        Rectangle paddleRect = thePaddle.getRect();

        for(int i = 0; i < perks.size(); i++) {                     //this part is responsable for the any collision that happen with the perk bricks.
            Rectangle peRect = perks.get(i).getRect();

            if(paddleRect.intersects(peRect)) {
                if(perks.get(i).getType() == Perk.WIDEPADDLE && !perks.get(i).getWasUsed()) {
                    thePaddle.setWidth(thePaddle.getWidth() * 2);
                    perks.get(i).setWasUsed(true);
                }
                if(perks.get(i).getType() == Perk.FASTBALL && !perks.get(i).getWasUsed()){
                    theBall.setDY(theBall.getDY() * 2.3);
                    perks.get(i).setWasUsed(true);
                }
            }
        }

        if(ballRect.intersects(paddleRect)){                    //and this part is responsable about the collision  between the paddle and the ball.
            
            theBall.setY(thePaddle.YPOS - theBall.getSize());
            theBall.setDY(-theBall.getDY());

            if(theBall.getX() >= moudeX + thePaddle.getWidth() / 4) {
                theBall.setDX(theBall.getDX() + .5);
            }
            if(theBall.getX() + theBall.getSize() < moudeX - thePaddle.getWidth() / 4) {
                theBall.setDX(theBall.getDX() - .5);
            }
        }

       A: for(int row = 0; row < theMap.getMapArray().length; row++){               // and this part is responsable about the collisions between the ball and the bricks in the map.
            for(int colom = 0; colom < theMap.getMapArray()[0].length; colom++){
                if(theMap.getMapArray()[row][colom] > 0 ){
                    int brickx = colom * theMap.getBrickWidth() + theMap.HOR_PAD;
                    int bricky = row * theMap.getBrickHight() + theMap.VERT_PAD;
                    int brickWidth = theMap.getBrickWidth();
                    int brickHight = theMap.getBrickHight();
    
                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHight);
    
                    if(ballRect.intersects(brickRect)){
                        
                        if(theMap.getMapArray()[row][colom] == 1) {
                           brickExplodes.add(new BrickExplode(brickx, bricky, theMap));
                        }

                        if(theMap.getMapArray()[row][colom] > 3) {
                            perks.add(new Perk(brickx, bricky, theMap.getMapArray()[row][colom], brickWidth, brickHight));
                            theMap.setBrick(row, colom, 3);
                        }
                        
                        theMap.hitBrick(row, colom);
    
                        theBall.setDY(-theBall.getDY());

                        theHud.addScore(1);

                        break A;
                    }
                }
            }
        }
    }

    public void update(){                                   //in this method we call every update method from the other class's we we can update the opjects.
        theBall.update();
        checkCollisions();
        thePaddle.Update();

        for(Perk pe : perks) {
            pe.update();
        }
        for(int i = 0; i < brickExplodes.size(); i++){
            brickExplodes.get(i).update();
                                                            //to update the btickExplodes then to temove those whos not active no more.
            if(!brickExplodes.get(i).getIsActive()){
                brickExplodes.remove(i);
            }
        }
    }

    public void draw(){                                                 //in this method we use every draw method in the other calss's to draw the opjects
        g.setColor(new Color(90, 120, 128));
        g.fillRect(0, 0, BBMain.WIDTH, BBMain.HEIGHT);

        theMap.draw(g);

        theBall.draw(g);

        thePaddle.draw(g);
        
        theHud.draw(g);

        drawPerks();

        if(theMap.win() == true) {                      // to stop the game from updating if the player wins.
            drawWin();
            running = false;
        }

        if(theBall.lose()) {                            // to stop the game from updating if the player loses.
            drawLose();
            running = false;
        }
        
        for(BrickExplode bs : brickExplodes){           //to draw every element in the arrylist.
            bs.draw(g);
        }
    }

    public void paintComponent(Graphics g){             //importent method to draw the map
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 0, 0, BBMain.WIDTH, BBMain.HEIGHT, null);
        g2.dispose();
    }

    public void drawWin() {                             //this mithod draw the word WiNNER when the player wins.
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("!WINNER!", 300, 200);
    }

    public void drawLose() {                            //this method draw the word loser when the player loses.
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("!LOSER!", 300, 200);
    }

    public void drawPerks(){                // this method is responsable for drawing the perks
        for(Perk pe : perks) {
            pe.draw(g);
        }
    }
    
    private class MyMouseMotionListener implements MouseMotionListener{         //this private class implements the class MouseMotionListener and uses to use one of its method
        @Override
        public void mouseDragged(MouseEvent e) {   
        }

        @Override
        public void mouseMoved(MouseEvent e) {                      // we overrid this method to get the mouse postion and give it to the paddle so we can move the paddle with the mouse.
            moudeX = e.getX();
            thePaddle.mouseMoved(e.getX());
        }
    }
}
