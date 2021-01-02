package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.CreditCard;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(
        parent = Account.class,
        discriminator = "type"
)
public class CurrentAccount extends Account{

    private Double overdraftInterest;

    private Double overdraftLimit;

    @JsonManagedReference(value = "credit-card")
    private Set<CreditCard> creditCards;
}
