package com.longarmx.ld30;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class World {

    public static final int VIEWPORT_WIDTH = Gdx.graphics.getWidth() / 2;
    public static final int VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

    private Tile[][] tiles;
    private WorldPosition position;
    private OrthographicCamera camera;
    private int width, height, level;
    private int desPosX, desPosY;
    private Player player;
    private WorldGui gui;

    public World(WorldPosition position, InputMultiplexer multiplexer) {
	this.position = position;
	camera = new OrthographicCamera(Gdx.graphics.getWidth(),
		Gdx.graphics.getHeight());
	camera.translate(VIEWPORT_WIDTH * (1 - position.ordinal()),
		VIEWPORT_HEIGHT / 2);

	level = 0;
	loadLevel(level);

	player = new Player(getStartingPos(), this);
	multiplexer.addProcessor(player);
	
	gui = new WorldGui(this);
    }

    public void update() {
	player.update();
	centerCamera(player.getCenterX(), player.getCenterY());

	camera.translate((desPosX - camera.position.x) / 10f,
		(desPosY - camera.position.y) / 10f);
	camera.update();
	
	gui.update();
    }

    public void render(SpriteBatch batch) {
	Gdx.gl.glScissor(viewportX(), viewportY(), VIEWPORT_WIDTH,
		VIEWPORT_HEIGHT);
	batch.setProjectionMatrix(camera.combined);

	for (int i = 0; i < tiles.length; i++) {
	    for (int j = 0; j < tiles[0].length; j++) {
		tiles[i][j].render(batch);
	    }
	}

	player.render(batch);
	gui.render(batch);
    }

    public void connectTiles() {
	for (int j = tiles[0].length - 1; j >= 0; j--) {
	    for (int i = 0; i < tiles.length; i++) {
		tiles[i][j].updateRegion(this);
	    }
	}
    }

    public void nextLevel() {
	loadLevel(++level);

	player.setPos(getStartingPos());
    }

    public void resetLevel() {
	loadLevel(level);

	player.setPos(getStartingPos());
    }

    private void loadLevel(int levelNum) {
	try {
	    if (!new File("res/levels/level_" + levelNum + ".lvl").exists()) {
		Main.instance.getStateMachine().addStateToTop(Main.instance.ending);
		return;
	    }

	    BufferedReader reader = new BufferedReader(new FileReader(
		    "res/levels/level_" + levelNum + ".lvl"));
	    ArrayList<Integer> ints = new ArrayList<Integer>();

	    width = 0;
	    height = 0;

	    int c;
	    boolean secondLevel = false;
	    while ((c = reader.read()) != -1) {
		if (position == WorldPosition.RIGHT) {
		    if (secondLevel) {
			ints.add(c);
		    }
		} else {
		    if (!secondLevel && c != 35)
			ints.add(c);
		}

		if (c == 35)
		    secondLevel = true;
		else
		    continue;
	    }

	    reader.close();

	    if (position == WorldPosition.RIGHT) {
		ints.remove(0);
		ints.remove(0);
		height++;
	    }

	    width = ints.indexOf(13);

	    for (int i = 0; i < ints.size(); i++) {
		if (ints.get(i) == 13)
		    height++;
	    }

	    for (int i = ints.size() - 1; i >= 0; i--) {
		if (ints.get(i) == 10 || ints.get(i) == 13) {
		    ints.remove(i);
		}
	    }

	    tiles = new Tile[width][height];

	    int k = 0;
	    for (int j = tiles[0].length - 1; j >= 0; j--) {
		for (int i = 0; i < tiles.length; i++) {
		    tiles[i][j] = new Tile(ints.get(k++) - 48, i, j);
		}
	    }

	    connectTiles();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private Vector2 getStartingPos() {
	for (int i = 0; i < tiles.length; i++) {
	    for (int j = 0; j < tiles[0].length; j++) {
		if (tiles[i][j].getId() == Tile.START_POS)
		    return new Vector2(i * Tile.SIZE, j * Tile.SIZE);
	    }
	}

	return new Vector2(0, 0);
    }

    public boolean win() {
	return player.isOnEndTile();
    }

    public Tile getTile(int x, int y) {
	if (x < 0 || x >= width || y < 0 || y >= height)
	    return null;

	return tiles[x][y];
    }

    private void centerCamera(int x, int y) {
	desPosX = x - viewportX() + VIEWPORT_WIDTH / 2;
	desPosY = y;
    }

    public int viewportX() {
	return position.ordinal() * VIEWPORT_WIDTH;
    }

    public int viewportY() {
	return 0;
    }
    
    public Player getPlayer() {
	return player;
    }
    
    public WorldPosition getPosition() {
	return position;
    }
    
    public int getLevel() {
	return level;
    }

    public enum WorldPosition {
	LEFT, RIGHT
    }

}
