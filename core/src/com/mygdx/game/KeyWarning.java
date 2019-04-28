package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KeyWarning extends AbstractImage{

    KeyWarning(SpriteBatch batch) {
        super(
                batch,
                new Texture("keywarningtrans.png"),
                0,
                0,
                843,
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
