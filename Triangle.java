import java.awt.*;
import java.awt.image.BufferedImage;

public class Triangle
{
	private int x, y, rad, angle, dist, extra, px, py, bt;
	private double[] coordx, coordy;
	private Color col;
	private Hexagon hex;
	
	public Triangle(int x1, int y1, int r1, Color c, Hexagon h)
	{
		rad = r1;
		x = x1;
		y = y1;
		px = x;
		py = y;
		col = c;
		dist = 40;
		bt = 2;
		extra = 20;
		
		hex = h;
		angle = hex.getAngle();		
		coordx = new double[3];
		coordy = new double[3];
		for(int f = 0; f<coordx.length; f++)
		{
			coordx[f] =(x + rad * Math.sin((120 * f) * Math.PI / 180));
   		coordy[f] =(y + rad * Math.cos((120 * f) * Math.PI / 180));
		}		

		

	}
	
	public int getD()
	{
		return dist;
	}
	
	public int getSector()
	{
		return Math.abs(((extra + 390) % 360) / 60);
	}
	public int getExtra()
	{
		return extra;
	}
	
	public void incExtra(int q)
	{
		extra -= q;
	}
	public void setX(int a)
	{
	x = a;
	}
	
	public void setY(int b)
	{
	y = b;
	}
	public void drawTri(Graphics g, double[] xa, double[] ya, Color c)
	{
		
		g.setColor(c);
		int[] tempx = new int[3];
		int[] tempy = new int[3];
		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] = (int)xa[f];
			tempy[f] = (int)ya[f];
		
		}		
		g.fillPolygon(tempx, tempy, 3);
	}
	
	
	
	public double[] getX()
	{
		return coordx;
	}
	public double[] getY()
	{
		return coordy;
	}
	
	public void updateCoords(int ang)
	{
			x = (int)(px + dist * Math.sin((ang + extra) * Math.PI / 180));
   		y = (int)(py + dist * Math.cos((ang + extra) * Math.PI / 180));	
	}
	
	public void rotate(int angledegrees, Graphics g)
	{
		updateCoords(-1 * angledegrees);
		angle = -1 * angledegrees + extra;
		angle = angle % 360;
		extra = extra % 360;
		double[] tempx = new double[3];
		double[] tempy = new double[3];
		for(int f = 0; f<coordx.length; f++)
		{
			tempx[f] =(x + rad * Math.sin((120 * f + angle) * Math.PI / 180));
   		tempy[f] =(y + rad * Math.cos((120 * f + angle) * Math.PI / 180));
		}		
		//border
		double[] t2x = new double[3];
		double[] t2y = new double[3];
		
		for(int f = 0; f<coordx.length; f++)
		{
			t2x[f] =(x + (rad + bt) * Math.sin((120 * f + angle) * Math.PI / 180));
   		t2y[f] =(y + (rad + bt) * Math.cos((120 * f + angle) * Math.PI / 180));
		}	
		drawTri(g, t2x, t2y, Color.BLACK);				
		
		drawTri(g, tempx, tempy, col);

	
	}
	
	public int getAngle()
	{
		return angle + extra;
	}
	
}
	
