package edu.brown.cs.student.yoki.commands;

import edu.brown.cs.student.yoki.driver.TriggerAction;
import edu.brown.cs.student.yoki.driver.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * This class gets user information.
 */
public class UserReader implements TriggerAction {
  private ArrayList<String> userData = new ArrayList<>();
  private User user;

  /**
   * Implements the trigger action class, thus overrides the method action and
   * executes the following code when the user command is inputted.
   * @param args List of strings from the command line
   */
  @Override
  public void action(ArrayList<String> args) {
    if (DataReader.getdataPath() == null) {
      System.err.println("ERROR: No database loaded");
    } else {
      if (args.size() == 2) {
        if (DataReader.getdataPath() == null) {
          System.err.println("ERROR: No database loaded");
        } else {
          try {
            int searchId = Integer.parseInt(args.get(1));
            PreparedStatement prep = SQLcommands.getUserData();
            // North to south, less than lat 1 and greater than lat2
            prep.setInt(1, searchId);
            ResultSet rs1 = prep.executeQuery();

            ArrayList<String> userInfo = new ArrayList<String>();
            ArrayList<Integer> idYear = new ArrayList<Integer>();

            int id = rs1.getInt("id");
            double year = rs1.getDouble("year");

            userInfo.add(rs1.getString("first_name"));
            userInfo.add(rs1.getString("last_name"));
            userInfo.add(rs1.getString("email"));
            userInfo.add(rs1.getString("password"));
            userInfo.add(rs1.getString("images"));
            userInfo.add(rs1.getString("major"));
            userInfo.add(rs1.getString("bio"));

            int[] interests = new int[DataReader.getInterestCount()];
            for (int j = 0; j < interests.length; j++) {
              interests[j] = rs1.getInt(j + DataReader.getUserDataColumnLen() + 2);
            }

            user = new User(id, year, userInfo, interests);

            getUser();
            prep.close();
            rs1.close();
          } catch (Exception e) {
            System.err.println("ERROR: must enter valid numbers");
          }
        }
      } else {
        System.err.println("ERROR: the user command must be in the form <[user] [id#]");
      }
    }
  }

  /**
   * Gets the user.
   * @return user
   */
  public User getUser() {
    System.out.println(user.toString());
    return user;
  }

}
