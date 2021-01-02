package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(
        parent = Account.class
)
public class SavingsBook extends AbstractSavingAccount{
}
