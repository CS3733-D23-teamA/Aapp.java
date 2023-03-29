package edu.wpi.teamA;

import edu.wpi.teamA.database.l1edges;
import edu.wpi.teamA.database.l1nodes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Adb {

  static void NodeEdgeInfo(Connection connection, String Case_1) {
    PreparedStatement ps = null;
    Scanner ID_scan = new Scanner(System.in);
    System.out.println("Please input node/edge ID (if no, hit enter):");
    String ID = ID_scan.nextLine();
    l1nodes ResultNode = null;
    try {
      ResultSet result = null;
      switch (Case_1) {
        case "1" -> {
          // Display all node information: This case executes a SELECT query on the l1nodes
          // table to retrieve all rows. It then iterates through the result set using a while loop,
          // creating a l1nodes object for each row and passing it to the
          // DisplayNode method of the l1nodes class.
          ps = connection.prepareStatement("Select * from L1Nodes");
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
        }
        case "2" -> {
          // Display all edge information: This case executes a SELECT query on the l1edges
          // table to retrieve all rows. It then iterates through the result set using a
          // while loop, creating a l1edges object for each row and passing it to the
          // DisplayEdge method of the l1edges class.
          ps =
              connection.prepareStatement(
                  "Select * from L1Edges"); // create an execute object and put it into ps
          result = ps.executeQuery();
          while (result.next()) {
            l1edges ResultEdge =
                new l1edges(
                    result.getString("edgeID"),
                    result.getString("startNode"),
                    result.getString("endNode"));
            l1edges.DisplayEdge(ResultEdge);
          }
        }
        case "3" -> {
          // Display particular node information by ID: This case executes a SELECT
          // query on the l1nodes table to retrieve a specific row based on the nodeID
          // parameter. It then iterates through the result set using a while loop,
          // creating a l1nodes object for the row and passing it to the DisplayNode method of the
          // l1nodes class.

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
        }
        case "4" -> {
          // Display particular edge information by ID: This case executes a SELECT
          // query on the l1edges table to retrieve a specific row based on the edgeID
          // parameter. It then iterates through the result set using a while loop,
          // creating a l1edges object for the row and passing it to the DisplayEdge
          // method of the l1edges class.
          ps = connection.prepareStatement("Select * from L1Edges where edgeID = ?");
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
        }
        default -> throw new IllegalStateException("Unexpected value: " + Case_1);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  // This method updates the coordinates of a node in the l1nodes table.
  // It prompts the user to input the node ID, the new x-coordinate, and the new y-coordinate.
  // It then creates a PreparedStatement object to execute the UPDATE query on the database, using
  // the input values as parameters.
  // If the update is successful, it prints a success message.
  static void UpdateNodeCoord(Connection connection) {
    try {
      PreparedStatement ps =
          connection.prepareStatement("UPDATE L1Nodes SET xcoord = ?, ycoord = ? WHERE nodeID = ?");
      Scanner input = new Scanner(System.in);
      System.out.print("Enter node ID: ");
      String nodeID = input.nextLine();
      System.out.print("Enter new x-coordinate: ");
      int xcoord = Integer.parseInt(input.nextLine());
      System.out.print("Enter new y-coordinate: ");
      int ycoord = Integer.parseInt(input.nextLine());
      ps.setInt(1, xcoord);
      ps.setInt(2, ycoord);
      ps.setString(3, nodeID);
      int updatedRows = ps.executeUpdate();
      if (updatedRows == 0) {
        System.out.println("Node with ID " + nodeID + " not found.");
      } else {
        System.out.println("Node with ID " + nodeID + " updated successfully.");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  // This method updates the long name of a location node in the l1nodes table.
  // It prompts the user to input the node ID and the new location name.
  // It then creates a PreparedStatement object to execute the UPDATE query on the
  // database, using the input values as parameters. If the update is successful,
  // it prints a success message.
  static void UpdateNodeLocName(Connection connection) {
    try {
      PreparedStatement ps =
          connection.prepareStatement(
              "UPDATE L1Nodes SET longName = ?, shortName = ? WHERE nodeID = ?");
      Scanner input = new Scanner(System.in);
      System.out.print("Enter node ID: ");
      String nodeID = input.nextLine();
      System.out.print("Enter new long name: ");
      String longName = input.nextLine();
      System.out.print("Enter new short name: ");
      String shortName = input.nextLine();
      ps.setString(1, longName);
      ps.setString(2, shortName);
      ps.setString(3, nodeID);
      int updatedRows = ps.executeUpdate();
      if (updatedRows == 0) {
        System.out.println("Node with ID " + nodeID + " not found.");
      } else {
        System.out.println("Node with ID " + nodeID + " updated successfully.");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  // This method exports the contents of the l1nodes table to a CSV file
  // named nodes.csv. It creates a ResultSet object by executing a SELECT
  // query on the table. It then writes each row of the result set to the
  // CSV file using a FileWriter. If the export is successful,
  // it prints a success message.
  static void ExportNode(Connection connection) {
    try {
      Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM L1Nodes");

      FileWriter csvWriter = new FileWriter("nodes.csv");
      csvWriter.append("nodeID,xcoord,ycoord,floor,building,nodeType,longName,shortName\n");

      while (rs.next()) {
        csvWriter.append(rs.getString("nodeID")).append(",");
        csvWriter.append(rs.getInt("xcoord") + ",");
        csvWriter.append(rs.getInt("ycoord") + ",");
        csvWriter.append(rs.getString("floor")).append(",");
        csvWriter.append(rs.getString("building")).append(",");
        csvWriter.append(rs.getString("nodeType")).append(",");
        csvWriter.append(rs.getString("longName")).append(",");
        csvWriter.append(rs.getString("shortName")).append("\n");
      }

      csvWriter.flush();
      csvWriter.close();

      System.out.println("Node table exported to nodes.csv");

    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }
  // This method imports data from a CSV file into the l1nodes table.
  // It prompts the user to input the name of the CSV file to import.
  // It then reads each row of the file using a BufferedReader,
  // and creates a PreparedStatement object to execute an INSERT
  // query on the database for each row. If the import is successful,
  // it prints a success message.
  static void ImportNode(Connection connection) {
    try {
      Scanner input = new Scanner(System.in);
      System.out.println("Please input the name of the CSV file to import:");
      String fileName = input.nextLine();

      BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        PreparedStatement ps =
            connection.prepareStatement("INSERT INTO L1Nodes VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, data[0]);
        ps.setInt(2, Integer.parseInt(data[1]));
        ps.setInt(3, Integer.parseInt(data[2]));
        ps.setString(4, data[3]);
        ps.setString(5, data[4]);
        ps.setString(6, data[5]);
        ps.setString(7, data[6]);
        ps.setString(8, data[7]);
        ps.executeUpdate();
      }
      csvReader.close();
      System.out.println("CSV file imported successfully");
    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }
  // This method simply prints a message explaining the functionalities
  // of the program and how to use them.
  static void DisplayHelp() {
    System.out.println(
        """
            This program provides the following functionalities:
            1- Display node and edge information
            2- Update node coordinates
            3- Update name of location node
            4- Import from a CSV file into the node table
            5- Display Help on how to use your database program
            6- Export node table into a CSV file
            7- Exit the program
            To select a functionality, enter the corresponding number and follow the instructions.
            """);
  }
  // This method closes the database connection and exits the program with a success message.
  static void ExitProgram(Connection connection) {
    try {
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Program exited successfully");
    System.exit(0);
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
          case "1" -> {
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
          }
          case "2" ->
          // Update node coordinates
          UpdateNodeCoord(connection);
          case "3" ->
          // Update name of location node
          UpdateNodeLocName(connection);
          case "4" ->
          // Import from a CSV file into the node table
          ImportNode(connection);
          case "5" ->
          // Display Help on how to use your database program
          DisplayHelp();
          case "6" ->
          // Export node table into a CSV file
          ExportNode(connection);
          case "7" ->
          // Exit the program
          ExitProgram(connection);
        }
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
  }
}
