package com.mygdx.game;
import com.badlogic.gdx.audio.Sound;
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
    private Label Highscores;

    private BitmapFont font = new BitmapFont();

    private Sound menunavigate0 = Gdx.audio.newSound(Gdx.files.internal("menunavigate0.mp3"));
    private Sound imqazi = Gdx.audio.newSound(Gdx.files.internal("ImQazi.mp3"));
    private boolean playsoundonce = false;
    private boolean playhighscoresoundonce = false;

    //Area of click ability
    private int leftx = MyGdxGame.SCREEN_WIDTH/2-100;
    private int rightx = MyGdxGame.SCREEN_WIDTH/2+100;
    private int bottomy = MyGdxGame.SCREEN_HEIGHT/2+25;
    private int topy = MyGdxGame.SCREEN_HEIGHT/2+150;

    private int leftx2 = leftx;
    private int rightx2 = rightx;
    private int bottomy2 = bottomy-175;
    private int topy2 = topy-175;


    private float labelstartingsize = 1.7f;
    private float labelincreasedsize = 2.5f;

    //Saved positions for square when spawning in


    MenuHUD(SpriteBatch batch) {
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table displayTable = new Table();
        displayTable.center();
        displayTable.setFillParent(true);

        StartGame = new Label("Start Game", new Label.LabelStyle(font, Color.WHITE));
        Highscores = new Label("Surprise", new Label.LabelStyle(font, Color.WHITE));

        StartGame.setFontScale(labelstartingsize);
        Highscores.setFontScale(labelstartingsize);

        displayTable.add(StartGame);
        //this line makes the following code appear on the next row
        displayTable.row();
        displayTable.add(Highscores).padTop(100);

        stage.addActor(displayTable);

    }

    void update() {

        //Information about getx/gety
        //The click area is actually calculating from the top left of the screen
        //To correct for this, I subtract the height from instances of .getY()

        //Change size of start game when hover over it
        if(Gdx.input.getX() >= leftx && Gdx.input.getX() <= rightx &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() >= bottomy &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() <= topy) {

            //This increases the size of the text
            StartGame.setFontScale(labelincreasedsize);

            //This plays a sound when it is hovered over
            if (!playsoundonce) {
                menunavigate0.play(0.5f);
                playsoundonce = true;
            }


            //This line of code makes it so that if you click in this area
            // it will change to the next screen
            if (Gdx.input.isTouched()) {
                MainMenu.game.setScreen(new GameScreen(MainMenu.game));
            }

        } else {
            StartGame.setFontScale(labelstartingsize);
            playsoundonce = false;
        }

        //Change size of highscores when hover over it
        if(Gdx.input.getX() >= leftx2 && Gdx.input.getX() <= rightx2 &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() >= bottomy2 &&
                MyGdxGame.SCREEN_HEIGHT - Gdx.input.getY() <= topy2) {
            Highscores.setFontScale(labelincreasedsize);

            if(!playhighscoresoundonce) {
                menunavigate0.play(0.5f);
                playhighscoresoundonce = true;
            }

            if(Gdx.input.isTouched()){
                imqazi.play(1f);
            }

        } else {
            Highscores.setFontScale(labelstartingsize);
            playhighscoresoundonce = false;
        }
    }

}
