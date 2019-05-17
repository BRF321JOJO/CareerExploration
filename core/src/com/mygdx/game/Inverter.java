package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Inverter extends Entity {

    boolean invertscreen;

    public Inverter(SpriteBatch batch){
        super(
                batch,
                new Texture("Inverttrans.png"),
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );
    }


    public void update(){

    }


    @Override
    public void render() {

    }

    @Override
    public void handleCollision(Entity e) {
        if(invertscreen){
            invertscreen = false;
        } else{
            invertscreen = true;
        }
    }
}
