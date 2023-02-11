package csis3275.project.seasell.account.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BalanceAccountDto {

    int id;

    BigDecimal availableBalance;

    BigDecimal heldBalance;

    OffsetDateTime lastUpdate;
}
