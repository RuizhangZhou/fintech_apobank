package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts.*;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

@RestController
public class AccountController {

    @Autowired RestTemplate restTemplate;

    @GetMapping("/accounts/{account_number}")
    public ResponseEntity<?> getAccount(@PathVariable(value = "account_number") String account_number ) {
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

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestParam(value = "customer_number", defaultValue = "0") String customer_number,
                                           @RequestParam(value = "type", defaultValue = "currentAccount") String type) {
        if(customer_number.equals("0")){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString() + ": No customer_number was provided" , HttpStatus.BAD_REQUEST);
        }

        //get Customer
        String customerUrlString = "http://localhost:8080/api/v1/customers/"+ customer_number + "?getBy=customer_number";
        if(customer_number.equals("0")){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString() + ": No customer_number was provided" , HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Customer> customerResponseEntity;
        try {
            customerResponseEntity = restTemplate.getForEntity(customerUrlString, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid customer_number", HttpStatus.NOT_FOUND);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        //if code reaches here, Customer was requested successfully
        Account acc;
        switch(type){
            case "currentAccount":
                acc = new CurrentAccount();
                CurrentAccount currAcc = (CurrentAccount) acc;
                acc.setBalance(0.0);
                acc.setCustomer(customerResponseEntity.getBody());
                currAcc.setCreditCards(new HashSet<>());
                currAcc.setOverdraftInterest(3.5);
                break;
            case "callMoneyAccount":
                acc = new CallMoneyAccount();
                CallMoneyAccount callAcc = (CallMoneyAccount) acc;
                callAcc.setBalance(0.0);
                callAcc.setCustomer(customerResponseEntity.getBody());
                callAcc.setInterest(1.5f);
                break;
            case "fixedDepositAccount":
                acc = new FixedDepositAccount();
                FixedDepositAccount fixedAcc = (FixedDepositAccount) acc;
                fixedAcc.setInterest(1.5f);
                fixedAcc.setCustomer(customerResponseEntity.getBody());
                fixedAcc.setBalance(0.0);
                break;
            case "savingsBook":
                acc = new SavingsBook();
                SavingsBook bookAcc = (SavingsBook) acc;
                bookAcc.setInterest(1.5f);
                bookAcc.setBalance(0.0);
                bookAcc.setCustomer(customerResponseEntity.getBody());
                break;
            default:
                return new ResponseEntity<>("Error: No Such account type", HttpStatus.BAD_REQUEST);
        }

        String urlString = "http://localhost:8080/api/v1/accounts";
        ResponseEntity<Account> accResponseEntity;
        try {
            accResponseEntity = restTemplate.postForEntity(urlString, acc,  Account.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString()+": acc couldn't be created", HttpStatus.BAD_REQUEST);
        }

        if(!accResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + accResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(accResponseEntity.getBody(), HttpStatus.OK);
    }

    @PutMapping("/accounts")
    public ResponseEntity<?> transferMoney(@ApiParam(example = "10000000") final @RequestParam(value = "account_number", required = true, defaultValue = "0") String account_number,
                                           @ApiParam(example = "10000001") final @RequestParam(value = "send_to", required = true, defaultValue = "0") String sendTo,
                                           @ApiParam(example = "99.99") final @RequestParam(value = "amount", required = true, defaultValue = "-1") double amount) {
        ResponseEntity<?> accSender = getAccount(account_number);
        if(!accSender.getStatusCode().equals(HttpStatus.OK) || !(accSender.getBody() instanceof Account)){
            String message = accSender.getBody().toString();
            message += " (sender account)";
            new ResponseEntity<>(message, accSender.getStatusCode());
        }

        ResponseEntity<?> accReceiver = getAccount(sendTo);
        if(!accReceiver.getStatusCode().equals(HttpStatus.OK) || !(accSender.getBody() instanceof Account)){
            String message = accReceiver.getBody().toString();
            message += " (receiver account)";
            new ResponseEntity<>(message, accReceiver.getStatusCode());
        }

        if(amount <= 0){
            new ResponseEntity<>("Provide a valid amount!", HttpStatus.BAD_REQUEST);
        }

        Account sender = (Account) accSender.getBody();
        if(sender.getBalance()< amount){
            return new ResponseEntity<>("Error: Sender doesn't have enough money.", HttpStatus.BAD_REQUEST);
        }
        sender.setBalance(sender.getBalance() - amount);
        ResponseEntity<?> updatedSender = updateAcc(sender);

        if(!(updatedSender.getStatusCode() == HttpStatus.OK)){
            return new ResponseEntity<>("Error while updating sender account:" + updatedSender.getBody().toString(), HttpStatus.BAD_REQUEST);
        }

        Account receiver = (Account) accReceiver.getBody();
        receiver.setBalance(receiver.getBalance() + amount);

        ResponseEntity<?> updatedReceiver = updateAcc(receiver);
        if(!(updatedReceiver.getStatusCode() ==  HttpStatus.OK)){
            return new ResponseEntity<>("Error while updating receiver account:" + updatedReceiver.getBody().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Transfer was successful", HttpStatus.OK);
    }

    private ResponseEntity<?> updateAcc(Account account){
        String accountId = String.valueOf(account.getId().toString());
        String urlString = "http://localhost:8080/api/v1/accounts/"+ account.getId().toString();/* + "?account=" + account;*/

        ResponseEntity<Void> accountResponseEntity;
        RequestEntity<Account> entity = new RequestEntity<Account>(account, HttpMethod.PUT, URI.create(urlString));
        try {
            accountResponseEntity = restTemplate.exchange(urlString, HttpMethod.PUT, entity, Void.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid account_number", HttpStatus.NOT_FOUND);
        }
        if(!accountResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + accountResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }



}
