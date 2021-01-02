package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts.CurrentAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreditCard {

    @ApiModelProperty(required = false, hidden = true)
    private Integer id;

    @ApiModelProperty(required = true, hidden = true)
    private Integer creditCardNumber;

    private Double creditLine;

    private LocalDateTime nextDebitingDay;

    @JsonBackReference(value = "credit-card")
    private CurrentAccount account;

    /*
    *     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference(value = "customer-accounts")
    protected Customer customer;*/
}
