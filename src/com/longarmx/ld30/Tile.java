package com.longarmx.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    
    public static final int SIZE = 64;
    
    public static final int NO_WALK = 1;
    public static final int PATH = 0;
    public static final int START_POS = 2;
    public static final int END_POS = 3;
    public static final int TRAP = 4;
    public static final int KEY = 5;
    public static final int LOCK = 6;
    
    private int id, x, y;
    private TextureRegion region;
    
    public Tile(int id, int x, int y) {
	this.id = id;
	this.x = x * SIZE;
	this.y = y * SIZE;
	
	region = Spritesheet.world.get(0, id);
    }
    
    public void updateRegion(World world) {
	int connect = 0;
	
	if(sameTile(world, 0, 1))
	    connect += 1;
	if(sameTile(world, 1, 0))
	    connect += 2;
	if(sameTile(world, 0, -1))
	    connect += 4;
	if(sameTile(world, -1, 0))
	    connect += 8;
	
	region = Spritesheet.world.get(connect, id);
    }
    
    private boolean sameTile(World world, int x, int y) {
	x *= SIZE;
	y *= SIZE;
	Tile tile = world.getTile((this.x + x) / SIZE, (this.y + y) / SIZE);
	
	if(tile == null)
	    return false;
	
	if(tile.getId() != Tile.NO_WALK)
	    return true;
	
	return tile.getId() == id;
    }
    
    public void render(SpriteBatch batch) {
	batch.draw(region, x, y, SIZE, SIZE);
    }
    
    public void setId(int id, World world) {
	this.id = id;
	
	if(world != null)
	    world.connectTiles();
    }
    
    public int getId() {
	return id;
    }

}
