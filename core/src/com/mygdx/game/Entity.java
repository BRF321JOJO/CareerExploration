package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    Texture texture;
    SpriteBatch batch;
    int posx;
    int posy;
    int width;
    int height;
    int velx;
    int vely;
    int ID;

    Entity(SpriteBatch batch, Texture texture, int posx, int posy, int width, int height, int velx, int vely, int ID){
        this.batch = batch;
        this.texture = texture;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.velx = velx;
        this.vely = vely;
        this.ID = ID;
    }

    //This function returns true/false if collision is occuring
    public boolean isCollide (Entity e) {
        return  posx < e.posx + e.width &&
                posx + width > e.posx &&
                posy < e.posy + e.height    &&
                height + posy > e.posy;
    }

    public abstract void render();
    public abstract void handleCollision(Entity e);
}
