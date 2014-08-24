package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Options extends State {

    private Gui gui;

    public void create() {
	gui = new Gui();

	// Mute button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128, 200, 256, 64,
		new Clickable() {

		    @Override
		    public void onClick() {

		    }

		    @Override
		    public void onRelease() {
			SoundManager.muted = !SoundManager.muted;

			if (SoundManager.muted)
			    ((Button) gui.get(0)).setText("Unmute");
			else
			    ((Button) gui.get(0)).setText("Mute");
		    }

		}).setText(SoundManager.muted ? "Unmute" : "Mute"));

	// Increase volume button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 + 128 + 15, 200, 64, 64,
		new Clickable() {

		    @Override
		    public void onClick() {
			if(SoundManager.soundVolume < 1)
			    SoundManager.soundVolume += 0.1f;
		    }

		    @Override
		    public void onRelease() {
			
		    }

		}).setText("+"));
	
	// Decrease volume button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128 - 64 - 15, 200, 64, 64,
		new Clickable() {

		    @Override
		    public void onClick() {
			if(SoundManager.soundVolume > 0)
			    SoundManager.soundVolume -= 0.1f;
		    }

		    @Override
		    public void onRelease() {
			
		    }

		}).setText("-"));

	// Back button
	gui.add(new Button(Gdx.graphics.getWidth() / 2 - 128, 100, 256, 64,
		new Clickable() {

		    @Override
		    public void onClick() {

		    }

		    @Override
		    public void onRelease() {
			Main.instance.getStateMachine().removeStateFromTop();
		    }

		}).setText("Back"));
    }

    public void update() {
	gui.update();
    }

    public void render(SpriteBatch batch) {
	gui.render(batch);
    }

}
