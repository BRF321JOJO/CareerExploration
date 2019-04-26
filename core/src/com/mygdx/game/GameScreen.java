package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MainRoomBackground mainRoomBackground;
    private Character character;

    //This starts at this point and is later dependent upon the saved position
    static int savedposx;
    static int savedposy;

    static int savedID;


    void checkposition () {
        //This line of code makes it so that the game only sets this position when the game starts
        if(savedID == 0) {
            savedposx = MyGdxGame.SCREEN_WIDTH / 2 - Character.characterwidth / 2;
            savedposy = MyGdxGame.SCREEN_HEIGHT / 2 - Character.characterheight / 2;
        }
    }



    GameScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);

        mainRoomBackground = new MainRoomBackground(game.batch);

        checkposition();

        character = new Character(game.batch,
                    savedposx,
                    savedposy,
                    75,
                    75,
                    5,
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
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        mainRoomBackground.render();
        character.render();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        //mainRoomBackground.update();

        character.update();

        if (character.posx >= 600 && character.posx <= 1000 && character.posy >= MyGdxGame.SCREEN_HEIGHT-100) {
            savedposx = character.posx;
            GameScreen.game.setScreen(new TopRoom(GameScreen.game));
        }


        if (character.posy >= 600 && character.posy <= 1000 && character.posx >= MyGdxGame.SCREEN_WIDTH-100) {
            savedposy = character.posy;
            GameScreen.game.setScreen(new RightRoom(GameScreen.game));
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
