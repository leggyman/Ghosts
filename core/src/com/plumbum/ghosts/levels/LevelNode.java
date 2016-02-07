package com.plumbum.ghosts.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jesse on 1/29/2016.
 */
public class LevelNode {
	private LevelNode up;
	private LevelNode down;
	private LevelNode left;
	private LevelNode right;
	private boolean isWall;
    private Set<LevelNode> neighbors;

	private int x;
	private int y;

	private NodeType nodeType;

	public LevelNode(int x, int y, boolean isWall) {
		this.isWall = isWall;
		this.x = x;
		this.y = y;
		this.up = this.down = this.left = this.right = null;
        if (this.isWall)
            this.nodeType = NodeType.WALL;
        neighbors = new HashSet<>();
	}

	public static enum NodeType {
		WALL(0, 0, " "),
		DEADEND_UP(1, 0, new String(Character.toChars(0x257B))),
		DEADEND_RIGHT(1, 90, new String(Character.toChars(0x2578))),
		DEADEND_DOWN(1, 180, new String(Character.toChars(0x2579))),
		DEADEND_LEFT(1, 270, new String(Character.toChars(0x257A))),
		STREET_VERTICAL(2, 0, new String(Character.toChars(0x2503))),
		STREET_HORIZONTAL(2, 90, new String(Character.toChars(0x2501))),
		BEND_UP_LEFT(3, 0, new String(Character.toChars(0x2513))),
		BEND_UP_RIGHT(3, 90, new String(Character.toChars(0x250F))),
		BEND_DOWN_RIGHT(3, 180, new String(Character.toChars(0x2517))),
		BEND_DOWN_LEFT(3, 270, new String(Character.toChars(0x251B))),
		TEE_UP(4, 0, new String(Character.toChars(0x253B))),
		TEE_RIGHT(4, 90, new String(Character.toChars(0x2523))),
		TEE_DOWN(4, 180, new String(Character.toChars(0x2533))),
		TEE_LEFT(4, 270, new String(Character.toChars(0x252B))),
		INTERSECTION(5, 0, new String(Character.toChars(0x254B)));

		private int pictureId;
		private int rotation;
		private String render;

		NodeType(int pictureId, int rotation, String render) {
			this.pictureId = pictureId;
			this.rotation = rotation;
			this.render = render;
		}

		public int getPictureId() {
			return pictureId;
		}
		public int getRotation() {
			return rotation;
		}
		public String getRender() {
			return render;
		}
	}

	public LevelNode setUp(LevelNode up) {
        if (this.up != up) {
            this.up = up;
            up.setDown(this);
            neighbors.add(up);
            calculateType();
        }
		return this;
	}

	public LevelNode setDown(LevelNode down) {
        if (this.down != down) {
            this.down = down;
            down.setUp(this);
            neighbors.add(down);
            calculateType();
        }
		return this;
	}

	public LevelNode setLeft(LevelNode left) {
        if (this.left != left) {
            this.left = left;
            left.setRight(this);
            neighbors.add(left);
            calculateType();
        }
		return this;
	}

	public LevelNode setRight(LevelNode right) {
        if (this.right != right) {
            this.right = right;
            right.setLeft(this);
            neighbors.add(right);
            calculateType();
        }
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
			this.nodeType = NodeType.BEND_UP_LEFT;
		else if (this.up == null && this.right != null && this.down == null && this.left != null)
			this.nodeType = NodeType.STREET_HORIZONTAL;
		else if (this.up == null && this.right != null && this.down != null && this.left == null)
			this.nodeType = NodeType.BEND_UP_RIGHT;
		else if (this.up != null && this.right == null && this.down == null && this.left != null)
			this.nodeType = NodeType.BEND_DOWN_LEFT;
		else if (this.up != null && this.right == null && this.down != null && this.left == null)
			this.nodeType = NodeType.STREET_VERTICAL;
		else if (this.up != null && this.right != null && this.down == null && this.left == null)
			this.nodeType = NodeType.BEND_DOWN_RIGHT;
		else if (this.up == null && this.right == null && this.down == null && this.left != null)
			this.nodeType = NodeType.DEADEND_RIGHT;
		else if (this.up == null && this.right == null && this.down != null && this.left == null)
			this.nodeType = NodeType.DEADEND_UP;
		else if (this.up == null && this.right != null && this.down == null && this.left == null)
			this.nodeType = NodeType.DEADEND_LEFT;
		else if (this.up != null && this.right == null && this.down == null && this.left == null)
			this.nodeType = NodeType.DEADEND_DOWN;
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

	public Set<LevelNode> getNeighbors() {
		return neighbors;
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
