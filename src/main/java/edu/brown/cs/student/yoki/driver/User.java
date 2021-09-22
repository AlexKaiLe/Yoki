package edu.brown.cs.student.yoki.driver;

import java.util.ArrayList;

/**
 * This is a user object.
 */
public class User extends KdNode {
  //set up global variables
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private double year;
  private String images;
  private String major;
  private String bio;
  private double distance;

  private int[] interests;
  private final double maxScore = 10.0;

  /**
   * Constructor of the user.
   * @param id
   * @param year
   * @param userInfo
   * @param interests
   */
  public User(int id, double year, ArrayList<String> userInfo, int[] interests) {
    this.id = id;
    this.year = year;

    this.firstName = userInfo.get(0);
    this.lastName = userInfo.get(1);
    this.email = userInfo.get(2);
    this.password = userInfo.get(3);
    this.images = userInfo.get(4);
    this.major = userInfo.get(5);
    this.bio = userInfo.get(6);

    this.interests = interests;
    setCoords(this.interests);
  }

  /**
   * gets id of user.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * gets the name of the user.
   * @return name
   */
  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  /**
   * gets the interests of the user.
   * @return interests
   */
  public int[] getInterests() {
    return this.interests;
  }

  /**
   * gets the email.
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * gets the password.
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * gets the image of the user.
   * @return image
   */
  public String getImages() {
    return this.images;
  }

  /**
   * gets the major of the user.
   * @return major
   */
  public String getMajor() {
    return this.major;
  }

  /**
   * gets the bio of the user.
   * @return bio
   */
  public String getBio() {
    return this.bio;
  }

  /**
   * gets the distance of the user.
   * @return distance
   */
  public double getDistance() {
    return this.distance;
  }

  /**
   * String of interests.
   * @return interests.
   */
  public String interestsToString() {
    String str = "";
    for (int i = 0; i < this.interests.length; i++) {
      str += this.interests[i] + ", ";
    }
    return str.substring(0, str.length() - 2);
  }

  /**
   * Distance formula of users.
   * @param user
   * @return distance
   */
  @Override
  public double distance(Object user) {
    User compareUser = (User) user;
    double matchScore = 0;
    for (int i = 0; i < interests.length; i++) {
      double relevance = interests[i] / maxScore * compareUser.interests[i] / maxScore;
      int dist = Math.abs(interests[i] - compareUser.interests[i]);
      matchScore += relevance * (maxScore - dist);
    }
    distance = 1 / matchScore;
    return distance;
  }

  /**
   * To string method for user.
   * @return string
   */
  @Override
  public String toString() {
    String str = "id: " + this.id + ", first_name: " + this.firstName
        + ", last_name: " + this.lastName + ", email: " + this.email
        + ", password: " + this.password + ", year: " + this.year
        + "\nInterest Levels: " + interestsToString() + "\n";
    return str;
  }
}
