package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RightRoom implements Screen {
    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Character character;
    private Platform[] platform;
    private RightKey rightKey;
    private RightRoomBackground rightRoomBackground;

    private boolean kaizoinvert = false;
    static int jumppresses;

    private Sound deathnote = Gdx.audio.newSound(Gdx.files.internal("Deathnote.wav"));
    private Sound jumpsound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
    static Music Clownfiesta = Gdx.audio.newMusic(Gdx.files.internal("Clownfiesta.wav"));

    RightRoom(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(kaizoinvert, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH,MyGdxGame.SCREEN_HEIGHT, camera);

        rightRoomBackground = new RightRoomBackground(game.batch);

        character = new Character(game.batch, 100, 100, 25, 26, 4, 0, 2);

        platform = new Platform[3];
        platform[0] = new Platform(game.batch,0,50,250,50,0,0);
        platform[1] = new Platform(game.batch, 350,100,300,10,3,0);
        platform[2] = new Platform(game.batch, 1000, 10, 200, 10, 0,0);

        //Makes the platforms entities
        for (int i = 0; i <= platform.length-1; i++) {
            Entity.entities.add(platform[i]);
        }

        rightKey = new RightKey(game.batch);
    }

    @Override
    public void show() {
        Clownfiesta.play();
        Clownfiesta.setVolume(0.5f);
    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        rightRoomBackground.render();
        character.render();
        for (int i = 0; i <= platform.length-1; i++) {
            platform[i].render();
        }

        //This makes it so that if the width is 0,
        //it won't render anymore
        //This is because collecting the key will make the width 0
        if(rightKey.width>0) {
            rightKey.render();
        }

        game.batch.end();

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
        RightKey.gotkey.dispose();
        deathnote.dispose();
        Clownfiesta.dispose();
        jumpsound.dispose();
    }

    //This method changes the value for a boolean which inverts the screen
    private void kaizoupdatemethod() {
        //Only works if player is on screen
        if(character.posy - character.height >= 0) {
            if (!kaizoinvert) {
                kaizoinvert = true;
            } else if (kaizoinvert) {
                kaizoinvert = false;
            }
        }
        camera.setToOrtho(kaizoinvert, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
    }

    private void resetplater() {
        if(character.posy + character.height <= 0) {
            character.posy = 100;
            character.posx = 100;
            deathnote.play(0.5f);
        }
    }

    private void platformxmovement(int i, int leftbound, int rightbound) {
        if(platform[i].posx + platform[i].width >= rightbound) {
            platform[i].velx= -platform[i].velx;
        } else if (platform[i].posx <= leftbound) {
            platform[i].velx = -platform[i].velx;
        }
    }


    public void update(float delta) {

        //Update methods
        character.update();
        for(int i = 0; i <= platform.length-1; i++) {
            platform[i].update();
        }
        rightKey.update(delta);


        //Class specific updates

        //Makes it so platform moves at speed of its velocity
        platform[1].posx += platform[1].velx;
        //Method which sets the bounds of movement
        platformxmovement(1, 250, 1000);

        //Makes it so that the character is affected by gravity
        character.posy += character.vely;

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            //Pressing W increases the velocity, which in turn increases the position
            jumppresses++;
            //This makes it so that a character can only jump a maximum of twice
            if(jumppresses == 1) {
                character.vely = 15;
                character.posy += character.vely;
                jumpsound.play(0.5f);
                //Inverts screen when jump
                //Note: Only inverts when jump is valid
                kaizoupdatemethod();
            }
            if(jumppresses == 2) {
                character.vely = 15;
                character.posy += character.vely;
                jumpsound.play(0.5f);
                kaizoupdatemethod();
            }

        } else {
            //This makes sure there is constant negative velocity
            character.vely--;
        }

        if (character.posy >= 50 && character.posy <= 100 && character.posx < 50) {
            GameScreen.savedposx = MyGdxGame.SCREEN_WIDTH-character.width-GameScreen.loadingzonewidth;
            GameScreen.savedposy = MyGdxGame.SCREEN_HEIGHT/2-character.height/2;
            GameScreen.savedID = character.ID;
            RightRoom.game.setScreen(new GameScreen(RightRoom.game));
        }

        resetplater();

        //Updated way to check for collision
        for (int i=0; i<=platform.length-1; i++){
            if(character.isCollide(platform[i])){
                //Makes it so the character is moved to the top of the platform it's on
                character.posy = platform[i].posy + platform[i].height-1;

                //These lines make it so that the character will move along with the platform
                character.posx += platform[i].velx;
                //character.posy += platform[i].vely;

                character.handleCollision(platform[i]);
            }
        }

        if (character.isCollide(rightKey)) {
            rightKey.handleCollision(character);
        }

    }
}
