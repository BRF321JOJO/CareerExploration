package com.mygdx.game;

import java.io.Serializable;

public class GameData implements Serializable {

    private static final int MAX_SCORES = 3;
    private static double[] highScores;

    //This sets the high scores as an array
    GameData() {
        highScores = new double[MAX_SCORES];
    }

    //This sets the high scores as 0 before the game starts
    void scorearray() {
        for (int i = 0; i < MAX_SCORES; i++) {
            highScores[i]=0;
        }
    }

    private double[] getHighScores() {return highScores;}

    //This returns true if there exists a new high score
    private static boolean isHighScore(double score){
        return score > highScores[MAX_SCORES-1];
    }

    static void addHighScore(double newScore) {
        if (isHighScore(newScore)) {
            highScores[MAX_SCORES - 1] = newScore;
            sortHighScores();
        }
    }

    //Sorts highscores by putting the highest score on top, lowest score on bottom of array
    private static void sortHighScores(){
        for(int i = 0; i <MAX_SCORES; i++){
            double score = highScores[i];

            for (int j = i-1; j>=0 && highScores[j] < score; j--) {
                highScores[j+1] = highScores[j];
            }
        }
    }

    void update() {
        SaveHandler.load();
        highScores = SaveHandler.gamedata.getHighScores();
    }
}
