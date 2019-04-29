package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RightKey extends Entity{

    private static Sound gotkey = Gdx.audio.newSound(Gdx.files.internal("Gotkey16bit.wav"));

    private Vector2 origin;
    private Vector2 distance;

    static boolean obtainedonce;
    static boolean obtainedkey = false;

    private int songpausecounter;
    private boolean beginpausecounter;

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

        if (beginpausecounter) {
            RightRoom.Clownfiesta.setVolume(0.1f);
            if (songpausecounter <= 100) {
                songpausecounter++;
            } else{
                RightRoom.Clownfiesta.setVolume(0.5f);
                beginpausecounter = false;
            }
        }

    }

    public void render() {
        batch.draw(texture, posx, posy, width, height);
    }

    public void handleCollision(Entity e) {
        //Plays sound if touch key, but only once
        if (!obtainedonce) {
            gotkey.play(0.5f);
            obtainedonce = true;

            //Pauses background music for a short time
            //*this will only occur once as well which is good
            beginpausecounter = true;
        }
        //This makes it so the key disappears when you touch it.
        //It is still technically there, you just can't interact with it
        width=0;

        obtainedkey = true;

    }
}
