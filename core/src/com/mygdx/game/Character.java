package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character extends Entity{

    static int characterwidth = 75;
    static int characterheight = 75;

    Character(SpriteBatch batch, int posx, int posy, int width, int height, int velx, int vely, int ID) {
        super(
                batch,
                new Texture("SquareGuy.png"),
                posx,
                posy,
                width,
                height,
                velx,
                vely,
                ID
        );
    }

    void update() {

        //This only works if the player isn't in rightroom
        if (ID != 2){
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
                posx += velx;
            }
        }
    }


    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {

    }



}
