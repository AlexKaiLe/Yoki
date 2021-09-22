package edu.brown.cs.student.yoki.commands;

import edu.brown.cs.student.yoki.driver.Interest;
import edu.brown.cs.student.yoki.driver.TriggerAction;
import edu.brown.cs.student.yoki.driver.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class get the interests of a user.
 */
public class InterestsReader implements TriggerAction {
  //set global variables
  private ArrayList<Integer> userInterests = new ArrayList<Integer>();

  /**
   * Implements the trigger action class, thus overrides the method action and
   * executes the following code when the interests command is inputted.
   * @param args List of strings from the command line
   */
  @Override
  public void action(ArrayList<String> args) {
    if (args.size() == 2) {
      if (DataReader.getdataPath() == null) {
        System.err.println("ERROR: No database loaded");
      } else {
        try {
          int id = Integer.parseInt(args.get(1));
          PreparedStatement prep = SQLcommands.getUserInterests();
          // North to south, less than lat 1 and greater than lat2
          prep.setInt(1, id);
          ResultSet rs = prep.executeQuery();
          String out = "";
          for (int i = 2; i < DataReader.getInterestCount() + 2; i++) {
            try {
              userInterests.add(rs.getInt(i));
              out += (rs.getInt(i) + ", ");
            } catch (Exception e) {
              System.err.println("Something went wrong with reading in data "
                  + "(the input id is invalid)");
              break;
            }
          }
          System.out.println(out.substring(0, out.length() - 2));
          prep.close();
          rs.close();
        } catch (Exception e) {
          System.err.println("ERROR: must enter valid numbers");
        }
      }
    } else {
      System.err.println("ERROR: interests command must follow the"
          + "following format <[interest] [#id of user]");
    }
  }

  /**
   * Gets the user interests.
   * @return user interests
   */
  public ArrayList<Integer> getUserInterests() {
    return userInterests;
  }

  /**
   * Get the user interests as a list.
   * @return list of interests
   */
  public int[] getInterestsList() {
    int[] output = new int[userInterests.size()];
    for (int i = 0; i < userInterests.size(); i++) {
      output[i] = userInterests.get(i);
    }
    return output;
  }

  /**
   * Gest the top interests of a user.
   * @return top interests
   */
  public ArrayList<ArrayList<Interest>> getTopInterests() {
    HashMap<Integer, Interest> converter = DataReader.getConvert();
    ArrayList<Interest> topCommonInterests = new ArrayList<>();
    ArrayList<Interest> topOtherInterests = new ArrayList<>();
    User currentUser = DataReader.getCurrentUser();
    ArrayList<ArrayList<Interest>> topInterests = new ArrayList<>();
    int c = DataReader.getInterestCount();
    for (int i = 0; i < DataReader.getInterestCount(); i++) {
      if (userInterests.get(i) > 0) {
        if (currentUser.getInterests()[i] > 0) {
          topCommonInterests.add(new Interest(i + 1,
              converter.get(i + DataReader.getUserDataColumnLen() + 2).getTag(),
              userInterests.get(i)));
        } else {
          topOtherInterests.add(new Interest(i + 1,
              converter.get(i + DataReader.getUserDataColumnLen() + 2).getTag(),
              userInterests.get(i)));
        }
      }
    }
    topCommonInterests.sort(new DistComparator());
    topOtherInterests.sort(new DistComparator());
    topInterests.add(topCommonInterests);
    topInterests.add(topOtherInterests);
    return topInterests;
  }

  /**
   * Comparator that compares the distance between two interests.
   */
  private final class DistComparator implements Comparator<Interest> {
    @Override
    public int compare(Interest a, Interest b) {
      return Integer.compare(b.getScore(), a.getScore());
    }
  }

}
