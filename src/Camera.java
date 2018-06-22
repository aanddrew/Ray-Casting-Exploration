import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Camera extends JComponent 
{
	private Point3D point;
	private JFrame frame;
	
	private double FOV;
	private double yAngle;
	private double vertAngle;
	private Ray[][] rays;
	
	private Pixel[][] pixels;
	
	private Sphere sphere;
	
	public Camera(JFrame frameIn, Sphere sphereIn)
	{
		frame = frameIn;
		sphere = sphereIn;
		
		point = new Point3D(0,0,-50);
		yAngle = Math.PI/2;
		FOV = Math.PI/2;
		
		this.setSize(frame.getSize());
		
		rays = new Ray[this.getHeight()][this.getWidth()];
		
		for (int row = 0; row < rays.length; row++)
		{
			for (int col = 0; col < rays[row].length; col++)
			{
				rays[row][col] = new Ray(this, sphere, new Point(col, row));
			}
		}
		
		pixels = new Pixel[rays.length][rays[0].length];
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[row].length; col++)
			{
//				pixels[row][col] = new Pixel(new Color(row%255, col%255, 255));
				pixels[row][col] = new Pixel(new Color(rays[row][col].getAlpha(),rays[row][col].getAlpha(),rays[row][col].getAlpha()));
			}
		}
	}
	
	public Point getCenter()
	{
		return new Point(this.getWidth()/2, this.getHeight()/2);
	}
	
	public Point3D getPoint3D() {return point;}
	public double getYAngle() {return yAngle;}
	public double getVertAngle() {return vertAngle;}
	public double getFOV() {return FOV;}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		for (int row = 0; row <pixels.length; row++)
		{
			for (int col = 0; col < pixels[row].length; col++)
			{
				g2d.setColor(pixels[row][col].getColor());
				g2d.fillRect(row, col, 1, 1);
			}
		}
	}
}
