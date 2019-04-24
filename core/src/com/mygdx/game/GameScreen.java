package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    private Viewport viewport;

    Character character;

    public GameScreen(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);
        viewport = new ExtendViewport(MyGdxGame.getScreenWidth(), MyGdxGame.getScreenHeight(), camera);

        character = new Character(game.batch);
    }

    @Override
    public void render(float delta){
//This runs update method at bottom of class
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        character.render();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void show(){
        //start when screen is shown
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void update() {

        character.update();
    }
 }
