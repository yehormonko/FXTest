package controllers;

import ent.Ant;

public abstract class Controller {
    public Controller(Ant ant){
        this.ant=ant;
    }
    private Ant ant;
    public abstract int tick();
}
