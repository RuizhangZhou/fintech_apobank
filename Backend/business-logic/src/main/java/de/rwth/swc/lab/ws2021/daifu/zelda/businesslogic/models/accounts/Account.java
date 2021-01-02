package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "currentAccount"),
        @JsonSubTypes.Type(value = AbstractSavingAccount.class, name = "abstractSavingAccount"),
        @JsonSubTypes.Type(value = CallMoneyAccount.class, name = "callMoneyAccount"),
        @JsonSubTypes.Type(value = FixedDepositAccount.class, name = "fixedDepositAccount"),
        @JsonSubTypes.Type(value = SavingsBook.class, name = "savingsBook")
})
@ApiModel(
        discriminator = "type",
        subTypes = {CurrentAccount.class, AbstractSavingAccount.class, CallMoneyAccount.class, FixedDepositAccount.class, SavingsBook.class}
)
public abstract class Account {

    @Id
    private Integer id;
    private Integer accountNumber;
    private Double balance;

    @JsonBackReference(value = "customer-accounts")
    @ApiModelProperty(example = "1")
    private Customer customer;

    

    public <T extends Account> boolean isOfSameInstance(T otherAccount) {
        return (this.getClass().equals(otherAccount.getClass()));
    }

    @Override
    public String toString() {
        return "The Account is " + accountNumber + ": " + balance;
    }
}
