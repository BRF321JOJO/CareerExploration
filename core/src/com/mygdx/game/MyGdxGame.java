package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    BitmapFont font;

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT	= 1080;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        //This sets the scale of the font
        font.getData().setScale(4f);

        this.setScreen(new TopRoom(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

