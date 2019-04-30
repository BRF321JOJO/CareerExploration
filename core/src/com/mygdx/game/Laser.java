package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Laser extends Entity {
    Laser(SpriteBatch batch, int posx, int posy, int velx, int vely) {
        super(batch,
                new Texture("laser.png"),
                posx,
                posy,
                75,
                25,
                velx,
                vely,
                0
        );

    }

    public void update(){
        posx += velx;

        //Unrenders laser and stops its movement
        if(posx >= MyGdxGame.SCREEN_WIDTH) {
            TopRoom.renderlaser = false;
        }
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
    public void handleCollision(Entity e){

    }
}
