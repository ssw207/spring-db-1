package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV0;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV0 memberRepositoryV0;

    public void accountTransfer(String fromId, String toId, int money) {
        // 트렌젝션 시작
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            bizLogic(fromId, toId, money);
            transactionManager.commit(transaction);
        } catch (Exception e) {
            transactionManager.rollback(transaction);
            throw new IllegalStateException(e);
        }
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member from = memberRepositoryV0.findById(fromId);
        Member to = memberRepositoryV0.findById(toId);

        memberRepositoryV0.update(fromId, from.getMoney() - money);
        memberRepositoryV0.update(toId, to.getMoney() + money);
    }
}

