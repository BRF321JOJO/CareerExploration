package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RedHealth extends AbstractImage {

    private static int constantheight = 100;

    RedHealth(SpriteBatch batch){
        super(batch,
                new Texture("Health.png"),
                0,
                MyGdxGame.SCREEN_HEIGHT - constantheight,
                MyGdxGame.SCREEN_WIDTH/2-40,
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
