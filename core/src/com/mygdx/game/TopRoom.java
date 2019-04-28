package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class TopRoom implements Screen {
    static MyGdxGame game;

    private Character character;

    TopRoom (MyGdxGame game) {
        this.game = game;
        character = new Character(game.batch,
                GameScreen.savedposx,
                100,
                75,
                75,
                5,
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

        //This is the loading zone for the top room
        if (character.posx >= MyGdxGame.SCREEN_WIDTH/2 - 50
                && character.posx <= MyGdxGame.SCREEN_WIDTH/2 + 50
                && character.posy <= 100) {
            GameScreen.savedposx = character.posx;
            GameScreen.savedposy = MyGdxGame.SCREEN_HEIGHT - 100;
            GameScreen.savedID = character.ID;
            TopRoom.game.setScreen(new GameScreen(TopRoom.game));
        }


        for (Entity e : Entity.entities) {
            //Checks collision for player specifically
            if (character.isCollide(e)) {
                //Says all handling denoted within respective class
                character.handleCollision(e);
                e.handleCollision(character);
            }

            //Can add more here

        }

    }
}
