package csis3275.project.seasell.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;

@Entity
@Data
public class BalanceAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;

    private BigDecimal availableBalance;

    private BigDecimal heldBalance;

    private OffsetDateTime lastUpdate;
}
