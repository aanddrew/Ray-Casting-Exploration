import java.awt.Dimension;

import javax.swing.JFrame;

public class RayRunner 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("ray casting");
		frame.setSize(new Dimension(800, 800));
		
		frame.add(new Camera(frame, new Sphere()));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
