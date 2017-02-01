//Amy Wang
//Comp Sci III Final project - Tetris Runner
//5/26/15
//Objective: Create Tetris-- a game involving moving Tiles and user interactivity.

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Final
{
   public static void main (String[] args)
   {   
      JFrame frame = new JFrame("tetris!");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setPreferredSize(new Dimension(500, 500));
      frame.setResizable(false);

      TetrisBoard board = new TetrisBoard(500, 500, 40);
      frame.add(board);
      frame.pack(); 
      frame.setVisible(true); 
   }
   
   /* Test plan 
    * Click the screen in random Locations while program is running
    * Press other keys aside from the arrow keys, to see if any effect is generated
    * Change the width, height, and Location size of the TetrisBoard
    * Change the TIMER_TICK constant in the TetrisBoard class
    */
}