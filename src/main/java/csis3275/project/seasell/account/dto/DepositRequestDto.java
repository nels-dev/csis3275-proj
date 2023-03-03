package csis3275.project.seasell.account.dto;

import csis3275.project.seasell.account.model.RequestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class DepositRequestDto {
    private long id;
    private OffsetDateTime createdAt;
    private String createdByEmail;
    private int createdById;

    @NotNull
    private LocalDate depositDate;
    @NotBlank
    private String bankReference;
    @Positive
    private BigDecimal amount;
    private RequestStatus status;
    private String rejectReason;
}
