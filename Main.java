import animation.Scene;
import animation.SceneObject;
import animation.View;
import imt2018511.*;
import imt2018063.*;
import Nipun.*;
import test.*;
import test2.*;

// Driver class to set up and exercise the animation
public class Main {

	public static void main(String[] args) {
		Scene scene = new test.TestScene(); // replace with your implementation

		// populate the scene with objects implemented by the team
		for (int i = 0; i < 2; i++) {
			SceneObject s = new Nipun.Diamond(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}
		for (int i = 2; i < 4; i++) {
			SceneObject s = new imt2018511.DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}
		for (int i = 0; i < 2; i++) {
			SceneObject s = new Nipun.Diamond(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}
		for (int i = 2; i < 4; i++) {
			SceneObject s = new imt2018511.DemoSceneObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}
		for (int i = 4; i < 6; i++) {
			SceneObject s = new test.TestObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}
		for (int i = 6; i < 8; i++) {
			SceneObject s = new test2.TestObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}
		
		for (int i = 8; i < 10; i++) {
			SceneObject s = new imt2018063.DemoSceneObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}
		// View view = new DemoTextView();
		View view = new DemoSwingView();

		scene.setView(view);

		view.init();

	}

}
