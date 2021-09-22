package edu.brown.cs.student.yoki.driver;


import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * This is a kdtree that implements a KdNode.
 * @param <N>
 */
public class AlexKDTree<N extends KdNode> {
  //instantiate private variables
  private final int dimensions;
  private final N root;
  private PriorityQueue<N> nearest = null;
  private int neighbor;
  private double radius;
  private final Random coinFlip = new Random();
  private final double maxScore = 10;

  /**
   * This the the constructor class where I create a list of nodes
   * from the a list of s that were passed in. Then it creates
   * the create.
   *
   * @param nodeList an arraylist of KdNodes
   * @param d        the dimensions of the KDTree
   */
  public AlexKDTree(ArrayList<N> nodeList, int d) {
    this.dimensions = d;
    this.root = this.createTree(nodeList, 0);
  }

  /**
   * Setter for the number of neighbors to search for.
   * @param newN A nonnegative integer
   */
  public void setNeighbor(int newN) {
    neighbor = newN;
  }

  /**
   * Setter for the search radius.
   * @param newR A double
   */
  public void setRadius(double newR) {
    radius = newR;
  }

  /**
   * This method returns the root of the tree that was created.
   *
   * @return root of the tree
   */
  public N getRoot() {
    return this.root;
  }

  /**
   * This method uses recursion to create the tree and assigns
   * locations to specific nodes.
   *
   * @param nodeList a list of nodes
   * @param depth    the depth of the node within the tree
   * @return the middle node/current node you are working on
   */
  public N createTree(List<N> nodeList, int depth) {
    int index = depth % this.dimensions;
    nodeList.sort(new NodeCompare(index));
    //Finding the middle node and setting its depth
    int mid = (nodeList.size()) / 2;
    N midNode = nodeList.get(mid);
    midNode.setDepth(depth);

    //If size is 0 or 1, then reaches the base case
    if (nodeList.size() == 1 || nodeList.size() == 0) {
      return midNode;
    }
    //Recurse on the left hand side of the tree
    if (mid > 0) {
      midNode.setLeft(createTree(nodeList.subList(0, mid), depth + 1));
    }
    //Recurse on the right hand side of the tree
    if (mid < nodeList.size() - 1) {
      midNode.setRight(createTree(nodeList.subList(mid + 1, nodeList.size()), depth + 1));
    }
    return midNode;
  }

  /**
   * This method find the neighbors that are closest to the input node.
   *
   * @param ignore the node that we ignore if given a name
   * @param node   the current node we are looking at
   * @param target the point of which we want to be the closest to
   * @return the list of neighbors that are nearest
   */
  public PriorityQueue<N> search(N ignore, N node, int[] target) {
    //create a new arraylist if we start out at the root
    if (root == null || node == null) {
      return null;
    }
    if (root == node) {
      //nearest = new ArrayList<N>();
      nearest = new PriorityQueue<>(new DistanceCompare(target));
    }

    // If we searched from this node by name, don't add it
    if (!node.equals(ignore)) {
      // if the size of the list of neighbors is less than the numb,
      // then we can add the node to the list
      if (nearest.size() < neighbor) {
        if (targetDis(node.getCoords(), target) <= radius) {
          nearest.add(node);
        }
      } else {
        //Edge case where the number of neighbors is 0
        if (neighbor == 0) {
          return nearest;
        }
        //Get the last node in the list
        N largest = nearest.peek();
      /*Check if the distance between the target point and the node is less that the
      distance between the largest nodes and the target point
      */
        if (targetDis(target, node.getCoords())
            < targetDis(largest.getCoords(), target)) {
          //Remove farthest
          nearest.poll();
          //Add new
          nearest.add(node);
        } else if (targetDis(target, node.getCoords())
            == targetDis(largest.getCoords(), target)) {
          // Equidistant, add randomness
          if (coinFlip.nextBoolean()) {
            nearest.poll();
            nearest.add(node);
          }
        }
      }
    }

    //get the index, the largest node, and the distance between the largest node and the targetPoint
    int index = node.getDepth() % dimensions;

    double targetNeighbor;
    if (nearest.size() > 0) {
      N bigNode = nearest.peek();
      targetNeighbor = targetDis(target, bigNode.getCoords());
    } else {
      // No max distance as no node found
      targetNeighbor = Double.POSITIVE_INFINITY;
    }

    /*If the target neighbor is greater or equal to the distance between teh current node
    and the targetPoint axis then recurse on the left and right nodes if they exist
    */
    if (nearest.size() < neighbor || targetNeighbor
        >= Math.abs(node.getCoords()[index] - target[index])) {
      search(ignore, (N) node.getLeft(), target);
      search(ignore, (N) node.getRight(), target);
    } else {
      /*If the current node index coordinate is less than the target point index
      and it has a right node or left node then recurse on the respective node
       */
      if ((node.getCoords()[index] < target[index])) {
        search(ignore, (N) node.getRight(), target);
      } else {
        search(ignore, (N) node.getLeft(), target);
      }
    }
    return nearest;
  }
  /**
   * This is is a comparator that compares the coordinates axis of different Nodes.
   */
  private final class NodeCompare implements Comparator<N> {
    private int index;

    private NodeCompare(int i) {
      this.index = i;
    }

    @Override
    public int compare(N a, N b) {
      return Double.compare(a.getCoords()[index], b.getCoords()[index]);
    }
  }

  /**
   * This is is a comparator that compares the distance of different Nodes.
   */
  private final class DistanceCompare implements Comparator<N> {
    private int[] coor;

    private DistanceCompare(int[] coor) {
      this.coor = coor;
    }

    @Override
    public int compare(N a, N b) {
      return Double.compare(targetDis(b.getCoords(), coor),
        targetDis(a.getCoords(), coor));
    }
  }

  /**
   * This method get the distance between a given node and the target point.
   *
   * @param node        current node you are looking at
   * @param targetPoint the target point you are looking at
   * @return the distance between the two points
   */
  public double targetDis(int[] node, int[] targetPoint) {
    double matchScore = 0;
    for (int i = 0; i < dimensions; i++) {
      double relevance = node[i] / maxScore * targetPoint[i] / maxScore;
      int dist = Math.abs(node[i] - targetPoint[i]);
      matchScore += relevance * (maxScore - dist);
    }
    if (matchScore == 0) {
      return Double.POSITIVE_INFINITY;
    }
    return 1 / matchScore;
  }
}
