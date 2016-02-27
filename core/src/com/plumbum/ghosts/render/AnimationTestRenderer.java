package com.plumbum.ghosts.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.plumbum.ghosts.levels.LevelGrid;

/**
 * Created by jesse on 2/26/2016.
 */
public class AnimationTestRenderer {
	private SpriteCache cache = new SpriteCache();
	private int cacheId;
	private SpriteBatch batch = new SpriteBatch();
	private LevelGrid grid;
	TextureRegion[] nodeTextures;
	OrthographicCamera camera;
	Animation porkman;

	private int levelHeight;
	private int levelWidth;

	private float stateTime = 0f;

	public AnimationTestRenderer() {
		levelWidth = 128;
		levelHeight = 128;
		camera = new OrthographicCamera(levelWidth, levelWidth*16/9);
		camera.position.set(levelWidth/2, levelHeight/2, 0);
		camera.update();
		Texture animationTexture = new Texture(Gdx.files.internal("porkman.png"));
		TextureRegion[] split = new TextureRegion(animationTexture).split(64, 64)[0];
		porkman = new Animation(.033f, split[0], split[1], split[2], split[3], split[2], split[1]);
		batch.setProjectionMatrix(camera.combined);

	}

	public void render(float delta) {
		stateTime += delta;
		batch.begin();
		batch.draw(porkman.getKeyFrame(stateTime, true), levelWidth/2, levelHeight/2);
		batch.end();
	}


}
