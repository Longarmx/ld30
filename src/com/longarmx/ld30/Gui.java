package com.longarmx.ld30;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gui {
    
    private ArrayList<Component> components;
    
    public static BitmapFont font = new BitmapFont(Gdx.files.local("res/font/default.fnt"), false);;
    
    protected OrthographicCamera camera;
    
    public Gui() {
	components = new ArrayList<Component>();
	
	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	camera.update();
    }
    
    public void update() {
	for(int i = 0; i < components.size(); i++) {
	    components.get(i).update();
	}
    }
    
    public void render(SpriteBatch batch) {
	batch.setProjectionMatrix(camera.combined);
	
	for(int i = 0; i < components.size(); i++) {
	    components.get(i).render(batch);
	}
    }
    
    public void add(Component c) {
	components.add(c);
    }
    
    public Component get(int i) {
	return components.get(i);
    }

}
