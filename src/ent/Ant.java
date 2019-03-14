package ent;

import javafx.scene.shape.Rectangle;
import sample.Main;

public class Ant {
    private Rectangle body;
    private boolean loaded = false;
    public Ant(Rectangle rectangle){
        this.body=rectangle;
        xHome = body.getX();
        yHome = body.getY();
    }
    public void moveLeft(){
        body.setX(body.getX()-5);
    }
    public void moveRight(){
        body.setX(body.getX()+5);
    }
    public void moveDown(){
        body.setY(body.getY()+5);
    }
    public  void moveUp(){
        body.setY(body.getY()-5);
    }
    private double xHome;
    private double yHome;
    public void catchFoodUp(){
        if(!loaded){
            Main.catchFood(body.getX(), body.getY()-5);
        }
    }
    public boolean isAtHome(){
        if(body.getX()==xHome&&body.getY()==yHome){
            return true;
        }
        return false;
    }
    public boolean checkFoodUp(){
        if(Main.isFoodThere(body.getX(), body.getY()-5)) return true;
        return false;
    }
    public boolean checkFoodDown(){
        if(Main.isFoodThere(body.getX(), body.getY()+5)) return true;
        return false;
    }
    public boolean checkFoodLeft(){
        if(Main.isFoodThere(body.getX()-5, body.getY())) return true;
        return false;
    }
    public boolean checkFoodRight(){
        if(Main.isFoodThere(body.getX()+5, body.getY())) return true;
        return false;
    }
}
