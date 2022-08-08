package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

/**
 * Created by seungwoo.song on 2022-08-08
 */
class MemberRepositoryV0Test {

	MemberRepositoryV0 repository = new MemberRepositoryV0();

	@Test
	void crud() {
		Member member = new Member("member", 10000);
		repository.save(member);
	}
}