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

        if (ID != 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                posy += vely;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                posy -= vely;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            posx -= velx;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            posx += velx;
        }
    }


    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {
        //This makes it so that if the character is ever colliding with the platform
        //it's velocity becomes 0 so it doesn't fall through or build up negative velocity

        //Checking for collision happens after changing velocity
        //Therefore, chen pressing W, it will see the velocity is 15, and therefore allow it to jump
        //It will only apply this when the velocity decreases back down to 0
        if(vely < 0) {
            vely = 0;
        }

        RightRoom.jumppresses = 0;
    }



}
