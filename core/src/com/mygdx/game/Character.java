package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character extends Entity{

    static private int characterwidth = 75;
    static private int characterheight = 75;

    Character(SpriteBatch batch) {
        super(
                batch,
                new Texture("SquareGuy.png"),
                MyGdxGame.SCREEN_WIDTH/2 - characterwidth/2,
                MyGdxGame.SCREEN_HEIGHT/2 - characterheight/2,
                characterwidth,
                characterheight,
                5,
                5
        );
    }

    void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            posy += vely;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            posx -= velx;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            posy -= vely;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            posx += velx;
        }
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {

    }
}
