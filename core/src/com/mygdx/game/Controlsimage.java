package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controlsimage extends AbstractImage{

    private static int constantwidth = 300;
    private static int constantheight = 300;

    Controlsimage(SpriteBatch batch) {
        super(
                batch,
                new Texture(Gdx.files.internal("Controls.png")),
                MyGdxGame.SCREEN_WIDTH/2 - constantwidth/2,
                MyGdxGame.SCREEN_HEIGHT/2 - constantheight/2 + 50,
                constantwidth,
                constantheight,
                0,
                0
        );
    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }
}
