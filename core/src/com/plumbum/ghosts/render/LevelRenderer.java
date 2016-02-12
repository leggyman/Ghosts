package com.plumbum.ghosts.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.plumbum.ghosts.levels.LevelGrid;

import static com.plumbum.ghosts.levels.LevelNode.NodeType.*;

/**
 * Created by jesse on 2/11/2016.
 */
public class LevelRenderer {
	private SpriteBatch batch = new SpriteBatch();
	private LevelGrid grid;
	TextureRegion[] nodeTextures;
	OrthographicCamera camera;

	public void init() {
		this.camera = new OrthographicCamera();
		nodeTextures = new TextureRegion[15];
		Texture mapTexture = new Texture(Gdx.files.internal("levelBlocks.png"));
		TextureRegion[] split = new TextureRegion(mapTexture).split(64, 64)[0];
		nodeTextures[DEADEND_DOWN.getIndex()] = split[6];
		nodeTextures[DEADEND_UP.getIndex()].setRegion(split[6]);
		nodeTextures[DEADEND_UP.getIndex()].flip(false, true);
		nodeTextures[DEADEND_RIGHT.getIndex()] = split[6];
		nodeTextures[DEADEND_LEFT.getIndex()].setRegion(split[6]);
		nodeTextures[DEADEND_LEFT.getIndex()].flip(true, false);
		nodeTextures[STREET_HORIZONTAL.getIndex()] = split[0];
		nodeTextures[STREET_VERTICAL.getIndex()] = split[1];
		nodeTextures[BEND_DOWN_RIGHT.getIndex()] = split[2];
		nodeTextures[BEND_DOWN_LEFT.getIndex()].setRegion(split[2]);
		nodeTextures[BEND_DOWN_LEFT.getIndex()].flip(true, false);
		nodeTextures[BEND_UP_RIGHT.getIndex()].setRegion(split[2]);
		nodeTextures[BEND_UP_RIGHT.getIndex()].flip(false, true);
		nodeTextures[BEND_UP_LEFT.getIndex()].setRegion(split[2]);
		nodeTextures[BEND_UP_LEFT.getIndex()].flip(true, true);
		nodeTextures[TEE_DOWN.getIndex()] = split[3];
		nodeTextures[TEE_UP.getIndex()].setRegion(split[3]);
		nodeTextures[TEE_UP.getIndex()].flip(false, true);
		nodeTextures[TEE_RIGHT.getIndex()] = split[4];
		nodeTextures[TEE_LEFT.getIndex()].setRegion(split[4]);
		nodeTextures[TEE_LEFT.getIndex()].flip(true, false);
		nodeTextures[INTERSECTION.getIndex()] = split[5];
	}

	public LevelRenderer(LevelGrid grid) {
		this.grid = grid;
	}

	public void render() {
		batch.begin();

	}
}
