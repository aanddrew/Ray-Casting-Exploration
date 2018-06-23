import java.awt.Dimension;

import javax.swing.JFrame;

public class RayRunner 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("ray casting");
		frame.setSize(new Dimension(800, 800));
		
		frame.setVisible(true);
		
		Camera cam = new Camera(frame, new Sphere(), new Light(new Point3D(-20,50,-50)));
		frame.add(cam);
		
		cam.renderSphere();
		
		frame.revalidate();
		frame.repaint();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}