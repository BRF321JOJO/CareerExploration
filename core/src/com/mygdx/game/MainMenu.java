package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {

    private final MyGdxGame game;
    private OrthographicCamera camera;
    //private Viewport viewport;

    private MenuHUD menuHUD;

    MainMenu(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.getScreenWidth(), MyGdxGame.getScreenHeight());
        //viewport = new ExtendViewport(MyGdxGame.getScreenWidth(), MyGdxGame.getScreenHeight(), camera);

        menuHUD = new MenuHUD(game.batch);
    }

    @Override
    public void show() {
        //starts when screen shows
    }

    @Override
    public void render(float delta) {
        //This runs update method at bottom of class
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Renders HUD
        menuHUD.stage.draw();
        game.batch.begin();
        //game.font.draw(game.batch, "Welcome to Drop!", 100, 150);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
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

    public void update(){

        menuHUD.update();

        //Changes Screen if screen touched
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
}
