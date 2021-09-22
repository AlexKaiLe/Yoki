package edu.brown.cs.student.yoki.driver;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface used for adding commands to REPL.
 */
public interface TriggerAction {
  /**
   * Action to execute.
   * @param args List of strings
   */
  void action(ArrayList<String> args) throws SQLException, ClassNotFoundException;
}
