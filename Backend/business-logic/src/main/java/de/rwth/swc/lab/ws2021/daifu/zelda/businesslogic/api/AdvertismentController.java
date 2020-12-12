package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.CostumerAdvertisment;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api.CustomerController;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RestController
@RequestMapping("/advertisment")
public class AdvertismentController {

    @Autowired
    private CustomerController costumerController;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"

            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisments(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
        ResponseEntity<?> customerResponseEntity = this.costumerController.getCustomer(customer_number);
        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }
        Customer customer = (Customer) customerResponseEntity.getBody();
        return new ResponseEntity<>(customer.getCustomerAdvertisments(), HttpStatus.OK);
        /*  There's a warning that casting like this may produce a NullPointerException.
            If the error handling of CustomerController.getCustomer() works correctly this shouldn't happen, because this is only executed with a good HttpStatus.
         */
    }
}
