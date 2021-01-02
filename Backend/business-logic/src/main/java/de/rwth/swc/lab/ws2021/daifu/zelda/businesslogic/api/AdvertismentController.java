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

    //TODO this shouldn't be exposed to frontend
    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisements(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
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

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "shouldShow"
            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> shouldShowAdvertisement(@RequestParam(value = "customer_number", required = false, defaultValue = "0")String customer_number,
                                                         @RequestParam(value = "campaign_number", required = false, defaultValue = "0")String campaign_number,
                                                         @RequestParam(value = "advertisement_number", required = false, defaultValue = "0")String advertisement_number){
        //TODO use PRIMARY KEYS of specific advertisement and campaign as parameters to identify what the ML should check for
        //Alex's DataHandler has to be edied in order for this to work because there aren't any primary keys

        //TODO 1. Get Data from MySQL and MongoDB needed for ML

        //TODO 2. Send Data to ML

        //TODO 3. Send result (true/false) to frontend;

        return new ResponseEntity<>("Not yet implemented! :(", HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            value = "recordResult"
            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> recordAdvertisementReaction(@RequestParam(value = "customer_number", required = false, defaultValue = "0")String customer_number,
                                                         @RequestParam(value = "campaign_number", required = false, defaultValue = "0")String campaign_number,
                                                         @RequestParam(value = "advertisement_number", required = false, defaultValue = "0")String advertisement_number,
                                                         @RequestParam(value = "successful", required = false, defaultValue = "false")String successful){
        //TODO use PRIMARY KEYS of specific advertisement and campaign as parameters to identify where to record the result
        //Alex's DataHandler has to be edied in order for this to work because there aren't any primary keys

        return new ResponseEntity<>("Not yet implemented! :(", HttpStatus.NOT_IMPLEMENTED);
    }

}
