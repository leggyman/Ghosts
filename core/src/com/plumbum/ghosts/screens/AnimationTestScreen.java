package com.plumbum.ghosts.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.plumbum.ghosts.levels.LevelGrid;
import com.plumbum.ghosts.render.AnimationTestRenderer;
import com.plumbum.ghosts.render.LevelRenderer;

/**
 * Created by jesse on 2/26/2016.
 */
public class AnimationTestScreen extends GhostsScreen {
	private AnimationTestRenderer renderer;

	public AnimationTestScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		renderer = new AnimationTestRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		delta = Gdx.graphics.getDeltaTime();
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
