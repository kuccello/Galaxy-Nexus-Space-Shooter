package krispy.kirorun.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * The main menu screen showing a background, the logo of the game and a 
 * label telling the user to touch the screen to start the game. Waits 
 * for the touch and returns isDone() == true when it's done so that the 
 * ochestrating KiroRun class can switch to the next screen.
 */
public class MainMenu implements Screen {

	/** the SpriteBatch used to draw the background, logo and text **/
	private final SpriteBatch spriteBatch;
	/** the background texture **/
	private final Texture background;
	/** the logo texture **/
	private final Texture logo;
	/** the font **/
	private final BitmapFont font;
	/** is done flag **/
	private boolean isDone = false;
	/** view & transform matrix **/
	private final Matrix4 viewMatrix = new Matrix4();
	private final Matrix4 transformMatrix = new Matrix4();

	public MainMenu (Application app) {
		spriteBatch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("data/planet.jpg"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);		

		logo = new Texture(Gdx.files.internal("data/title.png"));
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		font = new BitmapFont(Gdx.files.internal("data/font16.fnt"), Gdx.files.internal("data/font16.png"), false);
	}

	@Override public void render (Application app) {
		app.getGraphics().getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);

		viewMatrix.setToOrtho2D(0, 0, 480, 320);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(transformMatrix);
		spriteBatch.begin();
		spriteBatch.disableBlending();
		spriteBatch.setColor(Color.WHITE);		
		spriteBatch.draw(background, 0, 0, 480, 320, 0, 0, 512, 512, false, false);
		spriteBatch.enableBlending();
		spriteBatch.draw(logo, 0, 320-128, 480, 128, 0, 0, 512, 256, false, false);
		spriteBatch.setBlendFunction(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		String text = "Touch screen to start!";
		float width = font.getBounds(text).width;	
		font.draw(spriteBatch, text, 240 - width / 2, 128);
		spriteBatch.end();
	}

	@Override public void update (Application app) {
		isDone = app.getInput().isTouched();
	}

	@Override public boolean isDone () {
		return isDone;
	}

	@Override public void dispose () {
		spriteBatch.dispose();
		background.dispose();
		logo.dispose();
		font.dispose();
	}

}
