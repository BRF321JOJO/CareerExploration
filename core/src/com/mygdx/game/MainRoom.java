package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainRoom implements Screen {

    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MainRoomBackground mainRoomBackground;
    private Controlsimage controlsimage;
    private KeyWarning keyWarning;
    private LoadingZone[] loadingZone;
    private Character character;

    private Music ambientMusic = Gdx.audio.newMusic(Gdx.files.internal("bossfight.mp3"));;

    private Pixmap curserfont = new Pixmap(Gdx.files.internal("Cursor.png"));

    //This starts at this point and is later dependent upon the saved position
    static int savedposx;
    static int savedposy;
    static int savedID;
    static int rightloadwidth = 100;
    private static int rightloadheight = 300;
    private static int toploadheight = 100;
    private static int toploadwidth = 300;
    private boolean renderkey;


    private void checkposition () {
        //This line of code makes it so that the game only sets this position when the game starts
        if(savedID == 0) {
            savedposx = MyGdxGame.SCREEN_WIDTH / 2 - Character.characterwidth / 2 ;
            //Subtract 55 so that the character will be in the center of the controls area
            savedposy = MyGdxGame.SCREEN_HEIGHT / 2 - Character.characterheight / 2 - 55;
        }
    }


    private void keycheat(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            RightKey.obtainedkey=true;
            RightKey.obtainedonce=true;
        }
    }


    MainRoom(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);

        //Sets curser things
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(curserfont, 0, 0));

        mainRoomBackground = new MainRoomBackground(game.batch);
        controlsimage = new Controlsimage(game.batch);
        keyWarning = new KeyWarning(game.batch);
        loadingZone = new LoadingZone[2];

        loadingZone[0] = new LoadingZone(game.batch ,
                MyGdxGame.SCREEN_WIDTH - rightloadwidth,
                MyGdxGame.SCREEN_HEIGHT/2 - rightloadheight/2,
                rightloadwidth,
                rightloadheight
        );

        //Idk why this code is different from above, but I just have to continue, time running out
        //I would rather make this an entity
        loadingZone[1] = new LoadingZone(game.batch,
                MyGdxGame.SCREEN_WIDTH/2 - toploadwidth/2,
                MyGdxGame.SCREEN_HEIGHT - toploadheight,
                toploadwidth,
                MyGdxGame.SCREEN_HEIGHT - toploadheight
        );


        //Here so that the character will spawn in at the appropriote position
        checkposition();
        character = new Character(game.batch, savedposx, savedposy, 75, 75, 7, 7, 0);

    }

    @Override
    public void show() {
        ambientMusic.play();
        ambientMusic.setLooping(true);
        ambientMusic.setVolume(0.4f);
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
        controlsimage.render();
        //Only renders if within area for rendering
        if(renderkey) {
            keyWarning.render();
        }
        for(int i=0; i<=loadingZone.length-1; i++) {
            loadingZone[i].render();
        }
        character.render();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
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
        ambientMusic.dispose();
        curserfont.dispose();
    }

    private boolean TopLoadingZone() {
        return character.posx >= MyGdxGame.SCREEN_WIDTH/2 - character.width - toploadwidth/2
                && character.posx <= MyGdxGame.SCREEN_WIDTH/2 + toploadwidth/2
                && character.posy >= MyGdxGame.SCREEN_HEIGHT - character.height - toploadheight;
    }

    private boolean RightLoadingZone(){
        return character.posy >= MyGdxGame.SCREEN_HEIGHT/2 - character.height - rightloadheight/2
                && character.posy <= MyGdxGame.SCREEN_HEIGHT/2 + rightloadheight/2
                && character.posx >= MyGdxGame.SCREEN_WIDTH - character.width - rightloadwidth;
    }

    private void update(){
        keyWarning.update();
        character.update();

        keycheat();

        //You will only be able to enter when you have obtained the key
        if(TopLoadingZone()){
            if(RightKey.obtainedkey) {
                savedposx = character.posx;
                MainRoom.game.setScreen(new TopRoom(MainRoom.game));
            } else {
                //This code runs if you didn't get the key, renders the key warning
                renderkey = true;
            }
        } else {
            renderkey=false;
        }


        if (RightLoadingZone()) {
            savedposy = character.posy;
            MainRoom.game.setScreen(new RightRoom(MainRoom.game));
        }

//        for (Entity e : Entity.entities) {
//            //Checks collision for player specifically
//            if (character.isCollide(e)) {
//                //Says all handling denoted within respective class
//                character.handleCollision(e);
//                e.handleCollision(character);
//            }

//        }

    }
}
