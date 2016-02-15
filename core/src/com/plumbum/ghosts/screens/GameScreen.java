package com.plumbum.ghosts.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.plumbum.ghosts.levels.LevelGrid;
import com.plumbum.ghosts.render.LevelRenderer;

/**
 * Created by jesse on 2/12/2016.
 */
public class GameScreen extends GhostsScreen{
	LevelGrid grid;
	LevelRenderer renderer;

	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		grid = new LevelGrid();
		renderer = new LevelRenderer(grid);
	}

	@Override
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
