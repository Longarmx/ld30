package com.longarmx.ld30;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateMachine {
    
    private Stack<State> states;
    
    public StateMachine(State startingState) {
	this.states = new Stack<State>();
	
	addStateToTop(startingState);
    }
    
    public void addStateToTop(State state) {
	states.push(state);
	getCurrentState().create();
    }
    
    public void removeStateFromTop() {
	getCurrentState().dispose();
	states.pop();
    }
    
    public void removeAllStates() {
	states.removeAllElements();
    }
    
    public State getCurrentState() {    
	return states.lastElement();
    }
    
    public void update() {
	getCurrentState().update();
    }
    
    public void render(SpriteBatch batch) {
	getCurrentState().render(batch);
    }
    
    public void dispose() {
	getCurrentState().dispose();
    }
}
