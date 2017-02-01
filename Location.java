//Amy Wang
//Final Project Part 1 - Location.java
//5/8/15
//Essentially quite similar to GridWorld's Location class--a simple square region with a
//coordinate x and y value representing its position on the overall Tetris board. Can be filled or
//emptied with color.

import java.util.*;
import java.awt.*;

public class Location 
{
/* The side length of the Location, when drawn on the JPanel
 */
   private int size; 
/* The color of the Location--the color with which Location is filled in
 */
   private Color color;
/* The x coordinate of the Location (in the context of the coordinate-system of the
 * TetrisBoard)
 */
   private int xcoord;
/* The y coordinate of the Location
 */
   private int ycoord;
/* The upper left point of the Location square
 */
   private Point top;  
/* Tells whether or not the Location is being occupied by a TetrisPiece
 */
   private boolean full; 
/* The Graphics object upon which the Location will be drawn
 */
   private Graphics g;

/* Constructor method; uses the passed in parameter values to create a new Location, which is 
 * essentially a coordinate square that can be filled or emptied with a Color. 
 * Full is initially set to false.
 * @param size - the length of one side of the Location object
 * @param x - the x coordinate of the Location (the TetrisBoard is a 2D array of 
 * Locations, and thus a type of coordinate system)
 * @param y - the y coordinate of the Location
 * @param c - the Color with which the Location will be filled, if full
 */ 
   public Location(int size, int x, int y, Color c) 
   {
      this.size = size;
      xcoord = x; //corresponds to column number
      ycoord = y; //corresponds to row number
      top = new Point(x*size, y*size);
      color = c;
      this.full = false;
   }

/* Returns the "top," or upper-left corner of the Location's coordinate
 * square.
 * @return top - the upper-left point of the Location, when the Location is
 * drawn
 */
   public Point getTop()
   {
      return top;
   }

/* Returns the x coordinate of the Location
 * @return xcoord - the x coordinate of the Location (generally
 * in relation to the TetrisBoard)
 */
   public int getX()
   {
      return xcoord;
   }

/* Returns the y coordinate of the Location
 * @return ycoord - the y coordinate of the Location (generally
 * in relation to the TetrisBoard)
 */
   public int getY()
   {
      return ycoord;
   }

/* Returns the size of the side length of the Location
 * @return size - the size of one side of the Location, when
 * drawn on the JPanel
 */
   public int getSize()
   {	
      return size;
   }
   
/* Checks to see if the Location is full (Locations are full if occupied
 * by either a moving or resting Tile)
 * @return full - a boolean representing whether or not the Location is full
 */
   public boolean isFull()
   {
      return full;
   }

/* "Fills" the Location by setting full to true
 */
   public void fill() 
	{
      full = true;
   }

/* "Empties" the Location by setting full to false
 */
   public void empty()
   {
      full = false;
   }

   public Color getColor()
   {
      return color;
   }
   
/* Sets the Color of the Location to the passed-in parameter
 * -- generally utilized when drawing in Locations on the TetrisBoard
 * @param c - the Color the Location will later be drawn in 
 */
   public void setColor(Color c)
   {
      color = c;
   }
   

/* Draws the Location on the JPanel by filling in a square of side length size
 * starting from the Point "top"
 * @param graphics - the Graphics object that will help draw the Location
 */
   public void drawSelf(Graphics graphics)
   {
      if(graphics!=null)
      {
         if(full)
         {
            graphics.setColor(color);
         }
         else
         {
            graphics.setColor(Color.BLACK);
         }
         //System.out.println("" + (int)top.getX() + (int)top.getY());
         graphics.fillRect((int)top.getX(), (int)top.getY(), size, size);
      }
   }

/* Equals method - checks if the two Locations share the same top left corner (or "starting point")
 * as well the same side length
 * @param other - the other Location, to be compared to this one
 * @return - true if the two Locations are located at the same place as one another, and
 * also have the same side length 
 */
   public boolean equals(Location other)
   {
      if(other.getTop()==this.getTop() && other.getSize()==this.getSize())
      {
         return true;
	
      }
      return false;
   }
}










































































































































