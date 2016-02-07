package com.plumbum.ghosts.levels;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jesse on 1/29/2016.
 */
public class LevelNode {
	private LevelNode up;
	private LevelNode down;
	private LevelNode left;
	private LevelNode right;
	private boolean isWall;

	private int x;
	private int y;

	private NodeType nodeType;

	public LevelNode(int x, int y, boolean isWall) {
		this.isWall = isWall;
		this.x = x;
		this.y = y;
		this.up = this.down = this.left = this.right = null;
	}

	public static enum NodeType {
		WALL(0, 0),
		DEADEND_UP(1, 0),
		DEADEND_RIGHT(1, 90),
		DEADEND_DOWN(1, 180),
		DEADEND_LEFT(1, 270),
		STREET_VERTICAL(2, 0),
		STREET_HORIZONTAL(2, 90),
		BEND_UP_LEFT(3, 0),
		BEND_UP_RIGHT(3, 90),
		BEND_DOWN_RIGHT(3, 180),
		BEND_DOWN_LEFT(3, 270),
		TEE_UP(4, 0),
		TEE_RIGHT(4, 90),
		TEE_DOWN(4, 180),
		TEE_LEFT(4, 270),
		INTERSECTION(5, 0);

		private int pictureId;
		private int rotation;

		NodeType(int pictureId, int rotation) {
			this.pictureId = pictureId;
			this.rotation = rotation;
		}

		public int getPictureId() {
			return pictureId;
		}
		public int getRotation() {
			return rotation;
		}
	}

	public LevelNode setUp(LevelNode up) {
		this.up = up;
		up.setDown(this);
		calculateType();
		return this;
	}

	public LevelNode setDown(LevelNode down) {
		this.down = down;
		down.setUp(this);
		calculateType();
		return this;
	}

	public LevelNode setLeft(LevelNode left) {
		this.left = left;
		left.setRight(this);
		calculateType();
		return this;
	}

	public LevelNode setRight(LevelNode right) {
		this.right = right;
		right.setLeft(this);
		calculateType();
		return this;
	}

	private void calculateType() {
		if (this.up != null && this.right != null && this.down != null && this.left != null)
			this.nodeType = NodeType.INTERSECTION;
		else if (this.up == null && this.right != null && this.down != null && this.left != null)
			this.nodeType = NodeType.TEE_DOWN;
		else if (this.up != null && this.right == null && this.down != null && this.left != null)
			this.nodeType = NodeType.TEE_LEFT;
		else if (this.up != null && this.right != null && this.down == null && this.left != null)
			this.nodeType = NodeType.TEE_UP;
		else if (this.up != null && this.right != null && this.down != null && this.left == null)
			this.nodeType = NodeType.TEE_RIGHT;
		else if (this.up == null && this.right == null && this.down != null && this.left != null)
			this.nodeType = NodeType.BEND_DOWN_LEFT;
		else if (this.up == null && this.right != null && this.down == null && this.left != null)
			this.nodeType = NodeType.STREET_HORIZONTAL;
		else if (this.up == null && this.right != null && this.down != null && this.left == null)
			this.nodeType = NodeType.BEND_DOWN_RIGHT;
		else if (this.up != null && this.right == null && this.down == null && this.left != null)
			this.nodeType = NodeType.BEND_UP_LEFT;
		else if (this.up != null && this.right == null && this.down != null && this.left == null)
			this.nodeType = NodeType.STREET_VERTICAL;
		else if (this.up != null && this.right != null && this.down == null && this.left == null)
			this.nodeType = NodeType.BEND_UP_RIGHT;
		else if (this.up == null && this.right == null && this.down == null && this.left != null)
			this.nodeType = NodeType.DEADEND_LEFT;
		else if (this.up == null && this.right == null && this.down != null && this.left == null)
			this.nodeType = NodeType.DEADEND_DOWN;
		else if (this.up == null && this.right != null && this.down == null && this.left == null)
			this.nodeType = NodeType.DEADEND_RIGHT;
		else if (this.up != null && this.right == null && this.down == null && this.left == null)
			this.nodeType = NodeType.DEADEND_UP;
		else
			this.nodeType = NodeType.WALL;
	}

	public LevelNode getUp() {
		return up;
	}

	public LevelNode getDown() {
		return down;
	}

	public LevelNode getLeft() {
		return left;
	}

	public LevelNode getRight() {
		return right;
	}

	public List<LevelNode> getNeighbors() {
		return Arrays.asList(up, right, down, left);
	}

	public boolean isWall() {
		return isWall;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
