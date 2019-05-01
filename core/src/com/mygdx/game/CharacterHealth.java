package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterHealth extends AbstractImage {

    private static int constantheight = 100;

    CharacterHealth(SpriteBatch batch){
        super(
            batch,
            new Texture("CharacterHealthtransparent.png"),
            0,
            MyGdxGame.SCREEN_HEIGHT - constantheight,
            MyGdxGame.SCREEN_WIDTH/2,
            constantheight,
            0,
            0
        );
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
}
