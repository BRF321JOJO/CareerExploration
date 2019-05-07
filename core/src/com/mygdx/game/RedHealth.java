package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RedHealth extends AbstractImage {

    private static int constantheight = 203;

    RedHealth(SpriteBatch batch, int posx, int width){
        super(batch,
                new Texture("Health.png"),
                posx,
                MyGdxGame.SCREEN_HEIGHT - constantheight,
                width,
                constantheight,
                0,
                0
        );
    }

    public void update(){

    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
}
