package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    private static final int SCREEN_WIDTH = 1920;
    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    private static final int SCREEN_HEIGHT	= 1080;
    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

