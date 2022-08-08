package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by seungwoo.song on 2022-08-08
 */
@Slf4j
public class MemberRepositoryV0 {

	public Member save(Member member) {
		String sql = "insert into member(member_id, money) values (? , ?)";

		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, member.getMemberId());
			pstmt.setInt(2, member.getMoneyy());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error("db error", e);
		}
		return member;
	}

	private Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}
}
