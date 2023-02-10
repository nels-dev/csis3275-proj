package csis3275.project.seasell.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
