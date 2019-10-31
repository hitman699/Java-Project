package test;
import animation.*;

public class TestBBox implements BBox
{
	private Point minPoint,maxPoint;

	public TestBBox(Point p1,Point p2)
	{
		this.minPoint=p1;
		this.maxPoint=p2;
	}

public Point getMinPt() 
{
	return this.minPoint;
}

public Point getMaxPt() 
{
        return this.maxPoint;
}

    public boolean intersects(final BBox b)
    {
        if(this.minPoint.getX()>=b.getMinPt().getX() && this.minPoint.getX()<=b.getMaxPt().getX() && this.minPoint.getY()>=b.getMinPt().getY() && this.minPoint.getY()<=b.getMaxPt().getY())
                return true;
        if(this.maxPoint.getX()>=b.getMinPt().getX() && this.maxPoint.getX()<=b.getMaxPt().getX() && this.maxPoint.getY()>=b.getMinPt().getY() && this.maxPoint.getY()<=b.getMaxPt().getY())
                return true;
        if(this.minPoint.getX()+10>=b.getMinPt().getX() && this.minPoint.getX()+10<=b.getMaxPt().getX() && this.minPoint.getY()>=b.getMinPt().getY() && this.minPoint.getY()<=b.getMaxPt().getY())
                return true;
         if(this.minPoint.getX()>=b.getMinPt().getX() && this.minPoint.getX()<=b.getMaxPt().getX() && this.minPoint.getY()+10>=b.getMinPt().getY() && this.minPoint.getY()+10<=b.getMaxPt().getY())
                return true;
 

        return false;
    }
}
