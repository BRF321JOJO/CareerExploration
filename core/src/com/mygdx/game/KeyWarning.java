package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KeyWarning extends AbstractImage{

    static int constantwidth = 843;

    KeyWarning(SpriteBatch batch) {
        super(
                batch,
                new Texture("keywarningtrans.png"),
                MyGdxGame.SCREEN_WIDTH/2 - constantwidth/2,
                MyGdxGame.SCREEN_HEIGHT - 250,
                constantwidth,
                63,
                0,
                0
        );
    }

    public void update(){

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }
}
