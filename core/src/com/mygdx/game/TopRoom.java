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

public class TopRoom implements Screen {
    static MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    //These fields create the Objects
    //In order of rendering
    private RedHealth[] redHealth;
    private CharacterHealth characterHealthclass;
    private BossHealth bossHealthclass;
    private Laser[] laser;
    private Character character;
    private Boss boss;
    private SpacetoShoot spacetoShoot;
    private WinGame winGame;

    //These fields create music/sounds
    private Music backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal("Ashtonsong3.mp3"));
    private Sound shootlaser = Gdx.audio.newSound(Gdx.files.internal("Shootlaser.mp3"));
    private Sound hurtsound = Gdx.audio.newSound(Gdx.files.internal("BossHurt.mp3"));
//    private Sound bosshurt = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
//    private Sound characterhurt = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
//    private Sound characterdeath = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
//    private Sound bossdeath = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
    private Music wingamemusic = Gdx.audio.newMusic(Gdx.files.internal("happymusic.mp3"));


    //These fields are normal fields
    private int canshootlaser = 0;
    private int characterhealth = 13;
    private int bosshealth = 96;
    private boolean characterinvincible = false;
    private int invinciblecounter;
    private boolean bossdeathplayonce;
    private boolean characterdeathplayonce;
    private boolean wongame = false;
    private int bosscheckcounter;
    private boolean userandombossvelocity = true;
    private int laservelocity = 40;

    TopRoom(MyGdxGame game) {
        //These set up the world
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        viewport = new FitViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);

        //These construct the game (in order of rendering)
        redHealth = new RedHealth[2];
        redHealth[0] = new RedHealth(game.batch, 0, 757-40);
        redHealth[1] = new RedHealth(game.batch, MyGdxGame.SCREEN_WIDTH-BossHealth.constantwidth, 757-90);

        characterHealthclass = new CharacterHealth(game.batch);
        bossHealthclass = new BossHealth(game.batch);
        laser = new Laser[3];
        //Sets laser position to off screen
        for (int i = 0; i <= laser.length - 1; i++) {
            laser[i] = new Laser(game.batch, MyGdxGame.SCREEN_WIDTH, 0, 0, 0);
        }
        character = new Character(game.batch, MainRoom.savedposx, 150, 75, 75, 5, 5, 1);
        boss = new Boss(game.batch);
        spacetoShoot = new SpacetoShoot(game.batch);
        winGame = new WinGame(game.batch);
    }

    @Override
    public void show() {
        backgroundmusic.play();
        backgroundmusic.setLooping(true);
        backgroundmusic.setVolume(0.6f);
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        for(int i = 0; i<= redHealth.length-1;i++) {
            redHealth[i].render();
        }
        characterHealthclass.render();
        bossHealthclass.render();

        for (int i = 0; i <= laser.length - 1; i++) {
            laser[i].render();
        }

        character.render();

        //Only renders boss if the width actually exists
        if (boss.width > 0) {
            boss.render();
        }
        if (SpacetoShoot.renderspacetoshoot) {
            spacetoShoot.render();
        }
        if (wongame) {
            winGame.render();
        }
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
        backgroundmusic.dispose();
        shootlaser.dispose();

        hurtsound.dispose();
//        bosshurt.dispose();
//        characterhurt.dispose();
//        characterdeath.dispose();
//        bossdeath.dispose();

        wingamemusic.dispose();
    }

    private void update(){

       spacetoShoot.update();

        //All of this only happens when spacetoshoot stops rendering
        if(!SpacetoShoot.renderspacetoshoot) {

            //These update the game based on the classes
            for(int i = 0; i<= redHealth.length-1;i++) {
                redHealth[i].update();
            }
            for(int i = 0; i <=laser.length-1; i++) {
                laser[i].update();
            }
            character.update();
            boss.update();

            //Only runs when the laser is off screen, meaning can only shoot one laser at a time
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (laser[0].posx >= MyGdxGame.SCREEN_WIDTH && canshootlaser == 0) {
                    shootlaser.play(0.2f);
                    laser[0].posx = character.posx + character.width;
                    laser[0].posy = character.posy + character.width / 2;
                    laser[0].velx = laservelocity;
                    canshootlaser++;
                }
                else if (laser[1].posx >= MyGdxGame.SCREEN_WIDTH && canshootlaser == 1) {
                    shootlaser.play(0.2f);
                    laser[1].posx = character.posx + character.width;
                    laser[1].posy = character.posy + character.width / 2;
                    laser[1].velx = laservelocity;
                    canshootlaser++;
                }
                else if (laser[2].posx >= MyGdxGame.SCREEN_WIDTH && canshootlaser == 2){
                    shootlaser.play(0.2f);
                    laser[2].posx = character.posx + character.width;
                    laser[2].posy = character.posy + character.width / 2;
                    laser[2].velx = laservelocity;
                    canshootlaser = 0;
                }
            }


            //Checks if character or boss die
            if(characterhealth<=0){
                if(characterdeathplayonce) {
                    hurtsound.play(0.4f);
                    //characterdeath.play(0.4f);
                    characterdeathplayonce = false;
                }
            }

            if (bosshealth <= 0) {
                if(bossdeathplayonce) {
                    hurtsound.play(0.4f);
                    //bossdeath.play(0.7f);
                    bossdeathplayonce = false;
                }
            }


            //Makes character invincible for a short time (30 frames)
            if (characterinvincible) {
                if(invinciblecounter <= 30) {
                    invinciblecounter++;
                } else{
                    characterinvincible = false;
                    invinciblecounter = 0;
                }
            }

            //Checks collision for player against boss
            //Added where this can only collide when the boss actually exists on screen (rendered)
            if(!characterinvincible && boss.width>0) {
                if (character.isCollide(boss)) {
                    hurtsound.play(0.7f);
                    //characterhurt.play(0.7f);
                    characterhealth--;
                    characterinvincible = true;
                    redHealth[0].width -= 40;
                }
            }

            //Collision for boss against laser
            for(int i = 0; i<=laser.length-1; i++) {
                if (boss.isCollide(laser[i])) {
                    hurtsound.play(0.2f);
                    hurtsound.play(0.2f);
                    bosshealth--;
                    //Shrinks boss when hurt
                    boss.height -= 5;
                    boss.width -= 5;

                    //Speeds up boss when hurt
                    if(boss.velx > 0){
                        boss.velx++;
                    } else if (boss.velx<0){
                        boss.velx--;
                    }

                    if(boss.vely>0){
                        boss.vely++;
                    } else if(boss.vely<0){
                        boss.vely--;
                    }
                    bosshealth--;
                    redHealth[1].width -= 8;
                }
            }

            //Stops rendering laser if it collides with boss by sending it off screen
            for(int i = 0; i <=laser.length-1; i++) {
                if (laser[i].isCollide(boss)) {
                    laser[i].posx = MyGdxGame.SCREEN_WIDTH;
                }
            }


            //Makes boss follow you
            if(userandombossvelocity) {
                userandombossvelocity = false;

                //Makes boss follow player
                if (character.posx < boss.posx) {
                    if(boss.velx>0) {
                        boss.velx = -boss.velx;
                    }
                }

                if (character.posy < boss.posy) {
                    if(boss.vely>0) {
                        boss.vely = -boss.velx;
                    }
                }
            }

            if(!userandombossvelocity) {
                if (bosscheckcounter <= 100) {
                    bosscheckcounter++;
                } else {
                    userandombossvelocity = true;
                    bosscheckcounter = 0;
                }
            }



            //This actually loops because boss.width will continue to be true
            if(boss.width <= 0 || bosshealth <=0){
                wingamemusic.play();
                wingamemusic.setVolume(0.7f);
                backgroundmusic.stop();
                wongame = true;
                boss.width=0;
            }

            if(characterhealth==0){
                TopRoom.game.setScreen(new MainRoom(TopRoom.game));
            }

        }

    }
}
