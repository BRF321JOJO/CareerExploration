package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    public Texture texture;
    public SpriteBatch batch;
    public int posx;
    public int posy;
    public int width;
    public int height;
    public int velx;
    public int vely;

    Entity(SpriteBatch batch, Texture texture, int posx, int posy, int width, int height, int velx, int vely){
        this.batch = batch;
        this.texture = texture;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.velx = velx;
        this.vely = vely;
    }

    public boolean isCollide (Entity e) {
        if (
                posx < e.posx + e.width     &&
                        posx + width > e.posx       &&
                        posy < e.posy + e.height    &&
                        height + posy > e.posy
        )
        {
            return true;
        } else {
            return false;
        }
    }

    public abstract void render();
    public abstract void handleCollision(Entity e);
}
