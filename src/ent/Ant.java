package ent;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class Ant {
    private Rectangle body;
    private boolean loaded = false;

    public int getHomeScore() {
        return homeScore;
    }

    private int homeScore = 0;
    private int movePoints = 4;
    protected void endTurn(){
        movePoints=4;
    }
    public Ant(Rectangle rectangle){
        this.body=rectangle;
        xHome = body.getX();
        yHome = body.getY();
    }
    public void moveLeft(){
        if(movePoints<2) return;
        movePoints-=2;
        if(body.getX()-5>0) return;
        body.setX(body.getX()-5);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnBorder(){
        if(body.getX()-5==0||body.getX()+5==1200||
                body.getY()+5==800||body.getY()-5==0) return true;
        return false;
    }
    public void moveRight(){
        if(movePoints<2) return;
        movePoints-=2;
        if(body.getX()+5>1200) return;
        body.setX(body.getX()+5);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void moveDown(){
        if(movePoints<2) return;
        movePoints-=2;
        if(body.getY()+5>800) return;
        body.setY(body.getY()+5);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isLoaded(){
        return loaded;
    }
    public  void moveUp(){
        if(movePoints<2) return;
        movePoints-=2;
        if(body.getY()-5<0) return;
        body.setY(body.getY()-5);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private double xHome;
    private double yHome;
    public void catchFoodUp(){
        if(!loaded){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.catchFood(body.getX(), body.getY()-5);
            loaded=true;
        }
        movePoints-=1;
    }
    public void putFood(){
        if(isAtHome()){
            homeScore++;
            loaded=false;
        }
        movePoints-=1;
    }
    public boolean isAtHome(){
        if(body.getX()==xHome&&body.getY()==yHome){
            return true;
        }
        return false;
    }
    public boolean checkFoodUp(){
        if(movePoints<1) return false;
        movePoints-=1;
        if(Main.isFoodThere(body.getX(), body.getY()-5)) return true;
        return false;
    }
    public boolean checkFoodDown(){
        if(movePoints<1) return false;
        movePoints-=1;
        if(Main.isFoodThere(body.getX(), body.getY()+5)) return true;
        return false;
    }
    public boolean checkFoodLeft(){
        if(movePoints<1) return false;
        movePoints-=1;
        if(Main.isFoodThere(body.getX()-5, body.getY())) return true;
        return false;
    }
    public boolean checkFoodRight(){
        if(movePoints<1) return false;
        movePoints-=1;
        if(Main.isFoodThere(body.getX()+5, body.getY())) return true;
        return false;
    }
}
