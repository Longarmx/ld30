package com.longarmx.ld30;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener{
    
    public static final Main instance = new Main();
    
    public State mainMenu, mainGame, options, story, ending;
    
    private SpriteBatch batch;
    private StateMachine stateMachine;
    
    @Override
    public void create() {
	batch = new SpriteBatch();	
	
	mainMenu = new MainMenu();
	mainGame = new MainGame();
	options = new Options();
	story = new Story();
	ending = new Ending();
	
	stateMachine = new StateMachine(story);
    }
    
    public void update() {
	stateMachine.update();
    }
    
    @Override
    public void render() {
	update();
	
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	batch.begin();
	
	stateMachine.render(batch);	
	
	batch.end();
    }

    @Override
    public void dispose() {
	batch.dispose();
    }
    
    public StateMachine getStateMachine() {
	return stateMachine;
    }
    

    @Override
    public void pause() {
    }

    @Override
    public void resize(int arg0, int arg1) {
    }

    @Override
    public void resume() {
    }
    
    public static void main(String[] args) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.width = 1280;
	config.height = 720;
	config.title = "The Unfortunate Potato";
	config.vSyncEnabled = true;
	config.resizable = false;
	config.useGL20 = true;
	new LwjglApplication(instance, config);
    }

}
