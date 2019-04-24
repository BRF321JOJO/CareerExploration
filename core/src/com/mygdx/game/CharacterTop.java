package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterTop extends Entity{
    CharacterTop (SpriteBatch batch){
        super(
                batch,
                new Texture(""),
                0,
                0,
                0,
                0,
                5,
                5
        );
    }

    void update() {

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e){

    }


}
