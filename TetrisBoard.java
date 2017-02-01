//Amy Wang
//Final Project Part 3 - TetrisBoard.java
//5/11/15
//A representation of a Tetris "board," i.e. the screen of a Tetris game, comprised
//of a 2D array "grid" of Location objects that can contain moving Tetris Tiles.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class TetrisBoard extends JPanel implements ActionListener, KeyListener
{
/* The horizontal width of the board 
 */
   private int width;
/* The vertical height of the board
 */
   private int height;
/* The length of one side of one of the Location objects composing the board
 */
   private int locsize; 
/* A 2D array of Location objects, composing the layout of the board
 */
   private Location[][] locations;
/* The "active" Tile - the only one moving across the screen at the given time
 */
   private Tile activepiece;
/* A Random object used to generate random things throughout the program
 */
   private Random random;
/* The length of time, in milliseconds, the Timer will wait in between ticks, or calls
 * to actionPerformed
 */
   public static int TICK_TIME = 300;

/* Constructor method - makes new TetrisBoard, which is essentially a JPanel containing
 * a grid (a.k.a. a 2D array) of Location objects, each of them with size locsize
 * @param width - the horizontal width of the board
 * @param height - the vertical height of the board
 * @param locsize - the length of one side of a Location object "unit"--the board itself
 * is composed of this many units 
 */
   public TetrisBoard(int width, int height, int locsize)
   {
      super(new FlowLayout(FlowLayout.LEFT, 0, 0));
      setOpaque(true); 
      this.width = width;
      this.height = height;
      setSize(new Dimension(width, height));
      this.locsize = locsize;
      this.locations = new Location[width/locsize][height/locsize];
     
      int score = 0;
      JButton scorebox = new JButton("Score: " + score);
      random = new Random();
      
      for(int ii=0; ii<locations.length; ii++)
      {
         for(int jj=0; jj<locations[ii].length; jj++)
         {  
            locations[ii][jj] = new Location(locsize, jj, ii, Color.BLACK);
         }
      }
      activepiece = new Tile(this, locsize);
      
      Timer timer = new Timer(TICK_TIME, this);
      timer.start(); //will track the active piece and note whether or not it can move with each tick of the timer. 	
      
      addKeyListener(this); //adds a KeyListener to check if arrow keys are pressed
   }
 
 /* Paints the screen of the JPanel according to the positions and conditions of 
  * all of the objects on the board -- called with each tick of the Timer object
  * @param graphics - the Graphics object used draw the image onscreen
  */
   public void paintComponent(Graphics graphics)
   {
      super.paintComponent(graphics);
      for(int ii=0; ii<locations.length; ii++)
      {
         for(int jj=0; jj<locations[ii].length; jj++)
         {  
            locations[ii][jj].drawSelf(graphics);
         }
      } 
      activepiece.drawSelf(graphics);
   }

/* Dictates how the board should act given one tick of the timer--in other words,
 * with each timer tick, 1) moves the active Tile, activepiece; 2) if the Tile cannot move,
 * fills in the Locations on the TetrisBoard which the Tile had been occupying; 3) checks to see
 * if the Tile, once settled, finishes filling in a row of Locations; 4) if true, erases that row of
 * locations and shifts all rows above that row down 1 row (UNFINISHED IMPLEMENTATION!); 5) checks to 
 * see if the Tiles have stacked up to the very top row of the board; 6) if so, exits the game;
 * 7) if game hasn't been exited, repaints the screen;
 * @param event - the GUI ActionEvent that calls this method with each tick of the timer
 */
   public void actionPerformed(ActionEvent event)
   {
      boolean moved = activepiece.move('v');
      if(!moved)
      {
         //System.out.println("Stopped moving");
         Location[] locs = activepiece.getLocations();
         boolean rowfull = true; //boolean representing whether the row is completely filled or not
         for(int ii=0; ii<locs.length; ii++)
         {
            Location loc = locs[ii];
            int tempx = loc.getX();
            int tempy = loc.getY();
            locations[tempy][tempx].fill();
            locations[tempy][tempx].setColor(activepiece.getColor()); //maybe combine fill and setColor...
            //System.out.println("" + locations[0].length);
            int numfull = 0;
            for(int jj=0; jj<locations[0].length; jj++)
            {
               Location loc2 = locations[tempy][jj];
               if(checkFull(loc2))
               {
                  numfull++; //if any location in the row is empty, the row is not full
                  //System.out.println("Location " + tempx + " " + jj + "is not full");
               }
               if(locations[0][jj].isFull())
               {
                  System.out.println("GAME OVER");
                  System.exit(0);
               }
            }
            if(numfull==locations[0].length)
            {
               //System.out.println("row is full");
               for(int jj=0; jj<locations[0].length; jj++)
               {
                  locations[tempy][jj].empty();
                  //System.out.println("emptying location1");
               }
               for(int qq=ii; qq>0; qq--)
               {
                  for(int k=0; k<locations.length; k++)
                  {
                     Location temp = locations[qq][k];
                     int x = temp.getX();
                     int y = temp.getY();
                     Color color = temp.getColor();
                     y += 1;//adds locsize to the y value of the point--moves the location down 1 unit
                     locations[qq][k] = new Location(locsize, tempx, tempy, color);
                  }
               }
            } 
         } 
         activepiece = new Tile(this, locsize);
      }
      repaint();
   } 

/* Checks to see if the passed-in Location is full and/or within the bounds of the TetrisBoard--
 * in order to decide whether the Tile can keep moving into the location it is attempting to move to.
 * @param loc - the Location to be checked
 * @return - true if the Location is not within the bounds of the board, or if its instance variable
 * "full" = true; false otherwise
 */
   public boolean checkFull(Location loc)
   {
      int tempx = loc.getX();
      int tempy = loc.getY();
      if(tempx>=locations.length || tempy>=locations[0].length || tempx<0 || tempy<0 || locations[tempy][tempx].isFull()) //check if the location is outside the area of the grid, or is full (NOTE THE EPIC SHORT CIRCUIT EVALUATION!)
      {
         //System.out.println("loc is full");
         return true;
      }
      return false;
   }

/* Called by the GUI to notify the board that a key has been pressed by the user, and also
 * requests the "focus" of the key press
 */ 
   public void addNotify()
   {
      super.addNotify();
      requestFocus();
   }

/* Dictates the board's actions when a key has been pressed; if the key
 * is the left, right, or down arrow, calls the "move" method on the active Tile,
 * passing in the corresponding character. Then repaints the board, calling the paint
 * Component method
 * @param e - the keyEvent triggered by the user pressing a key
 */  
   public void keyPressed(KeyEvent e)
   {
      //System.out.println("Key pressed");
      if (e.getKeyCode()==KeyEvent.VK_RIGHT)
      {
         activepiece.move('>');
      }
      if (e.getKeyCode()==KeyEvent.VK_LEFT)
      {
         activepiece.move('<');
      }
      if (e.getKeyCode()==KeyEvent.VK_DOWN)
      {
         activepiece.move('v');
      }
      repaint();
   }

/* Dictates the board's actions when a key has been released -- which in this case
 * is nothing. 
 * @param e - the keyEvent triggered by the user releasing a key
 */
   public void keyReleased(KeyEvent e)
   {
   }

/* Dictates the board's actions when a key has been typed -- which in this case
 * is nothing. 
 * @param e - the keyEvent triggered by the user typing a key
 */
   public void keyTyped(KeyEvent e)
   {
   }
   
   /* FUTURE WORK
               	Rotate the piece w/ rotate method
                  Create new EventListener checkiffullchecks each time tick to make sure that the row of Locations in which the TetrisPiece has fallen/solidified is not completely full.
                   	If(the row is full)
                   Empty all the Locations in the row and shift down all the others on top to fill the blank spaces (by erasing and redrawing 1 below)
                   Add 10 to score
                   Create new EventListener gameend checks to see if the game has ended (i.e. a block is touching the top of the panel)
                   	If(the top row of Locations is NOT completely empty)	
                   	Stop everything and display a new dialog box that says GAME OVER + score    }*/
}






















































































































