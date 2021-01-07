package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.AdvertisementCampaign;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.Customer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.CustomerAdvertisement;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/advertisment")
public class AdvertisementController {

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
    public ResponseEntity<?> getAdvertisementCampaigns(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
        // get customer
        String url = "http://localhost:8080/api/v1/customers/"+customer_number+"?getBy=customer_number";
        ResponseEntity<?> customerResponseEntity;
        try {
            customerResponseEntity = restTemplate.getForEntity(url, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Customer customer = (Customer) customerResponseEntity.getBody();
        // get list of customer advertisements and filter for running campaigns
        List<CustomerAdvertisement> customerAdvertisements = new ArrayList<>(customer.getCustomerAdvertisements());
        List<AdvertisementCampaign> runningCampaigns = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(customerAdvertisements.isEmpty()){
            return new ResponseEntity<>(runningCampaigns, HttpStatus.OK);
        }
        for (CustomerAdvertisement customerAdvertisement: customerAdvertisements) {
            // get campaign
            url = "http://localhost:8080/api/v1/advertisementCampaigns/"+customerAdvertisement.getId().getAdvertisementCampaignId();
            ResponseEntity<?> campaignResponseEntity;
            try {
                campaignResponseEntity = restTemplate.getForEntity(url, AdvertisementCampaign.class);
            }catch (Exception e){
                return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            AdvertisementCampaign campaign = (AdvertisementCampaign) campaignResponseEntity.getBody();
            // check end-date
            if(LocalDate.now().isBefore(campaign.getEndDate()) && LocalDate.now().isAfter(campaign.getStartDate())){
                runningCampaigns.add(campaign);
            }
        }
        // check for ML response
        List<AdvertisementCampaign> result = new ArrayList<>();
        for (AdvertisementCampaign campaign: runningCampaigns) {
            // get productive data
            url = "http://localhost:8082/productive-data-service/v1/customerAdvertisementData/getSpecific?advertisementCampaignId="+campaign.getId()+"&customerId="+customer.getId();
            ResponseEntity<?> response;
            try {
                response = restTemplate.getForEntity(url, InputData.class);
            } catch (Exception e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(fakeML((InputData) response.getBody())){
                result.add(campaign);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public boolean fakeML(InputData inputData){
        Integer chance = 80; // chance in % for ML model to decide to show the advertisement
        Random random = new Random();
        Integer randomNumber = random.nextInt(100);
        if(randomNumber < chance){
            return true;
        }
        else{
            return false;
        }
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
