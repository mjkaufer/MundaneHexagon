import java.awt.*;
import java.awt.image.BufferedImage;

public class Trap
{
	private int x, y, height, angle, dist, extra, px, py, bt, sect;
	private double[] coordx, coordy;
	private Color col;
	private Hexagon hex;

	
	public Trap(int x1, int y1, int ext, Color c, Hexagon h, int s)
	{
		height = 20;
		x = x1;
		y = y1;
		px = x;
		py = y;
		sect = s;
		col = c;
		dist = (int)(275 + (Math.random() * 100) + (Math.random() * 50) + (Math.random() * (sect * sect)) + 50 * (sect * 2));
		bt = 2;
		extra = ext - 60;
		
		
		
		hex = h;
		angle = angle;		
		coordx = new double[4];
		coordy = new double[4];

		

	}
	
	public void redist()
	{
		dist = (int)(275 + (Math.random() * 100) + (Math.random() * 50) + (Math.random() * (sect * sect)) + 50 * (sect * 2));
	
	}
	
	public void shrinkDist(int a)
	{
	dist -= a;
	}
	
	public void drawTrap(Graphics g, double[] xa, double[] ya, Color c)
	{
		
		g.setColor(c);
		int[] tempx = new int[4];
		int[] tempy = new int[4];
		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] = (int)xa[f];
			tempy[f] = (int)ya[f];
		
		}		
		g.fillPolygon(tempx, tempy, 4);
		
		
	}
	
	
	
	public double[] getX()
	{
		return coordx;
	}
	public double[] getY()
	{
		return coordy;
	}
	public int getSector()
	{
		return sect;
	}
	public int getD()
	{
		return dist;
	}
	public int getHeight()
	{
		return height;
	}		
	public void rotate(int angledegrees, Graphics g)
	{
		if(dist < 10)
		dist = (int)(Math.random() * 500 + 250);
		int dt = dist;
		angle = -1 * angledegrees + extra;
		int a = angle;
		double[] tempx = new double[4];
		double[] tempy = new double[4];
		double mx = 0;
		double my = 0;
 		for(int f = 0; f<2; f++)
		{
			tempx[f] =(x + dist * Math.sin((60 * f + 30 + angle) * Math.PI / 180));
			mx += tempx[f];
   		tempy[f] =(y + dist * Math.cos((60 * f + 30 + angle) * Math.PI / 180));
			my += tempy[f];
		}
		//midpoints
		mx /= 2.0;
		my /= 2.0;
		
		dist += height;
 		for(int f = 2; f<tempx.length; f++)
		{
			int q = f - 2;
			int l = (f + 1) % 2 + 2;
			tempx[l] =(x + (dist + 10) * Math.sin((60 * q + 30 + angle) * Math.PI / 180));
   		tempy[l] =(y + (dist + 10) * Math.cos((60 * q + 30 + angle) * Math.PI / 180));
		}		

		
		
		
			

		dist = dt;
			
						
		drawTrap(g, tempx, tempy, col);

	
	}
	
	public int getAngle()
	{
		return angle + extra;
	}
	
}
	
