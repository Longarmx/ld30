package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldGui extends Gui {

    private World world;
    private TextureRegion key;
    private int level;
    private int levelCountdown;
    private BitmapFont font;

    public WorldGui(World world) {
	super();

	this.world = world;

	camera.position.set(world.viewportX(), Gdx.graphics.getHeight() / 2, 0);

	key = Spritesheet.entities.get(0, 1);
	font = new BitmapFont(Gdx.files.local("res/font/default.fnt"), false);
	font.setScale(.75f);

	level = world.getLevel() - 1;
    }

    public void update() {
	super.update();

	levelCountdown--;

	if (level != world.getLevel()) {
	    level = world.getLevel();
	    levelCountdown = 120;
	}
    }

    public void render(SpriteBatch batch) {
	super.render(batch);

	if (world.getPlayer().hasKey()) {
	    batch.draw(key, 15, 60, 48, 48);
	}

	if (levelCountdown >= 0) {
	    font.draw(batch, "Level: " + (level + 1), 15, 50);
	}
    }

    @Override
    public void finalize() {
	font.dispose();
    }
}
