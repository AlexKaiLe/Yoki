package edu.brown.cs.student.yoki.commands;

import edu.brown.cs.student.yoki.Main;
import edu.brown.cs.student.yoki.driver.TreeFunction;
import edu.brown.cs.student.yoki.driver.TriggerAction;
import edu.brown.cs.student.yoki.driver.User;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class finds all the matches for a user.
 */
public class MatchFinder implements TriggerAction {
  //set up global variables
  private int k;
  private double r;
  private User ignored = null;
  private int[] coords = new int[DataReader.getInterestCount()];
  private ArrayList<User> userList = new ArrayList<User>();

  /**
   * Implements the trigger action class, thus overrides the method action and
   * executes the following code when the match command is inputted.
   * @param args List of strings from the command line
   */
  @Override
  public void action(ArrayList<String> args) {
    if (DataReader.getdataPath() == null) {
      System.err.println("ERROR: No database loaded");
    } else {
      try {
        if (Integer.parseInt(args.get(1)) >= 0) {
          if (parse(args)) {
            TreeFunction<User> tree = Main.getKdTree();
            tree.setK(k);
            tree.setR(r);
            tree.setNodeIgnored(ignored);
            tree.searchAndPrint();
            userList = tree.getFound();
          }
        }
      } catch (Exception e) {
        System.err.println("ERROR: the match command must follow the form"
            + "<[match] [# of matches] [id#]> (make sure your inputs are integers)");
      }
    }
  }

  /**
   * Method specific for property based testing.
   * @param kdTree a kdTree
   * @param search number of people to search
   * @param currentUser current user
   * @return distance between users
   */
  public ArrayList<Double> propertyBasedTesting(TreeFunction<User> kdTree,
                                                int search, User currentUser) {
    ArrayList<Double> dist = new ArrayList<>();
    if (currentUser != null) {
      kdTree.setK(search);
      kdTree.setR(Double.POSITIVE_INFINITY);
      kdTree.setNodeIgnored(currentUser);
      kdTree.kdNeighbors();
      ArrayList<User> kdUsers = kdTree.getFound();
      for (int i = 0; i < kdUsers.size(); i++) {
        kdUsers.get(i).distance(currentUser);
      }

      kdUsers.sort(new CompareDist());
      for (int i = 0; i < kdUsers.size(); i++) {
        dist.add(kdUsers.get(i).distance(currentUser));
      }
      return dist;
    } else {
      return null;
    }
  }

  /**
   * This method is a comparator that compares the distance between users.
   */
  private final class CompareDist implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
      return Double.compare(a.getDistance(), b.getDistance());
    }
  }

  /**
   * gets the lists of users.
   * @return userList
   */
  public ArrayList<User> getUserList() {
    return userList;
  }

  /**
   * Gets an arrayList of user ids.
   * @return list of user ids
   */
  public ArrayList<Integer> getUserIds() {
    ArrayList<Integer> idList = new ArrayList<>();
    for (User i: userList) {
      idList.add(i.getId());
    }
    return idList;
  }

  /**
   * parse the arguments in the command line.
   * @param args command inputs
   * @return boolean
   */
  private boolean parse(ArrayList<String> args) {
    try {
      if (args.size() == 3) {
        k = Integer.parseInt(args.get(1));
        r = Double.POSITIVE_INFINITY;
        if (!setCoordsByName(args.get(2))) {
          System.err.println("ERROR: No user id found named " + args.get(2));
          return false;
        }
        return true;
      } else {
        System.err.println("ERROR: " + args + " <[match] [# of matches] [id#]>");
        return false;
      }
    } catch (Exception e) {
      System.err.println("ERROR: the match command must follow the"
          + " form <[match] [# of matches] [id#]> (make sure your inputs are integers)");
      return false;
    }
  }

  /**
   * Sets the coordinates of the user by name if provided.
   * @param id of a user
   * @return boolean
   */
  public boolean setCoordsByName(String id) {
    TreeFunction<User> tree = Main.getKdTree();
    ArrayList<User> users = tree.getNodeList();

    for (User usr : users) {
      if (usr.getId() == Integer.parseInt(id)) {
        ignored = usr;
        tree.setCoords(usr.getCoords());
        tree.setNodeIgnored(ignored);
        return true;
      }
    }
    return false;
  }
}
