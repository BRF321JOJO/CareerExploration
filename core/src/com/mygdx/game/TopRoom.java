package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class TopRoom implements Screen {
    static MyGdxGame game;

    private Character character;

    static int savedxpos;

    TopRoom (MyGdxGame game) {
        this.game = game;
        character = new Character(game.batch,
                //GameScreen.character.posx,
                GameScreen.savedxpos,
                150,
                5,
                1
        );
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        character.render();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

    private void update(){
        character.update();

        if (character.posx >= 600 && character.posx <= 1000 && character.posy <= 0) {
            savedxpos = character.posx;
            TopRoom.game.setScreen(new GameScreen(TopRoom.game));
        }
    }
}
