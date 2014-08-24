package com.longarmx.ld30;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements InputProcessor {

    public static final int SIZE = 48;

    private TextureRegion region;
    private int x, y;
    private World world;
    private boolean hasKey;

    public Player(Vector2 pos, World world) {
	setPos(pos);
	
	this.world = world;
	
	hasKey = false;
    }

    public void update() {

    }

    public void render(SpriteBatch batch) {
	batch.draw(region, x, y, SIZE, SIZE);
    }
    
    private void move(int x, int y) {	
	this.x += x * Tile.SIZE;
	this.y += y * Tile.SIZE;
	
	
	if(getTileID() == Tile.NO_WALK)
	{
	    this.x -= x * Tile.SIZE;
	    this.y -= y * Tile.SIZE;
	} else if(getTileID() == Tile.TRAP) {
	    SoundManager.play(SoundManager.FAIL);
	    ((MainGame)Main.instance.getStateMachine().getCurrentState()).resetLevel();
	} else if(getTileID() == Tile.KEY) {
	    setTile(Tile.PATH);
	    SoundManager.play(SoundManager.PICKUP);
	    hasKey = true;
	} else if(getTileID() == Tile.LOCK) {
	    if(hasKey) {
		setTile(Tile.PATH);
		hasKey = false;
	    } else {
		this.x -= x * Tile.SIZE;
		this.y -= y * Tile.SIZE;
	    }
	}
    }
    
    private int getTileID() {
	if(getTile() == null)
	    return Tile.NO_WALK;
	
	return getTile().getId();
    }
    
    private void setTile(int id) {
	if(getTile() == null)
	    return;
	
	getTile().setId(id, world);
    }
    
    private Tile getTile() {
	int x = (this.x + SIZE / 2 - Tile.SIZE / 2) / Tile.SIZE;
	int y = (this.y + SIZE / 2 - Tile.SIZE / 2) / Tile.SIZE;
	
	return world.getTile(x, y);
    }
    
    public int getCenterX() {
	return x + SIZE / 2;
    }
    
    public int getCenterY() {
	return y + SIZE / 2;
    }
    
    public boolean isOnEndTile() {
	return getTileID() == Tile.END_POS;
    }
    
    public void setPos(Vector2 pos) {
	this.x = (int) pos.x + Tile.SIZE / 2 - SIZE / 2;
	this.y = (int) pos.y + Tile.SIZE / 2 - SIZE / 2;
	
	region = Spritesheet.entities.get(0, 0);
	hasKey = false;
    }

    public boolean hasKey() {
	return hasKey;
    }
    
    @Override
    public boolean keyDown(int keycode) {
	switch (keycode) {
	case Keys.UP:
	case Keys.W:
	    move(0, 1);
	    region = Spritesheet.entities.get(3, 0);
	    break;
	case Keys.DOWN:
	case Keys.S:
	    move(0, -1);
	    region = Spritesheet.entities.get(0, 0);
	    break;
	case Keys.RIGHT:
	case Keys.D:
	    move(1, 0);
	    region = Spritesheet.entities.get(1, 0);
	    break;
	case Keys.LEFT:
	case Keys.A:
	    move(-1, 0);
	    region = Spritesheet.entities.get(2, 0);
	    break;
	}
	return false;
    }

    @Override
    public boolean keyTyped(char keycode) {
	return false;
    }

    @Override
    public boolean keyUp(int keycode) {
	return false;
    }

    @Override
    public boolean mouseMoved(int arg0, int arg1) {
	return false;
    }

    @Override
    public boolean scrolled(int arg0) {
	return false;
    }

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
	return false;
    }

    @Override
    public boolean touchDragged(int arg0, int arg1, int arg2) {
	return false;
    }

    @Override
    public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
	return false;
    }

}
