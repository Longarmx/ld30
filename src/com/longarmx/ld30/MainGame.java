package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.longarmx.ld30.World.WorldPosition;

public class MainGame extends State{
    
    private World leftWorld, rightWorld;
    private OrthographicCamera camera;
    private Texture divider;
    
    public void create() {
	InputMultiplexer multiplexer = new InputMultiplexer();
	leftWorld = new World(WorldPosition.LEFT, multiplexer);
	rightWorld = new World(WorldPosition.RIGHT, multiplexer);
	Gdx.input.setInputProcessor(multiplexer);
	
	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	camera.update();
	
	divider = new Texture(Gdx.files.local("res/textures/divider.png"));
	
	Gdx.gl.glClearColor(.37f, .6f, 1f, 1);
    }
    
    public void update() {
	leftWorld.update();
	rightWorld.update();
	
	if(leftWorld.win() ^ rightWorld.win()) {
	    resetLevel();
	    SoundManager.play(SoundManager.FAIL);
	}
	
	if(leftWorld.win() && rightWorld.win()) {
	    leftWorld.nextLevel();
	    rightWorld.nextLevel();
	    SoundManager.play(SoundManager.LEVEL_UP);
	}
	
	if(Gdx.input.isKeyPressed(Keys.R)) {
	    resetLevel();
	}
	
	if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
	    Main.instance.getStateMachine().removeStateFromTop();
	}
    }
    
    public void render(SpriteBatch batch) {
	Gdx.gl.glEnable(GL10.GL_SCISSOR_TEST);
	
	leftWorld.render(batch);
	batch.flush();
	rightWorld.render(batch);
	batch.flush();
	
	Gdx.gl.glDisable(GL10.GL_SCISSOR_TEST);
	
	batch.setProjectionMatrix(camera.combined);
	batch.draw(divider, Gdx.graphics.getWidth()/2 - divider.getWidth()/2, 0);
    }

    public void dispose() {
	divider.dispose();
    }
    
    public void resetLevel() {
	leftWorld.resetLevel();
	rightWorld.resetLevel();
    }

}
