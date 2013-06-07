import javax.swing.*;
import java.awt.*;

public class Driver03
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Mundane Hexagon");
		frame.setSize(600, 600);
		frame.setLocation(300,10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel03());
		frame.setVisible(true);
	}
}	
