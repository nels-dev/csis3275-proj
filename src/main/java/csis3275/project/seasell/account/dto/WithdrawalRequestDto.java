package csis3275.project.seasell.account.dto;

import csis3275.project.seasell.account.model.RequestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class WithdrawalRequestDto {
    private long id;
    private OffsetDateTime createdAt;
    private String createdByEmail;
    private int createdById;
    @NotBlank
    @Length(min = 3, max = 3)
    private String bankInstitutionNumber;
    @NotBlank
    @Length(min = 5, max = 5)
    private String bankTransitNumber;
    @NotBlank
    private String bankAccountNumber;
    @NotBlank
    private String beneficiaryName;
    @Positive
    private BigDecimal amount;
    private RequestStatus status;
    private String rejectReason;
}
