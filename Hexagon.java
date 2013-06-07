import java.awt.*;
import java.awt.image.BufferedImage;
   import javax.swing.JPanel;
   import javax.swing.*;
   import java.awt.event.*;
   import java.awt.image.*;	
	import javax.swing.JDialog;

public class Hexagon
{
	private int x, y, rad,angle, bt, ta;
	private double[] coordx, coordy;
	private Color col;

	
	public Hexagon(int x1, int y1, int r1, Color c)
	{
		rad = r1;
		x = x1;
		y = y1;
		col = c;
		angle = 0;
		bt = 2;
		coordx = new double[6];
		coordy = new double[6];
		for(int f = 0; f<coordx.length; f++)
		{
			coordx[f] =(x + rad * Math.sin((60 * f + 30) * Math.PI / 180));
   		coordy[f] =(y + rad * Math.cos((60 * f + 30) * Math.PI / 180));
		}		

		

	}
	public void drawHex(Graphics g, double[] xa, double[] ya, Color c)
	{
		
		g.setColor(c);
		int[] tempx = new int[6];
		int[] tempy = new int[6];
		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] = (int)xa[f];
			tempy[f] = (int)ya[f];
		
		}		
		g.fillPolygon(tempx, tempy, 6);

				
		
		
	
	}
	
	
	public boolean checkCol(Triangle t, Trap[] p, Timer a)
	{
		int u = p[t.getSector()].getD();
		if(u <= t.getD() + 15 && u >= p[t.getSector()].getHeight())
		{
		a.stop();
		return true;
		}
		return false;
	}
	

	
	public double[] getX()
	{
		return coordx;
	}
	public double[] getY()
	{
		return coordy;
	}
	public Color getColor()
	{
	return col;
	}
	public void rotate(int angledegrees, Graphics g)
	{
		angledegrees *= -1;
		double[] tempx = new double[6];
		double[] tempy = new double[6];
		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] =(x + rad * Math.sin((60 * f + 30 + angledegrees) * Math.PI / 180));
   		tempy[f] =(y + rad * Math.cos((60 * f + 30 + angledegrees) * Math.PI / 180));
		}		
		
		//border
		double[] t2x = new double[6];
		double[] t2y = new double[6];
		

		for(int f = 0; f<coordx.length; f++)
		{
			t2x[f] =(x + (rad + bt) * Math.sin((60 * f + 30 + angledegrees) * Math.PI / 180));
   		t2y[f] =(y + (rad + bt) * Math.cos((60 * f + 30 + angledegrees) * Math.PI / 180));
		}	
		drawHex(g, t2x, t2y, Color.BLACK);			

		
		angle = angledegrees;
		drawHex(g, tempx, tempy, col);	
// 		
// 				
// 		double[] lx = new double[6];
// 		double[] ly = new double[6];	
// 		
// 			
// 		g.setColor(Color.BLACK);
// 		for(int f = 0; f<coordx.length; f++)
// 		{
// 			lx[f] =(x + 1000 * Math.sin((60 * f + 30 + angledegrees) * Math.PI / 180));
//    		ly[f] =(y + 1000 * Math.cos((60 * f + 30 + angledegrees) * Math.PI / 180));
// 			g.drawLine((int)lx[f], (int)ly[f], (int)tempx[f], (int)tempy[f]);
// 			
// 		}			
	
	}
	public void setTA(int a)
	{
		ta = a;
	}
	public void drawLines(Graphics g){
		double colint = angle;
		colint = Math.toRadians(colint);
		g.setColor(Color.BLACK);
		double[] lx = new double[6];
		double[] ly = new double[6];		
		double[] tempx = new double[6];
		double[] tempy = new double[6];

		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] =(x + rad * Math.sin((60 * f + 30 + angle) * Math.PI / 180));
   		tempy[f] =(y + rad * Math.cos((60 * f + 30 + angle) * Math.PI / 180));
		}			
		for(int f = 0; f<coordx.length; f++)
		{
			lx[f] =(x + 1000 * Math.sin((60 * f + 30 + angle) * Math.PI / 180));
   		ly[f] =(y + 1000 * Math.cos((60 * f + 30 + angle) * Math.PI / 180));
			g.drawLine((int)lx[f], (int)ly[f], (int)tempx[f], (int)tempy[f]);
			
		}		
		for(int f = 0; f<tempx.length; f++)
		{
			int[] xs = {(int)tempx[f], (int)lx[f], (int)lx[(f + 1) % 6], (int)tempx[(f + 1) % 6]};
			int[] ys = {(int)tempy[f], (int)ly[f], (int)ly[(f + 1) % 6], (int)tempy[(f + 1) % 6]};
// 			if(f % 2 == 0)
// 			g.setColor(new Color((int)Math.abs((Math.cos(colint) * 255)),(int)Math.abs((Math.sin(colint) * 255)),Math.abs((int)(Math.sin(colint) * 255))));
// 			else
// 			g.setColor(new Color((int)Math.abs((Math.sin(colint) * 255)),(int)Math.abs((Math.cos(colint) * 255)),Math.abs((int)(Math.cos(colint) * 255))));
			g.setColor(new Color((int)Math.abs((Math.cos(colint + (f * 60)) * 255)),(int)Math.abs((Math.sin(colint + (f * 60)) * 255)),Math.abs((int)(Math.sin(colint + (f * 60)) * 255))));
			g.fillPolygon(xs, ys, 4);

 			g.fillPolygon(xs, ys, 4);

			
		}
		g.setColor(Color.BLACK);
		for(int f = 0; f<coordx.length; f++)
		{

			g.drawLine((int)lx[f], (int)ly[f], (int)tempx[f], (int)tempy[f]);
			
		}			
		
	
	}
	
	public int getAngle()
	{
		return angle;
	}
	
}
	
