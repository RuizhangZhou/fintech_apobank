package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Set;

@RestController
public class AccountController {

    @Autowired RestTemplate restTemplate;

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(@RequestParam(value = "account_number", defaultValue = "0") String account_number ) {
        String urlString = "http://localhost:8080/api/v1/accounts/"+ account_number + "?getBy=account_number";
        if(account_number.equals("0")){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString() + ": No account_number was provided" , HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Account> accountResponseEntity;
        try {
            accountResponseEntity = restTemplate.getForEntity(urlString, Account.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid account_number", HttpStatus.NOT_FOUND);
        }
        if(!accountResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + accountResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountResponseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccountsByCustomer(@RequestParam(value = "customer_number", defaultValue = "0") String customer_number ) {
        String urlString = "http://localhost:8080/api/v1/customers/"+ customer_number + "?getBy=customer_number";
        if(customer_number.equals("0")){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString() + ": No customer_number was provided" , HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Customer> customerResponseEntity;
        try {
            customerResponseEntity = restTemplate.getForEntity(urlString, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid customer_number", HttpStatus.NOT_FOUND);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerResponseEntity.getBody().getAccounts(), HttpStatus.OK);
    }

}
