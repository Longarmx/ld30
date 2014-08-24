package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Spritesheet {
    
    public static final Spritesheet world = new Spritesheet("world", 32);
    public static final Spritesheet entities = new Spritesheet("entities", 24);
    public static final Spritesheet gui = new Spritesheet("gui", 128);
    
    private TextureRegion[][] regions;
    private Texture texture;
    private String name;
    
    public Spritesheet(String name, int cellSize) {
	this.name = "res/textures/" + name + ".png";	
	
	regions = new TextureRegion[getTexture().getWidth() / cellSize][getTexture().getHeight() / cellSize];
	
	for(int i = 0; i < regions.length; i++) {
	    for(int j = 0; j < regions[0].length; j++) {
		regions[i][j] = getRaw(i * cellSize, j * cellSize, cellSize, cellSize);
	    }
	}
    }
    
    public TextureRegion getRaw(int x, int y, int width, int height) {
	return new TextureRegion(getTexture(), x, y, width, height);
    }
    
    public TextureRegion get(int x, int y) {
	return regions[x][y];
    }
    
    public Texture getTexture() {
	if(texture == null)
	    texture = new Texture(Gdx.files.local(name));
	
	return texture;
    }
    
    public int getCellsX() {
	return regions.length;
    }
    
    public int getCellsY() {
	return regions[0].length;
    }
    
    @Override
    public void finalize() {
	getTexture().dispose();
    }

}
