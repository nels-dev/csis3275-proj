package csis3275.project.seasell.account.model;

import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;

@Entity
@Data
public class WithdrawalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private AppUser createdBy;

    private String bankInstitutionNumber;

    private String bankTransitNumber;

    private String bankAccountNumber;

    private String beneficiaryName;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String rejectReason;
}
