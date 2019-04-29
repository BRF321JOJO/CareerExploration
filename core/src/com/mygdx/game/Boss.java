package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boss extends Entity {

    private static int startingwidth = 315;
    private static int startingheight = 315;

    Boss(SpriteBatch batch){
        super(
                batch,
                new Texture("Transparentboss.png"),
                MyGdxGame.SCREEN_WIDTH/2 - startingwidth/2,
                MyGdxGame.SCREEN_HEIGHT/2 - startingheight/2,
                startingwidth,
                startingheight,
                2,
                2,
                5

        );
    }

    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    public void update(){
        posx += velx;
        posy += vely;

        if(posx>=MyGdxGame.SCREEN_WIDTH - width || posx <= 0){
            velx = -velx;
        }
        if(posy>=MyGdxGame.SCREEN_HEIGHT - height || posy<=0){
            vely = -vely;
        }
    }

    public void handleCollision(Entity e){

    }
}
