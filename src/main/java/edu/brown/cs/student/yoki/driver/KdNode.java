package edu.brown.cs.student.yoki.driver;

/**
 * A node with coordinates and two children to be used in a KdTree.
 */
public abstract class KdNode {

  private int[] coordinates;
  private KdNode left = null;
  private KdNode right = null;
  private int depth;

  /**
   * Return the distance between the KdNode and an object.
   * @param o An object, such as an array of coordinates
   * @return The distance as a double
   */
  public abstract double distance(Object o);

  /**
   * Accessor for the coordinates.
   * @return coordinates
   */
  public int[] getCoords() {
    return coordinates;
  }

  /**
   * Setter for coordinates, creates a new array object.
   * @param coords array of doubles
   */
  public void setCoords(int[] coords) {
    coordinates = coords;
  }

  /**
   * Accessor for the left child.
   * @return The left child
   */
  public KdNode getLeft() {
    return left;
  }

  /**
   * Accessor for the right child.
   * @return The right child
   */
  public KdNode getRight() {
    return right;
  }

  /**
   * Setter for the left child.
   * @param child Another KdNode
   */
  public void setLeft(KdNode child) {
    left = child;
  }

  /**
   * Setter for the right child.
   * @param child Another KdNode
   */
  public void setRight(KdNode child) {
    right = child;
  }

  /**
   * Setter for depth.
   * @param depth An integer
   */
  public void setDepth(int depth) {
    this.depth = depth;
  }

  /**
   * Getter for depth.
   * @return An integer
   */
  public int getDepth() {
    return depth;
  }

}
