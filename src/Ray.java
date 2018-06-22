import java.awt.Point;

public class Ray 
{
	public static final double RAY_LENGTH = 100;
	public static final double RAY_INCREMENT= 5;
	
	private double yAngle;
	private double vertAngle;
	
	private Camera camera;
	private Sphere sphere;
	
	private Point3D[] points;
	
	private Point screenLoc;
	
	private boolean active;
	
	public Ray(Camera cameraIn, Sphere sphereIn, Point screenLocIn)
	{
		camera = cameraIn;
		sphere = sphereIn;
		screenLoc = screenLocIn;
		
		yAngle = camera.getYAngle() + ((screenLoc.getY()-camera.getCenter().getY())/camera.getHeight())*camera.getFOV();
		vertAngle = camera.getVertAngle() + ((screenLoc.getX()-camera.getCenter().getX())/camera.getWidth())*camera.getFOV();
		
		points = new Point3D[(int)(RAY_LENGTH/RAY_INCREMENT)];
		
		active = false;
		
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point3D(camera.getPoint3D().getX() + Math.cos(yAngle)*i*RAY_INCREMENT, 
									camera.getPoint3D().getY() + Math.sin(vertAngle)*i*RAY_INCREMENT,
									camera.getPoint3D().getZ() + Math.sin(yAngle)*i*RAY_INCREMENT);
			System.out.print(screenLocIn.getX() + ", " + screenLocIn.getY() + ", " + i + "/" + points.length + ", ");
			if (sphere.contains(points[i]))
			{
				active = true;
			}
			System.out.print(active);
			System.out.println();
		}
	}

	public int getAlpha()
	{
		if (active) return 255;
		return 0;
	}
}
