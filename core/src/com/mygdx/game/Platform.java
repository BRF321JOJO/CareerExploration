package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform extends Entity{

    Platform(SpriteBatch batch, int posx, int posy, int width, int height, int velx, int vely) {
        super(
                batch,
                new Texture("White.png"),
                posx,
                posy,
                width,
                height,
                velx,
                vely,
                0
        );
    }

    void update() {

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision (Entity e){
    }

}
