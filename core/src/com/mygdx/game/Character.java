package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character extends AbstractImage{

    static private int characterwidth = 100;
    static private int characterheight = 100;

    Character (SpriteBatch batch) {
        super(
                batch,
                new Texture("SquareGuy.png"),
                MyGdxGame.getScreenWidth()/2 - characterheight/2,
                MyGdxGame.getScreenHeight()/2 - characterheight/2,
                characterwidth,
                characterheight,
                1,
                1
        );
    }

    void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            posy += vely;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            posx -= velx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            posy -= vely;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            posy += velx;
        }
    }

    void render() {batch.draw(texture, posx, posy, width, height);}
}
