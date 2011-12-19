package krispy.kirorun;

import krispy.kirorun.screens.GameLoop;
import krispy.kirorun.screens.GameOver;
import krispy.kirorun.screens.MainMenu;
import krispy.kirorun.screens.Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class KiroRun implements ApplicationListener {
	/** flag indicating whether we were initialized already **/
	private boolean isInitialized = false;
	/** the current screen **/
	private Screen screen;

	@Override public void dispose () {

	}

	@Override public void render () {
		Application app = Gdx.app;

		// update the screen
		screen.update(app);

		// render the screen
		screen.render(app);

		// when the screen is done we change to the
		// next screen
		if (screen.isDone()) {
			// dispose the current screen
			screen.dispose();

			// if this screen is a main menu screen we switch to
			// the game loop
			if (screen instanceof MainMenu)
				screen = new GameLoop(app);
			else
			// if this screen is a game loop screen we switch to the
			// game over screen
			if (screen instanceof GameLoop)
				screen = new GameOver(app);
			else
			// if this screen is a game over screen we switch to the
			// main menu screen
			if (screen instanceof GameOver) screen = new MainMenu(app);
		}
	}

	@Override public void resize (int width, int height) {

	}

	@Override public void create () {
		if (!isInitialized) {
			screen = new MainMenu(Gdx.app);
			Music music = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/8.12.mp3", FileType.Internal));
			music.setLooping(true);
			music.play();
			isInitialized = true;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		System.out.println("resume");
	}
}
