package sample.objects;

import javafx.scene.Node;
import javafx.scene.Scene;

public abstract class Obj {
	protected Node node;
	private Scene scene;
	private double x;
	private double y;
	public Obj(Node node, Scene scene) {
		this.node=node;
		this.x=node.getLayoutX();
		this.y=node.getLayoutY();
		this.scene=scene;
}

	public void moveLeft(){
		node.setLayoutX(x-10);
		x=x-10;
	}

	public void moveRight(){
		node.setLayoutX(x+10);
		x=x+10;
	}
	public void moveDown(){
		node.setLayoutY(y+10);
		y=y+10;
	}
	public void moveUp(){
		node.setLayoutY(y-10);
		y=y-10;
	}
}
