package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    static MyGdxGame game;
    //private OrthographicCamera camera;
    //private Viewport viewport;

    private Character character;

    static int savedxpos;


    private int savedposx[];



    GameScreen(MyGdxGame game) {
        this.game = game;
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, MyGdxGame.getScreenWidth(), MyGdxGame.getScreenHeight());
        //viewport = new ExtendViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);

        //This is a list of x and y positions I will save when the character moves back into the central room
        savedposx[1] = 0;


        character = new Character(game.batch,
                    MyGdxGame.SCREEN_WIDTH / 2 - Character.characterwidth / 2,
                    MyGdxGame.SCREEN_HEIGHT / 2 - Character.characterheight / 2,
                    5,
                    0
            );
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

//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

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

        //Old method code
//        if(inloadingZone(600,1000,MyGdxGame.SCREEN_HEIGHT-100,MyGdxGame.SCREEN_HEIGHT)) {
//            savedxpos = character.posx;
//            GameScreen.game.setScreen(new TopRoom(GameScreen.game));
//        }

        if (character.posx >= 600 && character.posx <= 1000 && character.posy >= MyGdxGame.SCREEN_HEIGHT-100) {
            savedxpos = character.posx;
            savedposx[1] = character.posx;
            GameScreen.game.setScreen(new TopRoom(GameScreen.game));
        }



    }
}
