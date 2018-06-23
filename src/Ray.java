import java.awt.Point;

public class Ray 
{
	public static final double RAY_LENGTH = 100;
	public static final double RAY_INCREMENT= 1;
	
	private double yAngle;
	private double vertAngle;
	
	private Camera camera;
	private Sphere sphere;
	private Light light;
	
	private Point3D[] points;
	private Point3D lightPoint;
	
	private Point screenLoc;
	
	private boolean active;
	
	public Ray(Camera cameraIn, Sphere sphereIn, Point screenLocIn, Light lightIn)
	{
		camera = cameraIn;
		sphere = sphereIn;
		screenLoc = screenLocIn;
		light = lightIn;
		
		yAngle = camera.getYAngle() + ((screenLoc.getY()-camera.getCenter().getY())/camera.getHeight())*camera.getFOV();
		vertAngle = camera.getVertAngle() + ((screenLoc.getX()-camera.getCenter().getX())/camera.getWidth())*camera.getFOV();
		
		points = new Point3D[(int)(RAY_LENGTH/RAY_INCREMENT)];
		
		active = false;
		
		double xCoef = Math.cos(yAngle);
		double yCoef = Math.sin(vertAngle);
		double zCoef = Math.sin(yAngle);
		
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point3D(camera.getPoint3D().getX() + xCoef*i*RAY_INCREMENT, 
									camera.getPoint3D().getY() + yCoef*i*RAY_INCREMENT,
									camera.getPoint3D().getZ() + zCoef*i*RAY_INCREMENT);
			System.out.print(screenLocIn.getX() + ", " + screenLocIn.getY() + ", " + i + "/" + points.length + ", ");
			if (sphere.contains(points[i]) && !active)
			{
				lightPoint = points[i];
				active = true;
			}
			System.out.print(active);
			System.out.println();
		}
	}

	public int getAlpha()
	{

		if (active) {
			double alpha = 255-Math.pow(lightPoint.getDist(light.getPoint()),2) * 0.0550;
			if (alpha > 255) alpha = 255;
			else if (alpha < 0) alpha = 0;
			System.out.println(alpha);
			return (int) (alpha);
		}
		return 0;
	}
}
