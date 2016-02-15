package com.plumbum.ghosts;

import com.badlogic.gdx.Game;
import com.plumbum.ghosts.screens.GameScreen;

/**
 * Created by jesse on 2/14/2016.
 */
public class Ghosts extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}
