package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV0;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.utils.CustomTransactionTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

@Slf4j
public class MemberServiceV2_1 {

    private final CustomTransactionTemplate transactionTemplate;
    private final MemberRepositoryV1 memberRepositoryV1;

    public MemberServiceV2_1(PlatformTransactionManager transactionManager, MemberRepositoryV1 memberRepositoryV1) {
        this.transactionTemplate = new CustomTransactionTemplate(transactionManager);
        this.memberRepositoryV1 = memberRepositoryV1;
    }

    public void accountTransfer(String fromId, String toId, int money) {
        transactionTemplate.executeWithoutResult(() -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member from = memberRepositoryV1.findById(fromId);
        Member to = memberRepositoryV1.findById(toId);

        memberRepositoryV1.update(fromId, from.getMoney() - money);
        memberRepositoryV1.update(toId, to.getMoney() + money);
    }
}

