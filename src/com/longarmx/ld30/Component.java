package com.longarmx.ld30;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Component {
    
    private TextureRegion region;
    
    protected int x, y, width, height;
    
    public Component(TextureRegion region, int x, int y) {
	this(region, x, y, region.getRegionWidth(), region.getRegionHeight());
    }
    
    public Component(TextureRegion region, int x, int y, int width, int height) {
	this.region = region;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }
    
    public void update() {
	
    }
    
    public void render(SpriteBatch batch) {
	batch.draw(region, x, y, width, height);
    }

}
