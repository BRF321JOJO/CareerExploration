package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterHealth extends AbstractImage {

    private static int constantheight = 203;

    CharacterHealth(SpriteBatch batch){
        super(
            batch,
            new Texture("CharacterHealthImage.png"),
            0,
            MyGdxGame.SCREEN_HEIGHT - constantheight,
                757,
            constantheight,
            0,
            0
        );
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
}
