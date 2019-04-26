package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RightRoom implements Screen {
    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    Character character;
    Platform[] platform;

    RightRoom(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH,MyGdxGame.SCREEN_HEIGHT, camera);

        character = new Character(game.batch,
                100,
                100,
                25,
                25,
                0,
                0,
                2
        );

        platform = new Platform[5];
        platform[0] = new Platform(game.batch, 0,0,0,0,0,0);
        platform[1] = new Platform(game.batch,0,0,0,0,0,0);

        for (int i = 0; i <= platform.length-1; i++) {
            Entity.entities.add(platform[i]);
        }
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        character.render();

        game.batch.end();

}

    @Override
    public void resize(int width, int height) {
        //Updated the viewport according to the resizing
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

    public void update() {

        character.update();

        character.posy += character.vely;
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            character.vely = 3;
        } else if (character.posy > 0) {
            //This makes player have no velocity y
            character.vely--;
        }



        if (character.posy >= 600 && character.posy <= 1000 && character.posx <= 100) {
            GameScreen.savedposx = MyGdxGame.SCREEN_WIDTH-character.posx;
            GameScreen.savedposy = character.posy;
            GameScreen.savedID = character.ID;
            RightRoom.game.setScreen(new GameScreen(RightRoom.game));
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
