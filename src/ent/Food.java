package ent;

import javafx.scene.shape.Circle;

public class Food {
    private double x;
    private double y;
    private Circle circle;


    public Food( Circle circle) {
        this.x = circle.getLayoutX()-2.5;
        this.y = circle.getLayoutY()-2.5;
        this.circle = circle;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
