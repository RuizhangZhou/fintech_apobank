package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"

            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> getCustomer(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
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

        return new ResponseEntity<>(customerResponseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "login"
    )
    @ResponseBody
    public ResponseEntity<?> loginTest(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
        //TODO if sucessful return {sucessfull:true}, else return {sucess}
        ResponseEntity<?> response = getCustomer(customer_number);
        if( ( response.getStatusCode() == HttpStatus.OK ) && ( !response.getBody().toString().toLowerCase().contains("error") ) ){
            return new ResponseEntity<>("Login, sucessfull", HttpStatus.OK);
        }
        else{
            return response;
        }
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            value = "register"
    )
    @ResponseBody
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer){
        String urlString = "http://localhost:8080/api/v1/customers";
        ResponseEntity<Customer> customerResponseEntity;

        //TODO create an account for the customer

        try {
            customerResponseEntity = restTemplate.postForEntity(urlString, customer, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error: customer not created", HttpStatus.BAD_REQUEST);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerResponseEntity.getBody(), HttpStatus.OK);
    }

}
