package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends State {

    private Gui gui;
    private Texture background;

    public void create() {
	gui = new Gui();
	background = new Texture(Gdx.files.local("res/textures/mainmenu.png"));

	// Play button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128, 300, 256, 64,
		new Clickable() {

		    @Override
		    public void onClick() {

		    }

		    @Override
		    public void onRelease() {
			Main.instance.getStateMachine().addStateToTop(
				Main.instance.mainGame);
		    }

		}).setText("Play"));

	// Options button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128, 200, 256, 64,
		new Clickable() {

		    @Override
		    public void onClick() {

		    }

		    @Override
		    public void onRelease() {
			Main.instance.getStateMachine().addStateToTop(
				Main.instance.options);
		    }

		}).setText("Options"));

	// Exit button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128, 100, 256, 64,
		new Clickable() {

		    @Override
		    public void onClick() {

		    }

		    @Override
		    public void onRelease() {
			Gdx.app.exit();
		    }

		}).setText("Exit"));
	
	Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
    }

    public void update() {
	gui.update();
    }

    public void render(SpriteBatch batch) {
	batch.draw(background, 0, 0);
	
	Gui.font.draw(batch, "The Unfortunate", Gdx.graphics.getWidth()/2 - Gui.font.getBounds("The Unfortunate").width/2, 600);
	Gui.font.draw(batch, "Potato", Gdx.graphics.getWidth()/2 - Gui.font.getBounds("Potato").width/2, 600 - Gui.font.getLineHeight());
	
	gui.render(batch);
    }
    
    public void dispose() {
	background.dispose();
    }

}
