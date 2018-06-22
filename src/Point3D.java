
public class Point3D 
{
	double x, y, z;
	public Point3D()
	{
		x = 0; y = 0; z = 0;
	}
	
	public Point3D(double xIn, double yIn, double zIn)
	{
		x = xIn; y = yIn; z = zIn;
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getZ() {return z;}
	
	public double getDist(Point3D point) {return Math.sqrt(
			Math.pow(getX()-point.getX(), 2) +
			Math.pow(getY()-point.getY(), 2) +
			Math.pow(getZ()-point.getZ(), 2));
	}
}
