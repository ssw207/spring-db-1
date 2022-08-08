package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

/**
 * Created by seungwoo.song on 2022-08-08
 */
@Slf4j
public class DBConnectionUtil {

	public static Connection getConnection() {
		try {
			// JDBC드라이버의 Driver 클래스를 통해 DB에 접근한다
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			log.info("get connection={}, clas=={}", connection, connection.getClass());
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
