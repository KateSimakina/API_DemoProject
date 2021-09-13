package com.myProject.utilities.db;

import com.myProject.utilities.common.ConfigurationReader;
import java.sql.*;
import java.util.*;

public class DBUtility {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection() {
        String URL = ConfigurationReader.getProperty(" ");
        String userName = ConfigurationReader.getProperty(" ");
        String password = ConfigurationReader.getProperty(" ");

        try {
            connection = DriverManager.getConnection(URL, userName, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void runQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultSet: " + e.getMessage());
        }
    }

    public static void destroy(){
        try {
            if( resultSet!=null)  resultSet.close();
            if( statement!=null)  statement.close();
            if( connection!=null)  connection.close();
        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE CLOSING RESOURCES " + e.getMessage() );
        }
    }


    //get the row count
    public static int getRowCount() {
        int rowCount = 0;

        try {
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting the row count: " + e.getMessage());
        }
        return rowCount;
    }

    //get the column count
    public static int getColumnCount() {
        int columnCount = 0;
        try {
            columnCount = resultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error while getting the column count: " + e.getMessage());
        }
        return columnCount;
    }

    //get all column names
    public static List<String> getColumnNames() {

        List<String> columnNames = new ArrayList<>();
        try {
            resultSet.getMetaData();
            for (int i = 1; i <= getColumnCount(); i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting all column names: " + e.getMessage());
        }
        return columnNames;
    }

    //get a row data as a list of String
    public static List<String> getRowData(int rowNum) {

        List<String> rowData = new ArrayList<>();
        try {
            resultSet.absolute(rowNum);
            for (int i = 1; i <= getColumnCount(); i++) {
                rowData.add(resultSet.getString(i));
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting the row data: " + e.getMessage());
        }
        return rowData;
    }

    //get a particular row num and column index
    public static String getColumnDataAtRow(int rowNum, int columnIndex) {

        String result = "";
        try {
            resultSet.absolute(rowNum);
            result = resultSet.getString(columnIndex);
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting the row num and column index: " + e.getMessage());
        }
        return result;
    }

    //get a particular row num and column name
    public static String getColumnDataAtRow(int rowNum, String columnName) {
        String result = "";
        try {
            resultSet.absolute(rowNum);
            result = resultSet.getString(columnName);
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting the row num and column name: " + e.getMessage());
        }
        return result;
    }

    //get a column data as a list of String passing column index
    public static List<String> getColumnData(int columnIndex) {
        List<String> columnData = new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                columnData.add(resultSet.getString(columnIndex));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the column data: " + e.getMessage());
        }
        return columnData;
    }

    //get a column data as a list of String passing column Name
    public static List<String> getColumnData(String columnName) {
        List<String> columnData = new ArrayList<>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                columnData.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the column data: " + e.getMessage());
        }
        return columnData;
    }

    //get all data from a table
    public static void displayAllData() {
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                for (int i = 1; i <= getColumnCount(); i++) {
                    System.out.printf("%-25s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error while displaying all data: " + e.getMessage());
        }
    }

    //store a row data in a Map of String
    public static Map<String, String> getRowMap(int rowNum) {

        Map<String, String> rowMap = new LinkedHashMap<>();
        try {
            resultSet.absolute(rowNum);
            for (int i = 1; i <= getColumnCount(); i++) {
                rowMap.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting a row data: " + e.getMessage());
        }
        return rowMap;
    }

    //get a List of Maps (all data from a table)
    public static List<Map<String, String>> getAllDataAsListOfMaps() {
        List<Map<String, String>> allData = new ArrayList<>();
        try {
            resultSet.beforeFirst();
            for (int i = 1; i <= getRowCount(); i++) {
                allData.add(getRowMap(i));
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting the List of Maps (all data from a table): " + e.getMessage());
        }
        return allData;
    }

    //get the first row and column
    public static String getFirstData() {
        return getColumnDataAtRow(1, 1);
    }

    /**
     * This method will reset the cursor to before first location
     */
    private static void resetCursor(){

        try {
            resultSet.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Store All rows as List of Map object
     * @return List of List of Map object that contain each row data as Map<String,String>
     */
    public static List<Map<String,String>> getAllRowAsListOfMap(){

        List<Map<String,String>> allRowLstOfMap = new ArrayList<>();
        int rowCount = getRowCount() ;
        // move from first row till last row
        // get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount ; rowIndex++) {

            Map<String,String> rowMap = getRowMap(rowIndex);
            allRowLstOfMap.add( rowMap ) ;

        }
        resetCursor();

        return allRowLstOfMap ;

    }


}
