import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Display
{
   public static void main (String[] args)
   {   
      JFrame frame = new JFrame("Father's Day!");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setPreferredSize(new Dimension(500, 500));
      frame.setResizable(false);
      
      DrawingPanel p = new DrawingPanel(800, 800);

      Location[][] locations = new Location[8][8];
      Graphics g = p.getGraphics();
      
      for(int ii=0; ii<locations.length; ii++)
      {
         for(int jj=0; jj<locations[0].length; jj++)
         {
            if((ii%2==1 && jj%2==0) || (ii%2==0 && jj%2==1))
               locations[jj][ii] = new Location(100, ii, jj, Color.BLACK);
            else
               locations[jj][ii] = new Location(100, ii, jj, Color.WHITE); 
            Location loc = locations[jj][ii];
            loc.fill();
            loc.drawSelf(g); 
         }
      }
      g.setColor(Color.GREEN);
      Font stringfont = new Font( "SansSerif", Font.PLAIN, 50);
      g.setFont(stringfont);
      p.sleep(600);
      g.drawString("HAPPY", 200, 300);
      p.sleep(600);
      g.drawString("FATHER'S", 300, 350);
      p.sleep(600);
      g.drawString("DAY!!", 400, 400);
      stringfont = new Font( "SansSerif", Font.PLAIN, 20);
      g.setColor(Color.RED);
      g.setFont(stringfont);
      p.sleep(600);
      g.drawString("<3 Amy", 400, 450);
   }
}


