package until;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by Jung on 2016/10/23.
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.sql.SQLException;

public final class ConnectionManager {
    private static ConnectionManager instance;
    private static ComboPooledDataSource ds;

    // 初始化,只执行一次
    static {
        ResourceBundle rb = ResourceBundle.getBundle("c3p0");
        ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(rb.getString("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(rb.getString("url"));
        ds.setUser(rb.getString("username"));
        ds.setPassword(rb.getString("password"));
    }

    public synchronized static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        DataSources.destroy(ds);
        super.finalize();
    }

}
