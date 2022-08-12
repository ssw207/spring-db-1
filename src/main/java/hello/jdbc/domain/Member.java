package hello.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by seungwoo.song on 2022-08-08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String memberId;
	private int money;


}
