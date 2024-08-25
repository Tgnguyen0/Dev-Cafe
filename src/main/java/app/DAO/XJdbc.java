package app.DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

// data source=DESKTOP-LDD5UPA\MSSQLSERVER13;initial catalog=master;trusted_connection=true

public class XJdbc {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost\\\\MSSQLSERVER13:1433;databasename=IT_Coffee;encrypt=false";
    private static String username="sa";
    private static String password="admin@123";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;

        if (sql.trim().startsWith("{")) {
            pstmt = con.prepareCall(sql);
        } else {
            pstmt = con.prepareStatement(sql);
        }
    
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i+1, args[i]);
        }

        return pstmt;
    }

    public void update(String sql, Object...args) throws SQLException {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);

            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);

            if (rs.next()) {
                return rs.getObject(0);
            }

            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
