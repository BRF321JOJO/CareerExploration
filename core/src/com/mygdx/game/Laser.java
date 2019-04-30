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

        //Stops laser movement
        if(posx >= MyGdxGame.SCREEN_WIDTH) {
            velx = 0;
        }
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
    public void handleCollision(Entity e){

    }
}
