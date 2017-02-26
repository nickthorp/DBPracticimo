import java.sql.*;

public class DBPractice {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://192.168.0.5:3306/HOARD";

    private static final String USER = "nthorp";
    private static final String PASS = "nopat";

    private static final String SQL = "SELECT * FROM hoard_user";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating a statement...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String user_email  = rs.getString("user_email");
                String user_username = rs.getString("user_username");
                String user_first_name = rs.getString("user_first_name");
                String user_last_name = rs.getString("user_last_name");

                //Display values
                System.out.print("Email: " + user_email);
                System.out.print(", Username: " + user_username);
                System.out.print(", First: " + user_first_name);
                System.out.println(", Last: " + user_last_name);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            System.out.println("Uh oh!");
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }
        System.out.println("Peace!");
    }
}
