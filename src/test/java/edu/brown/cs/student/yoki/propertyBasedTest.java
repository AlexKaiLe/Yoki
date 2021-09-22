package edu.brown.cs.student.yoki;

import edu.brown.cs.student.yoki.commands.MatchFinder;
import edu.brown.cs.student.yoki.driver.TreeFunction;
import edu.brown.cs.student.yoki.driver.User;
import edu.brown.cs.student.yoki.commands.NaiveMatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class propertyBasedTest {
  //Instantiate instance variables

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;
  private ArrayList<User> userList;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
    userList = new ArrayList<User>();
    for (int i = 0; i < 10; i++) {
      int id = i;
      double year = (int)(Math.random() * 3 + 1) + (int)(Math.random() * 1 + 1)/2 + 2020;

      String firstName = "firstName" + i;
      String lastName = "lastName" + i;
      String email = "email" + i;
      String password = "password" + i;
      String images = "images" + i;
      String major = "major" + i;
      String bio = "bio" + i;
      ArrayList<String> userInfo = new ArrayList<>(){{
        add(firstName);
        add(lastName);
        add(email);
        add(password);
        add(images);
        add(major);
        add(bio);}};
      int[] interests = new int[100];

      for (int j = 0; j < 100; j++) {
        interests[j] = (int) (Math.random()*10);
      }
      User temp = new User(id, year, userInfo, interests);
      userList.add(temp);
    }
  }

  @After
  public void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
    userList = null;
  }

  @Test
  public void pbt(){
    this.setUp();
    Assert.assertEquals(userList.size(), 10);
    System.out.println(userList.size());
    TreeFunction<User> kdTree = new TreeFunction<>();
    kdTree.listToTree(userList);
    int id = 1;
    User currentUser = null;
    for (int i = 0; i < userList.size(); i++) {
      if (userList.get(i).getId() == id) {
        currentUser = userList.get(i);
      }
    }
    kdTree.setCoords(currentUser.getInterests());
    MatchFinder mf = new MatchFinder();
    ArrayList<Double> mfUsers = mf.propertyBasedTesting(kdTree, userList.size(), currentUser);
    NaiveMatch nm = new NaiveMatch();
    ArrayList<Double> nmUsers = nm.propertyBasedTesting(userList, currentUser);
    Assert.assertEquals(nmUsers, mfUsers);
    this.tearDown();
  }
}
