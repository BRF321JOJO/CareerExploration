package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RightKey extends Entity{
    RightKey(SpriteBatch batch) {
        super(
                batch,
                new Texture("TransparentKey.png"),
                700,
                700,
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
    }
}
