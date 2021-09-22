package edu.brown.cs.student.yoki.commands;


import edu.brown.cs.student.yoki.driver.Interest;
import edu.brown.cs.student.yoki.driver.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class contains the SQL commands we use throughout project.
 */
public final class SQLcommands {
  private static final int EIGHT = 8;
  private static final int SEVEN = 7;
  /**
   * Empty constructor.
   */
  private SQLcommands() {
  }

  /**
   * Updates the user.
   * @param userId
   * @param newInterest
   */
  public static void update(int userId, HashMap<Integer, Interest> newInterest) {
    try {
      Connection conn = DataReader.getConnection();

      Iterator hmIterator = newInterest.entrySet().iterator();
      while (hmIterator.hasNext()) {
        Map.Entry mapElement = (Map.Entry) hmIterator.next();
        Interest interest = (Interest) mapElement.getValue();

        PreparedStatement prep = conn.prepareStatement("UPDATE user_interests SET "
            + interest.getTag() + "=? WHERE id=?;");
        prep.setInt(1, interest.getScore());
        prep.setInt(2, userId);
        prep.execute();
        prep.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
    }
  }

  /**
   * gets the user data.
   * @return user data
   */
  public static PreparedStatement getUserData() {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("SELECT * FROM user_data"
          + " INNER JOIN user_interests ON user_data.id=user_interests.id WHERE user_data.id = ?;");
      return prep;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return null;
    }
  }

  /**
   * gets all the information from both tables.
   * @return all user data
   */
  public static PreparedStatement getAll() {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("SELECT * FROM"
          + " user_data INNER JOIN user_interests ON user_data.id=user_interests.id;");
      return prep;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return null;
    }
  }

  /**
   * gets the user interests.
   * @return user interests
   */
  public static PreparedStatement getUserInterests() {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("SELECT * FROM user_interests WHERE id = ?");
      return prep;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return null;
    }
  }

  /**
   * Add a match to the matches table.
   * @param userId
   * @param matchId
   * @param isMatch
   */
  public static void addMatch(int userId, int matchId, boolean isMatch) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("INSERT INTO matches VALUES (?,?,?)");
      prep.setInt(1, userId);
      prep.setInt(2, matchId);
      prep.setBoolean(3, isMatch);

      PreparedStatement prep2 = conn.prepareStatement("SELECT * FROM"
          + " matches WHERE id=? AND match_id=? AND matched=?");
      prep2.setInt(1, userId);
      prep2.setInt(2, matchId);
      prep2.setBoolean(3, isMatch);
      ResultSet rs2 = prep2.executeQuery();
      if (!rs2.next()) {
        prep.execute();
      }
      prep.close();
      prep2.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
    }
  }

  /**
   * Get all the matches of a user.
   * @param userId
   * @param includePasses
   * @return matches for a user
   */
  public static ArrayList<User> getAllMatches(int userId, boolean includePasses) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
          "SELECT * FROM matches WHERE id=? AND matched=true");
      if (includePasses) {
        prep = conn.prepareStatement("SELECT * FROM matches WHERE id=?");
      }
      prep.setInt(1, userId);
      ResultSet rs = prep.executeQuery();
      ArrayList<User> matches = new ArrayList<>();
      while (rs.next()) {
        PreparedStatement prep2 = getUserData();
        prep2.setInt(1, rs.getInt("match_id"));
        ResultSet rs2 = prep2.executeQuery();

        ArrayList<String> userInfo = new ArrayList<String>();
        ArrayList<Integer> idYear = new ArrayList<Integer>();

        int id = rs2.getInt("id");
        double year = rs2.getDouble("year");

        userInfo.add(rs2.getString("first_name"));
        userInfo.add(rs2.getString("last_name"));
        userInfo.add(rs2.getString("email"));
        userInfo.add(rs2.getString("password"));
        userInfo.add(rs2.getString("images"));
        userInfo.add(rs2.getString("major"));
        userInfo.add(rs2.getString("bio"));

        int[] interests = new int[DataReader.getInterestCount()];
        for (int j = 0; j < interests.length; j++) {
          interests[j] = rs2.getInt(j + EIGHT);
        }

        User user = new User(id, year, userInfo, interests);
        matches.add(user);
      }
      return matches;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return null;
    }
  }

  /**
   * Set the user's matched and passed datatable.
   * @param userId
   * @param matchId
   * @return boolean
   */
  public static boolean isAMatchPass(int userId, int matchId) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
          "SELECT * FROM matches WHERE id=? AND match_id=?");
      prep.setInt(1, userId);
      prep.setInt(2, matchId);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return false;
    }
    return false;
  }

  /**
   * Removes all the passes.
   * @param userId
   */
  public static void removeAllPasses(int userId) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
          "DELETE FROM matches WHERE id=? AND matched=false;");
      prep.setInt(1, userId);
      prep.execute();
      prep.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
    }
  }

  /**
   * Gets the user id from email and password.
   * @param email
   * @param password
   * @return user id
   */
  public static int getUserId(String email, String password) {
    System.out.println("email: " + email);
    System.out.println("pass: " + password);
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
          "SELECT * FROM user_data WHERE email=? AND password=?");
      prep.setString(1, email);
      prep.setString(2, password);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        return rs.getInt("id");
      } else {
        return -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return -1;
    }
  }

  /**
   * Gets the user info from an id.
   * @param userId
   * @return user
   */
  public static User getUserInfo(int userId) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
          "SELECT * FROM user_data INNER JOIN "
              + "user_interests WHERE user_data.id=user_interests.id AND user_data.id=?;");
      prep.setInt(1, userId);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        ArrayList<String> userInfo = new ArrayList<String>();
        int id = rs.getInt("id");
        double year = rs.getDouble("year");

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

        User user = new User(id, year, userInfo, interests);
        return user;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return null;
    }
    return null;
  }

  /**
   * Adds a user to the database.
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @param year
   * @param major
   * @param bio
   * @return boolean
   */
  public static boolean addUser(String firstName, String lastName,
                                String email, String password,
                                double year, String major, String bio) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("INSERT INTO user_data "
          + "(first_name, last_name, email, password, year, major, bio) "
          + "VALUES (?,?,?,?,?,?,?);");
      prep.setString(1, firstName);
      prep.setString(2, lastName);
      prep.setString(3, email);
      prep.setString(4, password);
      prep.setDouble(5, year);
      prep.setString(6, major);
      prep.setString(SEVEN, bio);
      prep.execute();

      prep = conn.prepareStatement("INSERT INTO user_interests (id) VALUES (last_insert_rowid())");
      prep.execute();

      prep.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return false;
    }
  }

  /**
   * Deletes a match.
   * @param userId
   * @param matchId
   * @return boolean
   */
  public static boolean deleteMatch(int userId, int matchId) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement(
              "DELETE FROM matches "
                      +  "WHERE id = ? AND match_id = ?;");
      prep.setInt(1, userId);
      prep.setInt(2, matchId);
      prep.execute();

      prep.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return false;
    }
  }

  /**
   * gets id by email.
   * @param email
   * @return id of user
   */
  public static int getIdByEmail(String email) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("SELECT id FROM user_data WHERE email=?");
      prep.setString(1, email);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        return rs.getInt("id");
      } else {
        return -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return -1;
    }
  }

  /**
   * Add a report to the dataset.
   * @param userId
   * @param reportedId
   * @param report
   */
  public static void addReport(int userId, int reportedId, String report) {
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("INSERT INTO reports VALUES (?,?,?);");
      prep.setInt(1, userId);
      prep.setInt(2, reportedId);
      prep.setString(3, report);
      prep.execute();

      prep.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
    }
  }

  /**
   * edit the profile of a user.
   * @param id
   * @param name
   * @param major
   * @param year
   * @param bio
   * @param email
   * @param image
   * @return boolean
   */
  public static boolean editProfile(int id, String[] name, String major,
                                    Double year, String bio, String email, String image) {
    String firstName = name[0];
    String lastName = name[1];
    try {
      Connection conn = DataReader.getConnection();
      PreparedStatement prep = conn.prepareStatement("UPDATE user_data SET first_name=?,"
          + " last_name=?, email=?, year=?, major=?, bio=?, images = ? WHERE id=?;");
      prep.setString(1, firstName);
      prep.setString(2, lastName);
      prep.setString(3, email);
      prep.setDouble(4, year);
      prep.setString(5, major);
      prep.setString(6, bio);
      prep.setString(SEVEN, image);
      prep.setInt(EIGHT, id);
      prep.execute();
      prep.close();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("ERROR: Issue reading in SQL");
      return false;
    }
  }
}
