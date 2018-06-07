package e_shopping.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.DataSource;

public class ConnectionUtils {
	
	private static ConnectionPool pool;
	
	private static void Initialize() {
		if(pool == null) {
			DataSource dataSource = new DataSource();
		    dataSource.setDriverClassName("org.postgresql.Driver");
		    dataSource.setUrl("jdbc:postgresql://localhost:3000/E-shop");
		    dataSource.setUsername("postgres");
		    /*dataSource.setPassword(""); here password for db*/
		    dataSource.setInitialSize(5);
		    dataSource.setMaxActive(10);
		    dataSource.setMaxIdle(5);
		    dataSource.setMinIdle(2);
	
		    try {
				pool = dataSource.createPool();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Initialize();
		// get connection from pool
		Connection c = pool.getConnection();
        return c;
	}
	
	public static void closeQuietly(Connection conn) throws SQLException {
            conn.close();
    }
 
    public static void rollbackQuietly(Connection conn) throws SQLException {
            conn.rollback();
    }
}
