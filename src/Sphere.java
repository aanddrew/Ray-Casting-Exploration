
public class Sphere 
{
	Point3D center;
	double radius;
	public Sphere()
	{
		center = new Point3D(0,0,0);
		radius = 15.0;
	}
	
	public boolean contains(Point3D point)
	{
		return point.getDist(center) < radius;
	}
	
	public double getRadius() {return radius;}
}
