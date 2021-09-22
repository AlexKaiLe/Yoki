package edu.brown.cs.student.yoki;

import edu.brown.cs.student.yoki.commands.*;
import edu.brown.cs.student.yoki.driver.Interest;
import edu.brown.cs.student.yoki.driver.TreeFunction;
import edu.brown.cs.student.yoki.driver.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class jacocoTest {
  //Instantiate instance variables

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  public void readInData() {
    ArrayList<String> input = new ArrayList<>(){{add("data"); add("data/smallData.sqlite");}};
    DataReader dr = new DataReader();
    dr.action(input);
  }

  @Test
  public void data(){
    this.setUp();
    ArrayList<String> input = new ArrayList<>(){{add("data"); add("data/smallData.sqlite");}};
    DataReader dr = new DataReader();
    dr.action(input);
    Assert.assertEquals(outContent.toString(), "Reading data from data/smallData.sqlite\n");
    this.tearDown();
  }

  @Test
  public void interests(){
    this.setUp();
    Main.setCurrentId(54);
    readInData();
    ArrayList<String> input = new ArrayList<>(){{add("interests"); add("55");}};
    InterestsReader ir = new InterestsReader();
    ir.action(input);
    ArrayList<Integer> checkInterests = new ArrayList<>(){{add(10); add(0); add(2); add(0); add(3); add(3); add(0); add(0); add(6); add(0);}};
    Assert.assertEquals(ir.getUserInterests(), checkInterests);
    String topInterests = "";

    for (ArrayList<Interest> i: ir.getTopInterests()) {
      for (Interest j: i) {
        topInterests += j.getName() + " ";
      }
      topInterests += "\n";
    }
    Assert.assertEquals(topInterests, "Abseling Adventure Racing Acroyoga \nAikido Action Figures \n");
    this.tearDown();
  }

  @Test
  public void match(){
    this.setUp();
    readInData();
    ArrayList<String> input = new ArrayList<>(){{add("match"); add("10"); add("55");}};
    MatchFinder mf = new MatchFinder();
    mf.action(input);
    ArrayList<Integer> checkInterests = new ArrayList<>(){{add(89); add(96); add(36); add(23); add(86); add(48); add(22); add(82); add(91); add(56);}};
    Assert.assertEquals(mf.getUserIds(), checkInterests);
    this.tearDown();
  }

  @Test
  public void user(){
    this.setUp();
    readInData();
    ArrayList<String> input = new ArrayList<>(){{add("user"); add("55");}};
    UserReader ur = new UserReader();
    ur.action(input);
    String output = "Reading data from data/smallData.sqlite\n" +
      "id: 55, first_name: Immanuel, last_name: Lelievre, email: immanuel_lelievre@brown.edu, password: axfXvF5myoKs7JrBbJhFtv7vLIzzQzr1Km4i0W5UsIo=, year: 2021.0\n" +
      "Interest Levels: 10, 0, 2, 0, 3, 3, 0, 0, 6, 0\n\n";
    Assert.assertEquals(outContent.toString(), output);
    this.tearDown();
  }

  @Test
  public void encrypt() throws SQLException, ClassNotFoundException {
    this.setUp();
    ArrayList<String> input = new ArrayList<>(){{add("encrypt"); add("quarantine"); add("vaccine");}};
    Encrypt en = new Encrypt();
    en.action(input);
    String output = "Encryption key: quarantine\n" +
      "Original message: vaccine\n" +
      "Encrypted message: 8rHP3C0Je9lxt3PH7q+GCg==\n" +
      "Decrypted message: vaccine\n";
    Assert.assertEquals(outContent.toString(), output);
    this.tearDown();
  }

  @Test
  public void naive() throws SQLException, ClassNotFoundException {
    this.setUp();
    readInData();
    ArrayList<String> input = new ArrayList<>(){{add("naive"); add("10"); add("75");}};
    NaiveMatch nm = new NaiveMatch();
    nm.action(input);
    nm.getUserIds();
    ArrayList<Integer> checkNaive = new ArrayList<>(){{add(72); add(65); add(50); add(6); add(32); add(3); add(30); add(33); add(86); add(96);}};
    Assert.assertEquals(nm.getUserIds(), checkNaive);
    this.tearDown();
  }
}