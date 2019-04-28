package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Table extends Entity {

    Table(SpriteBatch batch, int posx, int posy, int width, int height){
        super(batch,
                new Texture("White.png"),
                posx,
                posy,
                width,
                height,
                0,
                0,
                3
        );
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e){

    }
}
