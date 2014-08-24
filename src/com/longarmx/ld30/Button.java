package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Button extends Component{
    
    private static BitmapFont font;
    
    private TextureRegion hover, down;
    private Clickable clickable;
    private ButtonMode mode;
    private boolean lastDown;
    private String text;

    public Button(int x, int y, Clickable clickable) {
	super(Spritesheet.gui.get(0, 0), x, y);
	
	mode = ButtonMode.DEFAULT;
	this.clickable = clickable;
	
	hover = Spritesheet.gui.get(1, 0);
	down = Spritesheet.gui.get(2, 0);
	
	if(font == null) {
	    font = new BitmapFont(Gdx.files.local("res/font/default.fnt"), false);
	    font.setScale(0.5f);
	}
    }
    
    public Button(int x, int y, int width, int height, Clickable clickable) {
	super(Spritesheet.gui.get(0, 0), x, y, width, height);
	
	mode = ButtonMode.DEFAULT;
	this.clickable = clickable;
	
	hover = Spritesheet.gui.get(1, 0);
	down = Spritesheet.gui.get(2, 0);
	
	if(font == null) {
	    font = new BitmapFont(Gdx.files.local("res/font/default.fnt"), false);
	    font.setScale(0.5f);
	}
    }
    
    public void update() {
	super.update();
	
	Vector2 mouseCoords = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
	if(intersects(mouseCoords)) {
	    mode = ButtonMode.HOVER;
	    
	    if(Gdx.input.isButtonPressed(0)) {
		mode = ButtonMode.DOWN;
		
		if(!lastDown) {
		    clickable.onClick();
		    SoundManager.play(SoundManager.CLICK);
		}
		
		lastDown = true;
	    } else {
		
		if(lastDown) {
		    clickable.onRelease();
		}
		
		lastDown = false;
	    }
	    
	} else {
	    mode = ButtonMode.DEFAULT;
	}
	
    }
    
    public void render(SpriteBatch batch) {
	switch(mode) {
	case DEFAULT:
	    super.render(batch);
	    break;
	case HOVER:
	    batch.draw(hover, x, y, width, height);
	    break;
	case DOWN:
	    batch.draw(down, x, y, width, height);
	    break;
	}
	
	if(text != null) {
	    font.draw(batch, text, x + width/2 - font.getBounds(text).width/2, y + height/2 + font.getBounds(text).height/2);
	}
    }
    
    private boolean intersects(Vector2 point) {
	return !(point.x < x || point.x > x + width || point.y < y || point.y > y + height);
    }
    
    public Button setText(String text) {
	this.text = text;
	
	return this;
    }
    
    public enum ButtonMode {
	DEFAULT, HOVER, DOWN
    }

}
