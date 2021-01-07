package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractSavingAccount.class, name = "abstractSavingAccount"),
        @JsonSubTypes.Type(value = CallMoneyAccount.class, name = "callMoneyAccount"),
        @JsonSubTypes.Type(value = FixedDepositAccount.class, name = "fixedDepositAccount"),
        @JsonSubTypes.Type(value = SavingsBook.class, name = "savingsBook")
})
@ApiModel(
        parent = Account.class,
        discriminator = "type"
)
public abstract class AbstractSavingAccount extends Account {

    private Float interest;
}