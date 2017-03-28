import java.sql.*;
import java.util.*;


public class TestMySQLSSL
{
        public static void main (String[] args)
        {
                Connection con = null;
                try {
                        System.out.println("Before loading SQLServerDriver:");
                        listDrivers();
                        Class.forName("com.mysql.jdbc.Driver") ;
                        System.out.println("After loading SQLServerDriver:");
                        listDrivers();

                } catch (Exception e) {
                        System.err.println("Exception: "+e.getMessage());
                }
                try
                {
                        String url = "jdbc:mysql://somerdsurl.rds.amazonaws.com:3306/ibisstaging"+
                                "?verifyServerCertificate=true"+
                                "&useSSL=true"+
                                "&requireSSL=true";
                        String user = "login";
                        String password = "passwd";

                        Class dbDriver = Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(url, user, password);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
                finally
                {
                        if (con != null)
                        {
                                try
                                {
                                        con.close();
                                }
                                catch (Exception e){}
                        }
                }
        }

        private static void listDrivers() {
                Enumeration driverList = DriverManager.getDrivers();
                while (driverList.hasMoreElements()) {
                        Driver driverClass = (Driver) driverList.nextElement();
                        System.out.println("   "+driverClass.getClass().getName());
                }
        }
}
