package edu.brown.cs.student.yoki.driver;

/**
 * This class is an interest object.
 */
public class Interest {
  //set up global variables
  private int id;
  private String tag;
  private String name;
  private int score;

  /**
   * Constructor that set up some global variables.
   * @param id
   * @param tag
   */
  public Interest(int id, String tag) {
    this.id = id;
    this.tag = tag;
    this.score = -1;

    convertTagToName();
  }

  /**
   * Another constructor that sets up some global variables.
   * @param id
   * @param tag
   * @param score
   */
  public Interest(int id, String tag, int score) {
    this.id = id;
    this.tag = tag;
    this.score = score;
    convertTagToName();
  }

  /**
   * Method that converts the tags to names.
   */
  private void convertTagToName() {
    name = "";
    for (String word: tag.split("_")) {
      name += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
    }
    name = name.substring(0, name.length() - 1);
  }

  /**
   * get the id of the interest.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * get the tag of the interest.
   * @return tag
   */
  public String getTag() {
    return tag;
  }

  /**
   * gets the name of the interest.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * gets the score of the interest.
   * @return score
   */
  public int getScore() {
    return score;
  }

  /**
   * sets the score of the interest.
   * @param score
   */
  public void setScore(int score) {
    this.score = score;
  }

}
