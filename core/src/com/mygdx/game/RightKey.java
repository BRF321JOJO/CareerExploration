package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RightKey extends Entity{

    private Sound gotkey = Gdx.audio.newSound(Gdx.files.internal("Gotkey16bit.mp3"));

    private Vector2 origin;
    private Vector2 distance;

    static boolean obtainedonce;
    static boolean obtainedkey = false;

    static int songpausecounter;
    static boolean beginpausecounter;

    static boolean savedkey;

    RightKey(SpriteBatch batch) {
        super(
                batch,
                new Texture("TransparentKey.png"),
                1100,
                200,
                31,
                53,
                0,
                0,
                0
        );

        origin = new Vector2();
        //The y value corresponds to the radius of circulation
        distance = new Vector2(0,6);
    }

    void update(float delta) {

        //This makes the image go in a circle
        origin.set(1100,200);
        distance.rotate(250*delta);
        Vector2 newPos = origin.add(distance);
        posx = (int)newPos.x - width/2;
        posy = (int)newPos.y - height/2;

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {
        //Plays sound if touch key, but only once
        if (!obtainedonce&&!savedkey) {
            gotkey.play(0.5f);
            obtainedonce = true;

            //Pauses background music for a short time
            //*this will only occur once as well which is good
            beginpausecounter = true;
        }
        //This makes it so the key disappears when you touch it.
        //It is still technically there, you just can't interact with it
        //width=0;

        obtainedkey = true;

    }
}
