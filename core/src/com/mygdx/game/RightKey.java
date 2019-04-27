package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RightKey extends Entity{

    static Sound gotkey = Gdx.audio.newSound(Gdx.files.internal("Gotkey16bit.wav"));

    RightKey(SpriteBatch batch) {
        super(
                batch,
                new Texture("TransparentKey.png"),
                800,
                10,
                31,
                53,
                0,
                0,
                0
        );
    }

    void update() {

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {
        gotkey.play(0.5f);
    }

}
