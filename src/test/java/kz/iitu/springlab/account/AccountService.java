package kz.iitu.springlab.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuditService auditService;

    public AccountService(AccountRepository accountRepository, AuditService auditService) {
        this.accountRepository = accountRepository;
        this.auditService = auditService;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        Account to = accountRepository.findById(toId).orElseThrow();

        from.debit(amount);
        to.credit(amount);

        accountRepository.save(from);
        accountRepository.save(to);
    }

    @Transactional
    public void transferWithRollback(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        Account to = accountRepository.findById(toId).orElseThrow();

        from.debit(amount);
        accountRepository.save(from);

        // Искусственное исключение для проверки отката транзакции
        throw new RuntimeException("Forced exception to test rollback");
    }

    @Transactional
    public void transferWithAuditPropagation(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        from.debit(amount);
        accountRepository.save(from);

        // Лог сохраняется в отдельной независимой транзакции (REQUIRES_NEW)
        auditService.logActionRequiresNew("Transfer attempt from " + fromId);

        // Главная транзакция падает
        throw new RuntimeException("Main transaction failed!");
    }
}