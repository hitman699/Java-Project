//import animation.Scene;
//import animation.SceneObject;
//import animation.View;
import imt2018511.*;
import animation.*;
import test.*;
import test2.*;
import Nipun.*;
public class team {

	public static void main(String[] args) {
		Scene scene = new test.TestScene(); // replace with your implementation

		// populate the scene with objects implemented by the team
		for (int i = 1; i < 7; i++) {
			SceneObject s = new test.TestObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			//s.setPosition(0,50);
			scene.addObstacle(s); // using appropriate derived classes.
		}
		for(int i = 0;i<2;i++){
			SceneObject s = new Nipun.Diamond(); // replace with your implementation
			s.setPosition(300 - i * 50, 100 + i * 50); // these will be changed for the demo
			//s.setPosition(0,100);
			s.setDestPosition(i,i);
			scene.addActor(s);
		}
		for (int i = 0; i < 2; i++) 
		{
			SceneObject s = new test.TestObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			//s.setPosition(0,100);
			s.setDestPosition(i+2,i+2);
			scene.addActor(s); // using appropriate derived classes
		}

		//View view = new DemoTextView();
		View view = new DemoSwingView();

		scene.setView(view);

		view.init();

	}

}
