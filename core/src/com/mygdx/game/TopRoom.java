package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

public class TopRoom implements Screen {
    static MyGdxGame game;

    private Character character;
    private Boss boss;
    private TopRoomBackground topRoomBackground;
    private SpacetoShoot spacetoShoot;
    private Laser laser;

    private Music backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal("Ashtonsong3.wav"));

    private boolean renderspacetoshoot = true;
    private int pausecounter;

    private Sound shootlaser = Gdx.audio.newSound(Gdx.files.internal("Shootlaser.wav"));

    static boolean renderlaser = false;
    private int characterhealth = 10;
    private int bosshealth = 20;

    private boolean characterinvincible = false;
    private int invinciblecounter;

    private Sound bosshurt = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
    private Sound characterhurt = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
    private Sound characterdeath = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
    private Sound bossdeath = Gdx.audio.newSound(Gdx.files.internal("Bosshurt.wav"));
    private boolean bossdeathplayonce;


    TopRoom (MyGdxGame game) {
        this.game = game;
        topRoomBackground = new TopRoomBackground(game.batch);
        laser = new Laser(game.batch, 0, 0, 0, 0);
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
        spacetoShoot = new SpacetoShoot(game.batch);
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
        //laser only renders when shot
        if(renderlaser) {
            laser.render();
        }
        character.render();
        boss.render();

        if (renderspacetoshoot) {
            spacetoShoot.render();
        }

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
        shootlaser.dispose();
        bosshurt.dispose();
        characterhurt.dispose();
        characterdeath.dispose();
        bossdeath.dispose();
    }

    private void update(){

        if (pausecounter <= 100) {
            pausecounter++;
        } else{
            renderspacetoshoot = false;
        }

        //All of this only happens when spacetoshoot stops rendering
        if(!renderspacetoshoot) {

            topRoomBackground.update();
            character.update();
            boss.update();
            laser.update();

            //Only runs when the laser is off screen, meaning can only shoot one laser at a time
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !renderlaser){
                shootlaser.play(0.4f);

                laser.posx = character.posx + character.width;
                laser.posy = character.posy + character.width/2;
                laser.velx = 10;
                renderlaser = true;
            }

            //This keeps the player in bound
            if (character.posx < 0) {
                character.velx = 0;
                character.posx = 0;
            } else {
                character.velx = 7;
            }
            if (character.posy < 0) {
                character.vely = 0;
                character.posy = 0;
            } else {
                character.vely = 7;
            }

            if (character.posx > MyGdxGame.SCREEN_WIDTH - character.width) {
                character.velx = 0;
                character.posx = MyGdxGame.SCREEN_WIDTH - character.width;
            } else {
                character.velx = 7;
            }

            if (character.posy > MyGdxGame.SCREEN_HEIGHT - character.height) {
                character.vely = 0;
                character.posy = MyGdxGame.SCREEN_HEIGHT - character.height;
            } else {
                character.vely = 7;
            }


            //Checks if character or boss die
            if(characterhealth<=0){
                characterdeath.play(0.4f);
            }

            if (bosshealth <= 0) {
                if(bossdeathplayonce) {
                    bossdeath.play(0.4f);
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
            if(!characterinvincible) {
                if (character.isCollide(boss)) {
                    characterhurt.play(0.4f);
                    characterhealth--;
                    characterinvincible = true;
                }
            }

            //Collision for boss against laser
            if(boss.isCollide(laser)) {
                bosshurt.play(0.4f);
                bosshealth--;
                //Shrinks boss when hurt
                boss.height--;
                boss.width--;
                //Speeds up boss when hurt
                boss.velx++;
                boss.vely++;
            }

            //Stops rendering laser if it collides with boss by sending it off screen
            if(laser.isCollide(boss)){
                laser.posx = MyGdxGame.SCREEN_WIDTH;
            }

        }

    }
}
