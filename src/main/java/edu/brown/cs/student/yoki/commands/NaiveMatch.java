package edu.brown.cs.student.yoki.commands;

import edu.brown.cs.student.yoki.driver.TriggerAction;
import edu.brown.cs.student.yoki.driver.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class NaiveMatch implements TriggerAction {
  //Set global variables
  private ArrayList<User> userList;

  /**
   * Implements the trigger action class, thus overrides the method action and
   * executes the following code when the naive command is inputted.
   * @param args List of strings from the command line
   */
  @Override
  public void action(ArrayList<String> args) throws SQLException, ClassNotFoundException {
    if (DataReader.getdataPath() == null) {
      System.err.println("ERROR: No database loaded");
    } else {
      if (args.size() == 3) {
        try {
          String userId = args.get(2);
          PreparedStatement prep = SQLcommands.getAll();
          ResultSet rs = prep.executeQuery();
          ArrayList<User> allUserList = new ArrayList<>();

          while (rs.next()) {
            ArrayList<String> userInfo = new ArrayList<>();
            int id = rs.getInt("id");
            double year = rs.getInt("year");

            userInfo.add(rs.getString("first_name"));
            userInfo.add(rs.getString("last_name"));
            userInfo.add(rs.getString("email"));
            userInfo.add(rs.getString("password"));
            userInfo.add(rs.getString("images"));
            userInfo.add(rs.getString("major"));
            userInfo.add(rs.getString("bio"));

            int[] interests = new int[DataReader.getInterestCount()];
            for (int j = 0; j < interests.length; j++) {
              interests[j] = rs.getInt(j + DataReader.getUserDataColumnLen() + 2);
            }

            InterestsReader ir = new InterestsReader();
            ArrayList<String> userInterests = new ArrayList<>();
            userInterests.add("interests");
            userInterests.add(userId);

            User user = new User(id, year, userInfo, interests);
            allUserList.add(user);
          }

          InterestsReader ir = new InterestsReader();
          ArrayList<String> userInterests = new ArrayList<>();
          userInterests.add("interests");
          userInterests.add(userId);

          ir.action(userInterests);
          CompareUsers userCompare = new CompareUsers(ir.getInterestsList());
          CompareDist distCompare = new CompareDist();
          allUserList.sort(userCompare);

          int numbMatches = Integer.parseInt(args.get(1));
          allUserList.remove(0);
          userList = new ArrayList<>();
          for (int i = 0; i < numbMatches; i++) {
            System.out.println(allUserList.get(i).toString());
            userList.add(allUserList.get(i));
          }
        } catch (Exception e) {
          System.err.println("ERROR: the command naive must"
              + "follow the form <[naive] [# of matches] [id#]>");
        }
      }
    }
  }

  /**
   * This method is specific for property base testing.
   * @param userlist list of users
   * @param currentUser current user
   * @return boolean
   */
  public ArrayList<Double> propertyBasedTesting(ArrayList<User> userlist, User currentUser) {
    if (currentUser != null) {
      ArrayList<Double> dist = new ArrayList<>();
      for (int i = 0; i < userlist.size(); i++) {
        userlist.get(i).distance(currentUser);
      }
      userlist.sort(new CompareDist());
      userlist.remove(0);
      for (int i = 0; i < userlist.size(); i++) {
        dist.add(userlist.get(i).getDistance());
      }
      return dist;
    } else {
      return null;
    }
  }

  /**
   * Gets the user id.
   * @return user id
   */
  public ArrayList<Integer> getUserIds() {
    ArrayList<Integer> ids = new ArrayList<>();
    for (User i: userList) {
      ids.add(i.getId());
    }
    return ids;
  }

  /**
   * This is a comparator that sorts the users based on similar interests.
   */
  private final class CompareUsers implements Comparator<User> {
    private int[] interests;
    private final double ten = 10.0;

    private CompareUsers(int[] interests) {
      this.interests = interests;
    }

    public double targetDis(int[] node, int[] targetPoint) {
      double matchScore = 0;
      for (int i = 0; i < DataReader.getInterestCount(); i++) {
        double relevance = node[i] / ten * targetPoint[i] / ten;
        int dist = Math.abs(node[i] - targetPoint[i]);
        matchScore += relevance * (ten - dist);
      }
      return 1 / matchScore;
    }

    @Override
    public int compare(User a, User b) {
      return Double.compare(targetDis(a.getInterests(), interests),
          targetDis(b.getInterests(), interests));
    }
  }

  /**
   * This is a comparator that compares the user distances.
   */
  private final class CompareDist implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
      return Double.compare(a.getDistance(), b.getDistance());
    }
  }
}
