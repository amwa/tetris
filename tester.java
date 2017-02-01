import java.util.*;
import java.awt.*;
import javax.swing.*;

public class tester //implements KeyListener
{
   public static void main(String[] args)
   {
      /*JFrame frame = new JFrame("tetris!");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setPreferredSize(new Dimension(500, 500));
      frame.pack();
      frame.setResizable(false);
      
      JPanel panel = new JPanel();
      Graphics g = panel.getGraphics();
      
      if(g==null)
      {
         System.out.println("g is null");
      } */
      
      DrawingPanel panel = new DrawingPanel(500, 500);
      Graphics g = panel.getGraphics();
      System.out.println(g);
      //g.setColor(Color.BLACK);
      //g.fillRect(50, 50, 50, 50);
      //panel.setBackground(Color.YELLOW);
 
      Location loc = new Location(50, 9, 9, Color.BLUE);
      System.out.println(loc.getTop());
      loc.fill();
      loc.drawSelf(g);
      
      for(int ii=0; ii<10; ii++)
      {
         Location newloc = new Location(50, ii, 8, Color.BLACK);
         newloc.fill();
         newloc.drawSelf(g);
      }
   }
   /*public void keyPressed(KeyEvent e)
   {
      System.out.println("Key pressed");
      if (e.getKeyCode()==KeyEvent.VK_RIGHT)
      {
         System.out.println("Right key pressed");
      }
      if (e.getKeyCode()==KeyEvent.VK_LEFT)
      {
         System.out.println("Left key pressed");
      }
   }
   
   public void keyReleased(KeyEvent e)
   {
   }
   
   public void keyTyped(KeyEvent e)
   {
   } */
}