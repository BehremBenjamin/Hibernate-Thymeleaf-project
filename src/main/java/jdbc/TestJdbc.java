package jdbc;

import java.sql.*;

public class TestJdbc {
    final public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/students_project";
        String user = "hibernateProject";
        String password = "hibernateProject";
        Connection con = null;
        Statement st = null;

        try {
            System.out.println("Connecting to DB " + jdbcUrl);
            con = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connected!!!");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Student");
            printResultSet(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
