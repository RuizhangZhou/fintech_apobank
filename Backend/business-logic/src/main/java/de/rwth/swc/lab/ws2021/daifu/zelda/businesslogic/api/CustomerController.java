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
    public ResponseEntity<?> getCustomer(@RequestParam(value = "id", required = false, defaultValue = "0") String id ) {
        String urlString = "http://localhost:8080/api/v1/customers/"+ id + "?getBy=customer_number";
        if(id.equals("0")){
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

        return new ResponseEntity<Customer>(customerResponseEntity.getBody(), HttpStatus.OK);
    }
}
