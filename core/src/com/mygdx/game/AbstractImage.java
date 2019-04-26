package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractImage {
    Texture texture;
    SpriteBatch batch;
    int posx;
    int posy;
    int width;
    int height;
    int velx;
    int vely;

    AbstractImage(SpriteBatch batch, Texture texture, int posx, int posy, int width, int height, int velx, int vely){
        this.batch = batch;
        this.texture = texture;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.velx = velx;
        this.vely = vely;
    }

    public abstract void render();
}
