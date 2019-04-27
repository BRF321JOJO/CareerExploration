package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RightRoomBackground extends AbstractImage {
    RightRoomBackground(SpriteBatch batch) {
        super(batch,
                new Texture("Oldgrungebackground.jpg"),
                0,
                0,
                MyGdxGame.SCREEN_WIDTH,
                MyGdxGame.SCREEN_HEIGHT,
                0,
                0
        );
    }

//    void update() {
//
//    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

}
