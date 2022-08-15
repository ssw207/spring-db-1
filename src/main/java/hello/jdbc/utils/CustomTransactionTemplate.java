package hello.jdbc.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class CustomTransactionTemplate {

    private final PlatformTransactionManager platformTransactionManager;

    public void executeWithoutResult(Runnable runnable) {

        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            platformTransactionManager.commit(status);
            runnable.run();
        } catch (Exception e) {
            platformTransactionManager.rollback(status);
        }
    }
}
