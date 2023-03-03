package csis3275.project.seasell.account.repository;

import csis3275.project.seasell.account.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
