package com.longarmx.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    
    public static float soundVolume = 0.5f;
    public static boolean muted = false;
    
    public static final Sound LEVEL_UP = loadSound("levelUp");
    public static final Sound PICKUP = loadSound("pickup");
    public static final Sound CLICK = loadSound("click");
    public static final Sound FAIL = loadSound("fail");
    
    public static void play(Sound sound) {
	if(muted)
	    return;
	
	sound.play(soundVolume);
    }
    
    private static Sound loadSound(String name) {
	return Gdx.audio.newSound(Gdx.files.local("res/sounds/" + name + ".wav"));
    }

}
