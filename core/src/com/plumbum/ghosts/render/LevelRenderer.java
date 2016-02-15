package com.plumbum.ghosts.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.plumbum.ghosts.levels.LevelGrid;
import com.plumbum.ghosts.levels.LevelNode;

import static com.plumbum.ghosts.levels.LevelNode.NodeType.*;

/**
 * Created by jesse on 2/11/2016.
 */
public class LevelRenderer {
	private SpriteCache cache = new SpriteCache();
	private int cacheId;
	private SpriteBatch batch = new SpriteBatch();
	private LevelGrid grid;
	TextureRegion[] nodeTextures;
	OrthographicCamera camera;
	private static final int BOX_HEIGHT = 64;
	private static final int BOX_WIDTH = 64;

	private int levelHeight;
	private int levelWidth;

	public LevelRenderer(LevelGrid grid) {
		this.grid = grid;
		levelWidth = grid.WIDTH*BOX_WIDTH;
		levelHeight = grid.HEIGHT*BOX_HEIGHT;
		camera = new OrthographicCamera(levelWidth, levelWidth*16/9);
		camera.position.set(levelWidth/2, levelHeight/2, 0);
		camera.update();
		nodeTextures = new TextureRegion[15];
		Texture mapTexture = new Texture(Gdx.files.internal("levelBlocks.png"));
		TextureRegion[] split = new TextureRegion(mapTexture).split(BOX_WIDTH, BOX_HEIGHT)[0];
		nodeTextures[DEADEND_DOWN.getIndex()] = split[6];
		nodeTextures[DEADEND_UP.getIndex()] = new TextureRegion(split[6]);
		nodeTextures[DEADEND_UP.getIndex()].flip(false, true);
		nodeTextures[DEADEND_RIGHT.getIndex()] = split[7];
		nodeTextures[DEADEND_LEFT.getIndex()] = new TextureRegion(split[7]);
		nodeTextures[DEADEND_LEFT.getIndex()].flip(true, false);
		nodeTextures[STREET_HORIZONTAL.getIndex()] = split[0];
		nodeTextures[STREET_VERTICAL.getIndex()] = split[1];
		nodeTextures[BEND_DOWN_RIGHT.getIndex()] = split[2];
		nodeTextures[BEND_DOWN_LEFT.getIndex()] = new TextureRegion(split[2]);
		nodeTextures[BEND_DOWN_LEFT.getIndex()].flip(true, false);
		nodeTextures[BEND_UP_RIGHT.getIndex()] = new TextureRegion(split[2]);
		nodeTextures[BEND_UP_RIGHT.getIndex()].flip(false, true);
		nodeTextures[BEND_UP_LEFT.getIndex()] = new TextureRegion(split[2]);
		nodeTextures[BEND_UP_LEFT.getIndex()].flip(true, true);
		nodeTextures[TEE_DOWN.getIndex()] = split[3];
		nodeTextures[TEE_UP.getIndex()] = new TextureRegion(split[3]);
		nodeTextures[TEE_UP.getIndex()].flip(false, true);
		nodeTextures[TEE_LEFT.getIndex()] = split[4];
		nodeTextures[TEE_RIGHT.getIndex()] = new TextureRegion(split[4]);
		nodeTextures[TEE_RIGHT.getIndex()].flip(true, false);
		nodeTextures[INTERSECTION.getIndex()] = split[5];
		cacheLevelpath();
	}

	private void cacheLevelpath() {
		cache.setProjectionMatrix(camera.combined);
		cache.beginCache();
		for (LevelNode node : grid.getPathNodes()) {
			cache.add(nodeTextures[node.getNodeType().getIndex()], node.getX()*BOX_WIDTH, levelHeight-node.getY()*BOX_HEIGHT);
		}
		cacheId = cache.endCache();
	}

	public void render(float delta) {
		cache.setProjectionMatrix(camera.combined);
		cache.begin();
		cache.draw(cacheId);
		cache.end();
	}
}
