package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.io.*;

public class SaveHandler {
    static GameData gamedata;

    private static void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("highscore.sav")
            );
            out.writeObject(gamedata);
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }


    static void load() {
        try{
            if(!saveFileExists()) {
                CreateFileMaybe();
                return;
            }
            ObjectInputStream in = new ObjectInputStream((
                    new FileInputStream("highscore.sav")
            ));
            gamedata = (GameData) in.readObject();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }


    private static boolean saveFileExists() {
        File f = new File("highscores.sav");
        return f.exists();
    }


    private static void CreateFileMaybe() {
        gamedata = new GameData();
        gamedata.scorearray();
        save();
    }
}
