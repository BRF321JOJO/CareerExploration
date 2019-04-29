package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinGame extends AbstractImage{

    private static int constantwidth = 1920;
    private static int constantheight = 1080;

    WinGame(SpriteBatch batch){
        super(
                batch, new Texture("youwin.png"),
                MyGdxGame.SCREEN_WIDTH/2 - constantwidth/2,
                MyGdxGame.SCREEN_HEIGHT/2 - constantheight/2,
                constantwidth,
                constantheight,
                0,
                2
        );
    }


    public void update(){

    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
}
