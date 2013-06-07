	//Name______________________________ Date_____________
   import java.awt.*;
   import javax.swing.JPanel;
   import java.awt.image.BufferedImage;
   import javax.swing.*;
   import java.awt.event.*;
   import java.awt.image.*;	
	import javax.swing.JDialog;
   public class Panel03 extends JPanel
   {
      private BufferedImage myImage;
      private static Timer timer, c, cc, traps;  
		private Hexagon hex;
		private Triangle tri;
		private Graphics buffer;
		private Trap[] trap;
		private double ang = 0;
		private double inc = 0.2;
		private int elapsed, tspeed, neg;
		private static boolean coc, first, go;
		
      public Panel03()
      {
			neg = 1;
			first = true;
			tspeed = 10;
			go = false;
         final int FRAME = 600; //width & height of buffered-image
			setFocusable(true);
         myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         buffer = myImage.getGraphics();	
			coc = false;
			buffer.setFont(new Font("Arial",0, 28));

			buffer.setColor(Color.RED);
			buffer.fillRect(0,0,FRAME, FRAME);
			hex = new Hexagon(FRAME/2, FRAME/2, 30, new Color(255,255,0));
			tri = new Triangle(FRAME/2, FRAME/2, 10, new Color(0,255,0), hex);
			trap = new Trap[6];
			for(int q = 0; q<trap.length; q++)
			trap[q] = new Trap(FRAME/2, FRAME/2, (q * 60), Color.BLACK, hex, q);
         timer = new Timer(tspeed, new Listener());
			traps = new Timer(tspeed, new TListener());			
			
			buffer.setColor(Color.RED);
			buffer.fillRect(0,0,600,600);
				ang = 0;
				hex.drawLines(buffer);
				
				tri.rotate((int)ang, buffer);

				for(int q = 0; q<trap.length; q++)
				trap[q].rotate((int)ang, buffer);	
				hex.rotate((int)ang, buffer);
				repaint();
					
			

			 
			
			timer.start();

         c = new Timer(tspeed, new CListener());
         cc = new Timer(tspeed, new CCListener());
			
     		this.addKeyListener(new Key());
						
			
			
	
      }

   private class KeyR extends KeyAdapter
   {
    public void keyPressed(KeyEvent e)
    {
	 	if(go)
		{
	 	restart();
		go = false;
		}
	 
	 }
	 
	}

   private class Key extends KeyAdapter
   {
    public void keyPressed(KeyEvent e)
    {
	 
	if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
	{
			cc.stop();
         c.start();
			
	}
	else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
	{		
			c.stop();
         cc.start();		
	}
	else if(e.getKeyCode() == KeyEvent.VK_SPACE)
	{		
			restart();		
	}	
	
	
			
	 }
	 
	 
	 
	 public void keyReleased(KeyEvent e)
	 {
	if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
	{
			c.stop();
			
	}
	else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
	{		
			cc.stop();
	}
	 }
	}
	
		public static void stopListeners()
		{
			c.stop();
			cc.stop();
			coc = false;
		}
	
      private class CListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {		
				tri.incExtra(4);
			}
		}
		
      private class CCListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {		
				tri.incExtra(-4 - (int)(2 * inc));
				coc = true;
			}
		}
		
		
      private class TListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {		
			for(int q = 0; q<trap.length; q++)
			trap[q].shrinkDist(2);
			}
		}					
		public void restart()
		{
		c.stop();
		cc.stop();
		timer.stop();
		traps.stop();
		for(int l = 0; l<trap.length; l++)
		trap[l].redist();
		elapsed = 0;
		ang = 0;
		inc = 0.2;
		timer.start();
		traps.start();
		}	  
		
      private class Listener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {	
				
 			elapsed +=1;
	
			if(elapsed == 1 && first == true)
			{
				
				if(JOptionPane.showConfirmDialog(null, "Game by Matthew Kaufer. \nUse arrow keys or A & D to move. \nDon't hit the trapezoids!", "Mundane Hexagon", JOptionPane.DEFAULT_OPTION) == 0)
				{
					first = false;
					timer.start();
					traps.start();				
				}			
			}
			
			buffer.setColor(Color.RED);
			buffer.fillRect(0,0,600,600);
				ang += inc;
				ang = ang % 360;
				hex.drawLines(buffer);
				
				if(!coc)
				tri.rotate((int)ang, buffer);
				else
				tri.rotate((int)(ang - (2 * inc)), buffer);
				for(int q = 0; q<trap.length; q++)
				{
				trap[q].rotate((int)ang, buffer);	
				}
				hex.rotate((int)ang, buffer);
				double k = (elapsed) / 10 / 10.0;
 				buffer.drawString("Time: " + k, 425, 50);
				
									
				repaint();
				inc += 0.00075;
				if(hex.checkCol(tri, trap, timer))
				{
				showLose();
				
					

				
				}
		
				
//  			}
			}
		}

		public void showLose()
		{
				go = true;
				c.stop();
				cc.stop();
				double k = (elapsed) / 10 / 10.0;
				
				System.out.println("GG!");
				buffer.setColor(Color.GREEN);
				buffer.fillRect(0,0,600,600);	
				buffer.setColor(Color.RED);			
 				buffer.drawString("You died! ", 50, 125);		
				buffer.setColor(Color.WHITE);			
				
 				buffer.drawString("Your score: " + k, 50, 200);		
 				buffer.drawString("Press space to restart.", 50, 275);		
				
		
		}

      public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   }