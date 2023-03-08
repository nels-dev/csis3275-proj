package csis3275.project.seasell.account.service;

import static csis3275.project.seasell.account.model.TransactionType.DEPOSIT_FULFILLED;
import static csis3275.project.seasell.account.model.TransactionType.PURCHASE_CREDIT_HOLD;
import static csis3275.project.seasell.account.model.TransactionType.PURCHASE_CREDIT_RELEASE;
import static csis3275.project.seasell.account.model.TransactionType.SALES_PROCEED;
import static csis3275.project.seasell.account.model.TransactionType.WITHDRAWAL_REJECTED;
import static csis3275.project.seasell.account.model.TransactionType.WITHDRAWAL_REQUESTED;

import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.account.model.JournalEntry;
import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository repository;

    @Transactional
    public void post(BalanceAccount to, BigDecimal amount, TransactionType type, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        JournalEntry entry = new JournalEntry();
        entry.setDescription(description);
        entry.setCreatedAt(OffsetDateTime.now());
        entry.setAccount(to);
        entry.setTransactionType(type);
        entry.setAmount(amount);
        adjustAccountBalance(to, amount, type);
        repository.save(entry);
    }

    private void adjustAccountBalance(BalanceAccount to, BigDecimal amount, TransactionType type) {
        BigDecimal adjustmentToAvailableBalance = BigDecimal.ZERO;
        BigDecimal adjustmentToHeldBalance = BigDecimal.ZERO;
        if (type == DEPOSIT_FULFILLED || type == WITHDRAWAL_REJECTED || type == SALES_PROCEED) {
            adjustmentToAvailableBalance = amount;
        } else if (type == PURCHASE_CREDIT_RELEASE) {
            adjustmentToHeldBalance = amount.negate();
        } else if (type == WITHDRAWAL_REQUESTED) {
            adjustmentToAvailableBalance = amount.negate();
        } else if (type == PURCHASE_CREDIT_HOLD) {
            adjustmentToAvailableBalance = amount.negate();
            adjustmentToHeldBalance = amount;
        } else {
            throw new IllegalArgumentException("Transaction type not configured.");
        }
        to.setAvailableBalance(to.getAvailableBalance().add(adjustmentToAvailableBalance));
        to.setHeldBalance(to.getHeldBalance().add(adjustmentToHeldBalance));
    }
}
