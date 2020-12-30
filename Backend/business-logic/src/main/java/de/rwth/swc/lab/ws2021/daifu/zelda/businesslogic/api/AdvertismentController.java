package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.CustomerAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.InputMismatchException;
import java.util.List;
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
        Set<CustomerAdvertisement> customerAdvertisements = customer.getCustomerAdvertisments();
        for (CustomerAdvertisement customerAdvertisement: customerAdvertisements) {
            String url = "http://localhost:8082/productive-data-service/v1/customerAdvertisementData/get?advertisementCampaignId="+customerAdvertisement.getAdvertismentCampaign().getId()+"&customerId="+customer.getId();
            //TODO pass response to ML Model and save productId in a list, if ML response is True.
        }
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
