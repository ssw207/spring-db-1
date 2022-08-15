package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV0;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class MemberServiceV2 {

    private final TransactionTemplate transactionTemplate;
    private final MemberRepositoryV0 memberRepositoryV0;

    public MemberServiceV2(PlatformTransactionManager transactionManager, MemberRepositoryV0 memberRepositoryV0) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.memberRepositoryV0 = memberRepositoryV0;
    }

    public void accountTransfer(String fromId, String toId, int money) {
        transactionTemplate.executeWithoutResult((status) -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });

    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member from = memberRepositoryV0.findById(fromId);
        Member to = memberRepositoryV0.findById(toId);

        memberRepositoryV0.update(fromId, from.getMoney() - money);
        memberRepositoryV0.update(toId, to.getMoney() + money);
    }
}

