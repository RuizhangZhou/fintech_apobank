package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(
        parent = Account.class,
        discriminator = "type"
)
public class FixedDepositAccount extends AbstractSavingAccount{

    private LocalDateTime completionDate;
}
