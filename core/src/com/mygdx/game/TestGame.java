//package com.mygdx.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector3;
//
//public class MyGdxGame extends ApplicationAdapter {
//
//	//Fields
//	Texture img;
//	Rectangle Testimage;
//
//	private OrthographicCamera camera;
//	private SpriteBatch batch;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 480);
//
//		//Create game specific things
//		img = new Texture("badlogic.jpg");
//
//		//To create a sound
//		//name = Gdx.audio.newSound(Gdx.files.interval("name.wav"));
//
//		//To create music
//		//name = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//		//music..setLooping(true); makes music loop
//		//music.play(); plays music
//
//		//Create a new image
//		Testimage = new Rectangle();
//		Testimage.x = 64;
//		Testimage.y = 20;
//		Testimage.width = 64;
//		Testimage.height=64;
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		camera.update();
//
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.draw(img, Testimage.x, Testimage.y);
//		batch.end();
//
//
//
//
//		//This code allows touching and holding images
//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
//			camera.unproject(touchPos);
//			Testimage.x = touchPos.x;
//		}
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
//}
