package ent;

import controllers.Controller;
import controllers.TestController;
import ent.Ant;
import ent.Food;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main extends Application {
    private Rectangle rectangle = new Rectangle(5, 5);
    private static  Group group = new Group();
    private static List<Food> foods= Collections.synchronizedList(new ArrayList<Food>());
    public static boolean isFoodThere(double x, double y){
        if(foods.isEmpty()) return false;
        for (Food food:foods) {
            if(food.getX()==x&&food.getY()==y){
                return true;
            }
        }
        return false;
    }
    public static void catchFood(double x, double y){
                try {


              if(isFoodThere(x, y)){
                  if(!foods.isEmpty())
                      for (Food food:foods) {
                          if(food.getY()==y&& food.getX()==x){
                             foods.remove(food);
                             Platform.runLater(()->{group.getChildren().remove(food.getCircle());});

                          }
                      }
              }
                }catch (Exception e){
                }

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        generateFood();
        drawLines(group);
        //Ant ant =generateAnt();
      //  background(ant);
        game();
        final Scene scene = new Scene(group, 1400, 800, Color.rgb(208, 236, 178));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setScene(scene);
        primaryStage.toFront();

    }
    public void background(Ant ant) throws InterruptedException {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        for (int i=0;i<100; i++){
                            System.out.println(ant.isOnBorder());
                            ant.moveDown();
                            ant.endTurn();
                        }
                        for (int i=0;i<100; i++){
                            ant.moveUp();
                            ant.endTurn();
                        }
                        System.out.println(ant.isAtHome());
//                        System.out.println(foods.get(0).getX()+" "+foods.get(0).getY());
//                        System.out.println(ant.checkFoodLeft());
//                        System.out.println(ant.checkFoodRight());
//                        System.out.println(ant.checkFoodUp());
//                        System.out.println(ant.checkFoodDown());
//                        ant.catchFoodUp();
                    return null;
                     }

                    };

            }
        };
        service.start();
    }
    private void game(){
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {


                        Label label = new Label();
                        label.setLayoutX(1300);
                        label.setLayoutY(400);
                        label.setText("0");
                        group.getChildren().add(label);
                        Ant ant = generateAnt();
                        Controller controller  = new TestController(ant);
                        while (!foods.isEmpty()){
                            int a = controller.tick();
                            Platform.runLater(()->{label.setText(a+"");});
                            ant.endTurn();
                        }











                        return null;
                    }

                };

            }
        };
        service.start();
    }
    private Ant generateAnt(){
        Random rand = new Random();
        int pre =  rand.nextInt(240*5);
        int mod = 5-pre%5;
        int x =pre+mod;
        pre = rand.nextInt(160*5);
        mod = 5-pre%5;
        int y = pre+mod;
        Rectangle ant = new Rectangle(5, 5);
        ant.setStroke(Color.BLACK);
        ant.setX(x);
        ant.setY(y);
        Rectangle home = new Rectangle(5,5);
        home.setStroke(Color.RED);
        home.setX(x);
        home.setY(y);
        home.toFront();
        ant.toFront();
        group.getChildren().add(ant);
        group.getChildren().add(home);
        return new Ant(ant);
    }
    private void generateFood(){
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int pre =  rand.nextInt(240*5);
            int mod = 5-pre%5;
            int x =pre+mod;
            pre = rand.nextInt(160*5);
            mod = 5-pre%5;
            int y = pre+mod;
            if(isFoodThere(x, y)){
                i--;
            }else {
                Circle circle = new Circle(2.5);
                circle.setLayoutX(x + 2.5);
                circle.setLayoutY(y + 2.5);
                circle.setStroke(Color.GREENYELLOW);
                group.getChildren().add(circle);
                foods.add(new Food(circle));
            }
        }
    }
    public void drawLines(Group group){
        int linesx = 0;
        for(int i = 0; i<1200; i+=5){
        Line line = new Line();
        line.setStroke(Color.rgb(74, 115, 30));
        line.setStartX(i);
        line.setEndX(i);
        line.setStartY(0);
        line.setEndY(800);
        group.getChildren().add(line);
        linesx++;
        }
        System.out.println(linesx);
        int linesy = 0;
        for(int i = 0; i<1200; i+=5){
            Line line = new Line();
            line.setStartY(i);
            line.setStroke(Color.rgb(74, 115, 30));
            line.setEndY(i);
            line.setStartX(0);
            line.setEndX(1200);
            group.getChildren().add(line);
            linesy++;
        }
        System.out.println(linesy);
    }


    public static void main(String[] args) {

            launch(args);


    }
}