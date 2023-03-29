package edu.wpi.teamA;

import edu.wpi.teamA.database.l1edges;
import edu.wpi.teamA.database.l1nodes;
import java.sql.*;
import java.util.Scanner;

public class Adb {

  //Conection object represents the connection to the sql database, it returns basic info on the tables
  static void NodeEdgeInfo(Connection connection, String Case_1) {
    PreparedStatement ps = null; //object to execute a command in
    ResultSet result = null;//Object with a cursor that points to specific line in a table returns false at end
    Scanner ID_scan = new Scanner(System.in); //What are we scanning?
    System.out.println("Please input node/edge ID (if no, hit enter):");
    String ID = ID_scan.nextLine();//take input from scanner and store in id
    l1nodes ResultNode = null;//What is this?
    try {//If an error occurs in try, continue to catch
      switch (Case_1) {//Param value causes code to go to the corresponding case
        case "1":
          // 1- Display all node information
          ps = connection.prepareStatement("Select * from l1nodes"); //creates an object holding an sql statement
          result = ps.executeQuery();//Execute the statement stored in the object, and put it at cursor location

          while (result.next()) {//While there are more lines, move through the table
            //What is this?
            ResultNode =
                new l1nodes(
                    result.getString("nodeID"),
                    result.getInt("xcoord"),
                    result.getInt("ycoord"),
                    result.getString("floor"),
                    result.getString("building"),
                    result.getString("nodeType"),
                    result.getString("longName"),
                    result.getString("ShortName"));
            l1nodes.DisplayNode(ResultNode);
          }
          break;
        case "2":
          // 2- Display all edge information
          ps = connection.prepareStatement("Select * from l1edges"); //create an execute object and put it into ps
          result = ps.executeQuery();

          while (result.next()) {
            l1edges ResultEdge =
                new l1edges(
                    result.getString("edgeID"),
                    result.getString("startNode"),
                    result.getString("endNode"));
            l1edges.DisplayEdge(ResultEdge);
          }
          break;
        case "3":
          // 3- Display particular node information by id

          ps = connection.prepareStatement("Select * from l1nodes where nodeID = ?");
          ps.setString(1, ID);

          result = ps.executeQuery();
          while (result.next()) {
            ResultNode =
                new l1nodes(
                    result.getString("nodeID"),
                    result.getInt("xcoord"),
                    result.getInt("ycoord"),
                    result.getString("floor"),
                    result.getString("building"),
                    result.getString("nodeType"),
                    result.getString("longName"),
                    result.getString("ShortName"));
            l1nodes.DisplayNode(ResultNode);
          }
          break;
        case "4":
          // 4- Display particular edge information by id123
          ps = connection.prepareStatement("Select * from l1edges where edgeID = ?");
          ps.setString(1, ID);

          result = ps.executeQuery();
          while (result.next()) {
            l1edges ResultEdge =
                new l1edges(
                    result.getString("edgeID"),
                    result.getString("startNode"),
                    result.getString("endNode"));
            l1edges.DisplayEdge(ResultEdge);
          }
          break;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  static void UpdateNodeCoord(Connection connection) {}

  static void UpdateNodeLocName(Connection connection) {}

  static void ExportNode(Connection connection) {}

  static void ImportNode(Connection connection) {}

  static void DisplayHelp(Connection connection) {}

  static void ExitProgram(Connection connection) {
    // Close connection then exit
  }

  public static void main(String[] args) {
    try (Connection connection =
        DriverManager.getConnection("jdbc:postgresql://database.wpi.cs.edu/", "teama", "teama10")) {
      while (true) {
        System.out.println(
            """
                        What do you want to do?
                        1- Display node and edge information
                        2- Update node coordinates
                        3- Update name of location node
                        4- Import from a CSV file into the node table
                        5- Display Help on how to use your database program
                        6- Export node table into a CSV file
                        7- Exit the program
                            """);

        Scanner input = new Scanner(System.in);
        String CaseNum = input.nextLine();
        switch (CaseNum) {
          case "1":
            // Display node and edge information
            System.out.println(
                """
                                What do you want to do?
                                1- Display all node information
                                2- Display all edge information
                                3- Display particular node information by id
                                4- Display particular edge information by id
                                    """);
            Scanner input_1 = new Scanner(System.in);
            String Case_1 = input_1.nextLine();
            NodeEdgeInfo(connection, Case_1);
            break;
          case "2":
            // Update node coordinates
            UpdateNodeCoord(connection);
            break;
          case "3":
            // Update name of location node
            UpdateNodeLocName(connection);
            break;
          case "4":
            // Import from a CSV file into the node table
            ImportNode(connection);
            break;
          case "5":
            // Display Help on how to use your database program
            DisplayHelp(connection);
            break;
          case "6":
            // Export node table into a CSV file
            ExportNode(connection);
            break;
          case "7":
            // Exit the program
            ExitProgram(connection);
            break;
        }
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
  }
}
