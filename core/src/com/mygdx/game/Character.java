package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Character extends Entity{

    public Character(SpriteBatch batch) {
        super(
                batch,
                new Texture("badlogic.jpg"),
                0,
                0,
                100,
                100,
                0,
                0
        );
    }

    public void update() {
        
    }

    @Override
    public void render(){
        batch.draw(texture, posx, posy, width, height);
    }

    @Override
    public void handleCollision(Entity e) {

    }
}
