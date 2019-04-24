package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


class MenuHUD {
    Stage stage;
    private Viewport viewport;

    private Label StartGame;
    //private Label Highscores;
    private BitmapFont font = new BitmapFont();


    //Area of click ability
    private int leftx = 500;
    private int rightx = 700;
    private int bottomy = 500;
    private int topy = 700;

    MenuHUD(SpriteBatch batch) {
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table displayTable = new Table();
        displayTable.center();
        displayTable.setFillParent(true);

        StartGame = new Label("", new Label.LabelStyle(font, Color.WHITE));

        StartGame.setFontScale(1.3F);

        displayTable.add(StartGame).expandX();

        stage.addActor(displayTable);
    }

    void update() {

        if(Gdx.input.getX() > leftx && Gdx.input.getX() < rightx) {
            if(Gdx.input.getY() > bottomy && Gdx.input.getY() < topy) {
                StartGame.setFontScale(2f);
            }
        } else{
            StartGame.setFontScale(1.3f);
        }

        updateStartGame("Start Game");
    }

    private void updateStartGame (String StartGameupdate) {StartGame.setText(StartGameupdate);}
}
