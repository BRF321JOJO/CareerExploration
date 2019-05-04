package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpacetoShoot extends AbstractImage {

    static boolean renderspacetoshoot = true;
    private static int pausecounter;

    SpacetoShoot(SpriteBatch batch){
        super(
                batch,
                new Texture("SpacetoShoot.png"),
                0,
                0,
                MyGdxGame.SCREEN_WIDTH,
                MyGdxGame.SCREEN_HEIGHT,
                0,
                0
        );
    }

    public void update(){
        if (pausecounter <= 100) {
            pausecounter++;
        } else{
            renderspacetoshoot = false;
        }
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }
}
