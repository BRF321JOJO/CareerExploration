package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    SpriteBatch batch;

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT	= 1080;

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }
}

