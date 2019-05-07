package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossHealth extends AbstractImage {

    static int constantwidth = 757;
    private static int constantheight = 203;

    public BossHealth(SpriteBatch batch) {
        super(
                batch,
                new Texture("Bosshealth.png"),
                MyGdxGame.SCREEN_WIDTH-constantwidth,
                MyGdxGame.SCREEN_HEIGHT-constantheight,
                constantwidth,
                constantheight,
                0,
                0
        );
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

}

