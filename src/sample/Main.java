package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.objects.Ball;
import sample.objects.Obj;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Circle circle = new Circle(50);
        circle.setLayoutX(100);
        circle.setLayoutY(100);
        Group group = new Group(circle);
        Scene scene = new Scene(group, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        Ball ball = new Ball(circle, scene);
//        scene.setOnKeyPressed(event -> {
//            if(event.getCode()== KeyCode.LEFT) ball.moveLeft();
//            if(event.getCode()== KeyCode.RIGHT) ball.moveRight();
//            if(event.getCode()== KeyCode.UP) ball.moveUp();
//            if(event.getCode()== KeyCode.DOWN) ball.moveDown();
//        });
    
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
