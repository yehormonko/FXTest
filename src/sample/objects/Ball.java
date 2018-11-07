package sample.objects;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.MultiplePressedKeysEventHandler;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Ball extends Obj{
private ArrayList<KeyCode> pressedCodes = new ArrayList<>();
private HashMap<KeyCode, Thread> move = new HashMap<>();
public Ball(Node node, Scene scene) {
	super(node, scene);
	
	scene.setOnKeyPressed(handler);
	scene.setOnKeyReleased(handler);
}

EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
	@Override
	public void handle(KeyEvent event) {
		KeyCode code = event.getCode();
		if(move.containsKey(code) && event.getEventType().equals(KeyEvent.KEY_RELEASED)){
			move.get(code).stop();
			move.remove(code);
		}
		else if(move.containsKey(code)) return;
		else if (code.equals(KeyCode.UP)&&event.getEventType().equals(KeyEvent.KEY_PRESSED)){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int a =1;
					while (true){
						System.out.println(move);
						moveUp();
						try {
							sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			move.put(code,thread);
		}
		else if (code.equals(KeyCode.DOWN)&&event.getEventType().equals(KeyEvent.KEY_PRESSED)){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int a =1;
					while (true){
						System.out.println(a++);
						moveDown();
						try {
							sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			move.put(code,thread);
		}
		else if (code.equals(KeyCode.LEFT)&&event.getEventType().equals(KeyEvent.KEY_PRESSED)){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int a =1;
					while (true){
						System.out.println(a++);
						moveLeft();
						try {
							sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			move.put(code,thread);
		}
		else if (code.equals(KeyCode.RIGHT)&&event.getEventType().equals(KeyEvent.KEY_PRESSED)){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int a =1;
					while (true){
						System.out.println(a++);
						moveRight();
						try {
							sleep(30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			move.put(code,thread);
		}
		
	}
};

final MultiplePressedKeysEventHandler keyHandler =
		new MultiplePressedKeysEventHandler(new MultiplePressedKeysEventHandler.MultiKeyEventHandler() {
			
			public void handle(MultiplePressedKeysEventHandler.MultiKeyEvent event) {
				if(event.isPressed(KeyCode.LEFT)) moveLeft();
				if(event.isPressed(KeyCode.RIGHT)) moveRight();
				if(event.isPressed(KeyCode.UP)) moveUp();
				if(event.isPressed(KeyCode.DOWN)) moveDown();
			}
		});
}
