package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class TopRoom implements Screen {
    static MyGdxGame game;

    private Character character;
    private Boss boss;
    private TopRoomBackground topRoomBackground;

    private Music backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal("Ashtonsong3.wav"));

    TopRoom (MyGdxGame game) {
        this.game = game;
        topRoomBackground = new TopRoomBackground(game.batch);
        character = new Character(game.batch,
                GameScreen.savedposx,
                150,
                75,
                75,
                5,
                5,
                1
        );
        boss = new Boss(game.batch);
    }

    @Override
    public void show() {
        backgroundmusic.play();
        backgroundmusic.setLooping(true);
        backgroundmusic.setVolume(0.4f);
    }

    @Override
    public void render (float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        topRoomBackground.render();
        character.render();
        boss.render();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
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
        backgroundmusic.dispose();
    }

//    boolean CentralLoadingZone(){
//        return character.posx >= MyGdxGame.SCREEN_WIDTH/2 - 50
//                && character.posx <= MyGdxGame.SCREEN_WIDTH/2 + 50
//                && character.posy <= GameScreen.totoproomloadingzoneheight;
//    }

    private void update(){
        topRoomBackground.update();
        character.update();
        boss.update();

        //This is the loading zone for the top room
//        if (CentralLoadingZone()) {
//            GameScreen.savedposx = character.posx;
//            GameScreen.savedposy = MyGdxGame.SCREEN_HEIGHT - 100;
//            GameScreen.savedID = character.ID;
//            TopRoom.game.setScreen(new GameScreen(TopRoom.game));
//        }


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
