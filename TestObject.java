package test;

import java.util.*;
import animation.BBox;
import animation.Point;
import animation.SceneObject;
import animation.Scene;

public class TestObject extends SceneObject //implements BBox
{

        private static int id=0;
        private int objectId;
        private Point presentPosition,destPosition;
        public ArrayList<Point>pointCollection;
        private Point minPoint;
        private Point maxPoint;
        private double speed;

        public TestObject()
        {
                this.speed=10.0;
                this.objectId=TestObject.id;
                TestObject.id++;
                this.presentPosition=new Point(0,0);
                this.destPosition=new Point(0,0);
		this.minPoint=new Point(0,0);
		this.maxPoint=new Point(10,10);


                this.pointCollection = new ArrayList<Point>();
                this.pointCollection.add(new Point(0,0));
                this.pointCollection.add(new Point(10,0));
                this.pointCollection.add(new Point(10,10));
                this.pointCollection.add(new Point(0,10));
        }
        @Override
        public String getObjName()
        {
                return "Object: " + this.objectId;
        }

        @Override
        public Point getPosition() {
                return this.presentPosition;
        }

        @Override
        public void setPosition(int x, int y)
        {
		this.minPoint=new Point(x,y);
		this.maxPoint=new Point(x+10,y+10);

                this.presentPosition.setPos(x,y);
                this.pointCollection.clear();
                this.pointCollection.add(this.presentPosition);
                this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()));
                this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
                this.pointCollection.add(new Point(this.presentPosition.getX(),this.presentPosition.getY()+10));
        }

        public void setDestPosition(int x, int y)
        {
                this.destPosition.setPos(x,y);
        }

        @Override
        public BBox getBBox()
        {
		/*
		BBox temp=this;
		return temp;*/
		return new TestBBox(new Point(this.presentPosition),new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
        }

        @Override
        protected ArrayList<Point> getOutline()
        {
                return this.pointCollection;
        }

	@Override
	protected void updatePos(int deltaT)
	{
		int ds = deltaT/100*2;
		int chg_x=0;
		int chg_y=0;

		if(this.destPosition.getX()!=this.presentPosition.getX())
		{
			double tanO = Math.abs((this.destPosition.getY()-this.presentPosition.getY())/(this.destPosition.getX()-this.presentPosition.getX()));
		 	//double tan2O = tanO*tanO;
		 	//int chg_x,chg_y;
		 	chg_x=(int)(ds/Math.sqrt(1+tanO));
		 	chg_y=(int)(chg_x*tanO);

		 	if(this.presentPosition.getX()<this.destPosition.getX())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX()+chg_x,this.presentPosition.getY());
		 		if(this.presentPosition.getX()>this.destPosition.getX())
				{
					this.presentPosition.setPos(this.destPosition.getX(),this.presentPosition.getY()); 
				}
		 	}
		 	else if(this.presentPosition.getX()>this.destPosition.getX())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX()-chg_x,this.presentPosition.getY());
		 		if(this.presentPosition.getX()<this.destPosition.getX())
				{
					this.presentPosition.setPos(this.destPosition.getX(),this.presentPosition.getY()); 
				}
		 	}

		 	if(this.presentPosition.getY()<this.destPosition.getY())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX(),this.presentPosition.getY()+chg_y);
		 		if(this.presentPosition.getY()>this.destPosition.getY())
				{
					this.presentPosition.setPos(this.presentPosition.getX(),this.destPosition.getY()); 
				}
		 	}
		 	else if(this.presentPosition.getY()>this.destPosition.getY())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX(),this.presentPosition.getY()-chg_y);
		 		if(this.presentPosition.getY()<this.destPosition.getY())
				{
					this.presentPosition.setPos(this.presentPosition.getX(),this.destPosition.getY()); 
				}
		 	}
			this.minPoint=this.presentPosition;
			this.maxPoint=new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10);

			this.pointCollection.clear();
			this.pointCollection.add(presentPosition);
			this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()));
                	this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
                	this.pointCollection.add(new Point(this.presentPosition.getX(),this.presentPosition.getY()+10));

		}
		else
		{
		 	if(this.presentPosition.getY()<this.destPosition.getY())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX(),this.presentPosition.getY()+ds);
		 		if(this.presentPosition.getY()>this.destPosition.getY())
				{
					this.presentPosition.setPos(this.presentPosition.getX(),this.destPosition.getY()); 
				}
		 	}
		 	else if(this.presentPosition.getY()>this.destPosition.getY())
			{ 
		 		this.presentPosition.setPos(this.presentPosition.getX(),this.presentPosition.getY()-ds);
		 		if(this.presentPosition.getY()<this.destPosition.getY())
				{
					this.presentPosition.setPos(this.presentPosition.getX(),this.destPosition.getY()); 
				}
		 	}
			this.minPoint=this.presentPosition;
			this.maxPoint=new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10);

			this.pointCollection.clear();
			this.pointCollection.add(presentPosition);
			this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()));
            		this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
            		this.pointCollection.add(new Point(this.presentPosition.getX(),this.presentPosition.getY()+10));

		}
		//Checking Actor-Actor collision

		Scene s1=Scene.getScene();
	    	ArrayList<SceneObject>actorsArray    = s1.getActors();
        	ArrayList<SceneObject>obstaclesArray = s1.getObstacles();
        
		
        for(int i=0;i<actorsArray.size();i++)
        {
			BBox originalActor=this.getBBox();
			SceneObject actorFromArray=actorsArray.get(i);
			if(actorFromArray!=this)
			{
				if(originalActor.intersects(actorFromArray.getBBox())==true)
				{
					this.presentPosition.setPos(this.presentPosition.getX()-3*chg_x,this.presentPosition.getY());
				//	actorFromArray.presentPosition.setPos(actorFromArray.presentPosition.getX()+2*chg_x,actorFromArray.presentPosition.getY()+2*chg_y);
				}
			}
			
			this.minPoint=this.presentPosition;
			this.maxPoint=new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10);

			this.pointCollection.clear();
			this.pointCollection.add(presentPosition);
			this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()));
            		this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
            		this.pointCollection.add(new Point(this.presentPosition.getX(),this.presentPosition.getY()+10));
			
		/*	
			actorFromArray.minPoint=actorFromArray.presentPosition;
			actorFromArray.maxPoint=new Point(actorFromArray.presentPosition.getX()+10,actorFromArray.presentPosition.getY()+10);

			actorFromArray.pointCollection.clear();
			actorFromArray.pointCollection.add(presentPosition);
			actorFromArray.pointCollection.add(new Point(actorFromArray.presentPosition.getX()+10,actorFromArray.presentPosition.getY()));
            		actorFromArray.pointCollection.add(new Point(actorFromArray.presentPosition.getX()+10,actorFromArray.presentPosition.getY()+10));
            		actorFromArray.pointCollection.add(new Point(actorFromArray.presentPosition.getX(),actorFromArray.presentPosition.getY()+10));
		*/	
	}
	for(int i=0;i<obstaclesArray.size();i++)
	{
		BBox originalActor=this.getBBox();
		SceneObject obstacle=obstaclesArray.get(i);
		if(originalActor.intersects(obstacle.getBBox())==true)
		{
			this.presentPosition.setPos(this.presentPosition.getX()-3*chg_x,this.presentPosition.getY());
		}
		this.minPoint=this.presentPosition;
                this.maxPoint=new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10);

                this.pointCollection.clear();
                this.pointCollection.add(presentPosition);
                this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()));
                this.pointCollection.add(new Point(this.presentPosition.getX()+10,this.presentPosition.getY()+10));
                this.pointCollection.add(new Point(this.presentPosition.getX(),this.presentPosition.getY()+10));
 
	}
	}


/*
 *
 //BBox Part
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
*/	
}




