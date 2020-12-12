package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Set;

@RestController
public class AccountController {

    @Autowired RestTemplate restTemplate;

    @GetMapping("/account")
    public Account getAccount(@RequestParam(value = "account_number", defaultValue = "1") String account_number ) {
        String urlString = "http://localhost:8080/api/v1/accounts/"+ account_number + "?getBy=account_number";
        Account account = restTemplate.getForObject(urlString, Account.class);
        return account;
    }

    @GetMapping("/accounts")
    public Set<Account> getAccountCustomer(@RequestParam(value = "customer_number", defaultValue = "1") String customer_number ) {
        String urlString = "http://localhost:8080/api/v1/customers/"+ customer_number + "?getBy=customer_number";
        Customer customer = restTemplate.getForObject(urlString, Customer.class);
        return customer.getAccounts();
    }
}
