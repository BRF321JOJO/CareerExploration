package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {

	public static void main (String[] arg) {

		//This makes it borderless
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.title = "Escape Room";
		config.addIcon("pixelkey.jpg", Files.FileType.Internal);
		config.width = MyGdxGame.SCREEN_WIDTH;
		config.height = MyGdxGame.SCREEN_HEIGHT;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
