package edu.brown.cs.student.yoki.driver;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A KdTree of Nodes.
 * @param <N> A node extending KdNode
 */
public class TreeFunction<N extends KdNode> {
  private N root = null;
  private int dim;
  private ArrayList<N> list = new ArrayList<>();

  // Fields for search functions
  private int k;
  private double r;
  private int[] coords = new int[5];
  private N nodeIgnored;
  private final ArrayList<N> found = new ArrayList<>();
  private AlexKDTree<N> alexKDTree;

  /**
   * Constructs a KD tree from its list of nodes; nodes remain
   * in the list but are assigned left and right children.
   * @param nodeList List of nodes
   */
  public void listToTree(ArrayList<N> nodeList) {
    // start by getting median along first dim, root
    // recur down tree, alternating dimension
    // pass down range to look for the median in
    this.list = nodeList;

    if (nodeList.size() == 0) {
      root = null;
      return;
    }

    dim = list.get(0).getCoords().length;
    alexKDTree = new AlexKDTree<>(nodeList, dim);
    root = alexKDTree.getRoot();
  }

  /**
   * Performs a KD Tree search and prints the found nodes in order.
   */
  public void searchAndPrint() {
    kdNeighbors();
    printFound();
  }

  private void printFound() {
    for (N node : found) {
      System.out.println(node.toString());
    }
  }

  /**
   * Performs a KD nearest neighbors search based on fields.
   */
  public void kdNeighbors() {
    found.clear();
    if (root == null || k == 0) {
      return;
    }
    alexKDTree.setNeighbor(k);
    alexKDTree.setRadius(r);
    PriorityQueue<N> foundQ = alexKDTree.search(nodeIgnored, alexKDTree.getRoot(), coords);
    heapToList(foundQ);
  }

  /**
   * turns the heap to a list.
   * @param maxHeap
   */
  private void heapToList(PriorityQueue<N> maxHeap) {
    if (maxHeap == null || maxHeap.size() == 0) {
      found.clear();
      return;
    }

    N maxElt = maxHeap.poll();
    heapToList(maxHeap);
    found.add(maxElt);
  }

  /**
   * Accessor for nodes.
   * @return An ArrayList of nodes
   */
  public ArrayList<N> getNodeList() {
    return list;
  }

  /**
   * Accessor for nodes found in search.
   * @return An ArrayList of nodes
   */
  public ArrayList<N> getFound() {
    return found;
  }

  /**
   * Setter for k.
   * @param newK Nonnegative integer
   */
  public void setK(int newK) {
    k = newK;
  }

  /**
   * Setter for radius.
   * @param newR Nonnegative double
   */
  public void setR(double newR) {
    r = newR;
  }

  /**
   * Setter for coordinates, creates a copy of the given array.
   * @param newCoords An ArrayList of doubles.
   */
  public void setCoords(int[] newCoords) {
    coords = newCoords;
  }

  /**
   * Setter for node to be ignored.
   * @param node A KdNode
   */
  public void setNodeIgnored(N node) {
    nodeIgnored = node;
  }


}
