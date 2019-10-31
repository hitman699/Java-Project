package test;

import animation.Scene;
import animation.SceneObject;
import animation.BBox;
import java.util.*;
public class TestScene extends Scene
{

    protected void checkScene()
    {
	    

        ArrayList<SceneObject>actorsArray    = getActors();
        ArrayList<SceneObject>obstaclesArray = getObstacles();
        ArrayList<String>returnDeletedObject = new ArrayList<String>();

        for(int i=0;i<actorsArray.size();i++)
        {
	    int flag=0;
            SceneObject actorFromArray=actorsArray.get(i);
            BBox forActor=actorFromArray.getBBox();
            for(int j=0;j<actorsArray.size();j++)
            {
                SceneObject otherActor=actorsArray.get(j);
                if(j!=i)
                {
                    if(forActor.intersects(otherActor.getBBox())==true)
                    {
			    flag=1;
			    returnDeletedObject.add(actorFromArray.getObjName());
                            returnDeletedObject.add(otherActor.getObjName());
                            break;  //No need to check obstaclesArray
                    }
                }
            }


	    if(flag==0)
	    {
	    	for(int j=0;j<obstaclesArray.size();j++)
	    	{
	    		SceneObject obstacles=obstaclesArray.get(j);
			if(forActor.intersects(obstacles.getBBox())==true)
			{
				returnDeletedObject.add(actorFromArray.getObjName());
				break;
			}
	    	}
	    }
	}

        //Print Out details of DeletedArray
        if(returnDeletedObject.size()>0)
        {
		for(int i=0;i<returnDeletedObject.size();i++)
		{
            		String name=returnDeletedObject.get(i);
            		Iterator<SceneObject>iter=actorsArray.iterator();
            		while(iter.hasNext())
            		{
                		SceneObject inArrayActor=iter.next();
                		if(inArrayActor.getObjName().equals(name))
                		{
                    			iter.remove();
                    			System.out.println("Actor deleted: "+name);
                    			break;
                		}

            		}
		}
        }


    }    
}


