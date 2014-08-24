package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ending extends State {
    
    private int time;
    
    public void create() {
	time = 0;
	Gdx.gl.glClearColor(1, 1, 1, 1);
    }
    
    public void update() {
	time++;
	
	if(time > 22 * 60) {
	    Main.instance.getStateMachine().addStateToTop(Main.instance.mainMenu);
	}
    }
    
    /**
     * 
     * 
     * WARNING: Don't ever code like this!
     * 
     * KEEP YOUR EYES AWAY!
     * 
     * An Object Oriented approach would be your friend. This is my enemy.
     * 
     * 
     */
    public void render(SpriteBatch batch) {
	TextureRegion region = Spritesheet.gui.get(0, 3);
	
	String text1 = "In the end, the potato\ncame to a realization.";
	String text2 = "His journey had brought him\nto a single conclusion.";
	String text3 = "That he was still himself.\nHe still had his spudfriends.";
	String text4 = "He was better off not meeting\nhis counterpart;";
	String text5 = "Because if he did, then he\nwould EXPLODE!";

	Gui.font.setScale(0.5f);
	Gui.font.drawMultiLine(batch, text1, 50, Gdx.graphics.getHeight() - 50);

	if (time < 2 * 60) {
	    if(time > 1 * 60)
		batch.setColor(1, 1, 1, 1 - time / (60f));
	    
	    TextBounds bounds = Gui.font.getBounds(text1);
	    batch.draw(region, 50, Gdx.graphics.getHeight() - 50 - bounds.height * 3, bounds.width * 2, bounds.height * 4);
	    batch.setColor(1, 1, 1, 1);
	}

	Gui.font.drawMultiLine(batch, text2, 200,
		Gdx.graphics.getHeight() - 175);
	
	if (time < 4 * 60) {
	    if(time > 3 * 60)
		batch.setColor(1, 1, 1, 1 - (time) / (60f));
	    
	    TextBounds bounds = Gui.font.getBounds(text2);
	    batch.draw(region, 200, Gdx.graphics.getHeight() - 175 - bounds.height * 3, bounds.width * 2, bounds.height * 4);
	    batch.setColor(1, 1, 1, 1);
	}
	
	Gui.font.drawMultiLine(batch, text3, 350,
		Gdx.graphics.getHeight() - 300);
	
	if (time < 6 * 60) {
	    if(time > 5 * 61)
		batch.setColor(1, 1, 1, 1 - (time) / (60f));
	    
	    TextBounds bounds = Gui.font.getBounds(text3);
	    batch.draw(region, 350, Gdx.graphics.getHeight() - 300 - bounds.height * 3, bounds.width * 2, bounds.height * 4);
	    batch.setColor(1, 1, 1, 1);
	}
	
	Gui.font.drawMultiLine(batch, text4, 500,
		Gdx.graphics.getHeight() - 450);
	
	if (time < 8 * 60) {
	    if(time > 7 * 61)
		batch.setColor(1, 1, 1, 1 - (time) / (60f));
	    
	    TextBounds bounds = Gui.font.getBounds(text4);
	    batch.draw(region, 500, Gdx.graphics.getHeight() - 450 - bounds.height * 5, bounds.width * 2, bounds.height * 6);
	    batch.setColor(1, 1, 1, 1);
	}
	
	Gui.font.drawMultiLine(batch, text5, 650,
		Gdx.graphics.getHeight() - 600);
	
	if (time < 10 * 60) {
	    if(time > 9 * 61)
		batch.setColor(1, 1, 1, 1 - (time) / (60f));
	    
	    TextBounds bounds = Gui.font.getBounds(text3);
	    batch.draw(region, 650, Gdx.graphics.getHeight() - 600 - bounds.height * 3, bounds.width * 2, bounds.height * 4);
	    batch.setColor(1, 1, 1, 1);
	}
	
	if(time > 14 * 60) {
	    if(time < 16 * 60) {
		batch.setColor(1, 1, 1, (time - 14*60) / 120f);
	    }
	    batch.draw(region, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    batch.setColor(1, 1, 1, 1);
	}
	
	Gui.font.setScale(1f);
	
	if(time > 18 * 60) {
	    Gui.font.draw(batch, "The End", Gdx.graphics.getWidth()/2 - Gui.font.getBounds("The End").width/2, Gdx.graphics.getHeight()/2 + Gui.font.getBounds("The End").height/2);
	}
	
    }

}
