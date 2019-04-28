package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {

    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MenuHUD menuHUD;
    private MenuBackground menuBackground;

    //Music code
    private static Music MenuMusic;

    MainMenu(MyGdxGame game) {
        this.game = game;

        //Camera stuff supposedly makes it so that any quality render works
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);

        //Makes it so when the screen in resized, the screen won't be stretched but have black bars get fill the screen when possible
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH,MyGdxGame.SCREEN_HEIGHT, camera);

        menuHUD = new MenuHUD(game.batch);
        menuBackground = new MenuBackground(game.batch);

        MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Introduction.wav"));
    }

    @Override
    public void show() {
        //Insert music when game starts
        MenuMusic.play();
        MenuMusic.setLooping(true);
        MenuMusic.setVolume(0.4f);
    }

    @Override
    public void render(float delta) {
        //This runs update method at bottom of class
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Idk what this does still
//        camera.update();
        //Supposedly makes it so that any quality screen works
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        menuBackground.render();
        game.font.draw(game.batch, "Escape Room", MyGdxGame.SCREEN_WIDTH/2 - 350/2, MyGdxGame.SCREEN_HEIGHT-150);
        game.batch.end();
        //Renders HUD
        menuHUD.stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        //Updated the viewport according to the resizing
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        MenuMusic.dispose();
    }

    private void update(){
        menuHUD.update();
        //menuBackground.update();
    }
}
