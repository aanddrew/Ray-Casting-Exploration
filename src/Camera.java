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
	private Light light;
	
	private boolean loading;
	private int renderingRow;
	
	public Camera(JFrame frameIn, Sphere sphereIn, Light lightIn)
	{
		frame = frameIn;
		sphere = sphereIn;
		light = lightIn;
		
		point = new Point3D(0,0,-50);
		yAngle = Math.PI/2;
		FOV = Math.PI/2;
		
		this.setSize(frame.getSize());
		
		rays = new Ray[this.getHeight()][this.getWidth()];
		pixels = new Pixel[rays.length][rays[0].length];
		
		loading = true;
		renderingRow = 0;
	}
	
	public void renderSphere()
	{
		for (int row = 0; row < rays.length; row++)
		{
			for (int col = 0; col < rays[row].length; col++)
			{
				rays[row][col] = new Ray(this, sphere, new Point(col, row), light);
			}
			renderingRow = row;
		}

		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[row].length; col++)
			{
				pixels[row][col] = new Pixel(new Color(rays[row][col].getAlpha(),rays[row][col].getAlpha(),rays[row][col].getAlpha()));
			}
		}
		System.out.println("LOADING DONE");
		loading = false;
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
		
		if (loading)
		{
			g2d.setColor(Color.BLACK);
			g2d.fillRect((int)(this.getCenter().getX()-150), (int)(this.getCenter().getY()-20), 300, 40);
			g2d.setColor(Color.GREEN);
			g2d.fillRect((int)(this.getCenter().getX()-145), (int)(this.getCenter().getY()-18), 290*(renderingRow/rays.length), 16);
		}
		else
		{
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
}
