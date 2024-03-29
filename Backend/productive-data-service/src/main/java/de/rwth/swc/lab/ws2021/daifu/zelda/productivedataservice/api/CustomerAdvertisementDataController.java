package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.CustomerAdvertisementData;
import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories.CustomerAdvertisementDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerAdvertisementData")
public class CustomerAdvertisementDataController {

    @Autowired
    CustomerAdvertisementDataRepository customerAdvertisementDataRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getAll"
    )
    @ResponseBody
    public ResponseEntity<?> getAllCustomerAdvertisementData() {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findAll();
            return new ResponseEntity<>(customerAdvertisementDataList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getSpecific"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementData(@RequestParam(value = "customerId") Integer customerId, @RequestParam(value = "advertisementCampaignId") Integer advertisementCampaignId) {
        try{
            List<CustomerAdvertisementData> bycustomerId = customerAdvertisementDataRepository.findByCustomerId(customerId);
            List<CustomerAdvertisementData> byadvertisementCampaignId = customerAdvertisementDataRepository.findByAdvertisementCampaignId(advertisementCampaignId);

            for (CustomerAdvertisementData cN : bycustomerId) {
                for (CustomerAdvertisementData cP : byadvertisementCampaignId)
                    if((cN.getCustomerId().equals(cP.getCustomerId())) && (cN.getAdvertisementCampaignId().equals(cP.getAdvertisementCampaignId()))) {
                        return new ResponseEntity<>(cN, HttpStatus.OK);
                    }
            }
            return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerId "+customerId+" and advertisementCampaignId "+advertisementCampaignId, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getByCustomerId"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementDataByCustomerId(@RequestParam(value = "customerId") Integer customerId) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByCustomerId(customerId);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerId "+customerId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customerAdvertisementDataList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getByAdvertisementCampaignId"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementDataByAdvertisementCampaignId(@RequestParam(value = "advertisementCampaignId") Integer advertisementCampaignId) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByAdvertisementCampaignId(advertisementCampaignId);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with advertisementCampaignId "+advertisementCampaignId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customerAdvertisementDataList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            value = "create"
    )
    @ResponseBody
    public ResponseEntity<?> createCustomerAdvertisementData(@RequestBody CustomerAdvertisementData customerAdvertisementData) {
        try{
            return new ResponseEntity<>(customerAdvertisementDataRepository.save(customerAdvertisementData), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "deleteAll",
            produces = "application/json",
            method = {RequestMethod.DELETE, RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> deleteCustomerAllAdvertisementData() {
        try{
            customerAdvertisementDataRepository.deleteAll();
            return new ResponseEntity<>("Successfully deleted all CustomerAdvertisementData", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "deleteSpecific",
            produces = "application/json",
            method = {RequestMethod.DELETE, RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> deleteCustomerAdvertisementData(@RequestParam(value = "customerId") Integer customerId, @RequestParam(value = "advertisementCampaignId") Integer advertisementCampaignId) {
        try{
            List<CustomerAdvertisementData> byCustomerId = customerAdvertisementDataRepository.findByCustomerId(customerId);
            List<CustomerAdvertisementData> byAdvertisementCampaignId = customerAdvertisementDataRepository.findByAdvertisementCampaignId(advertisementCampaignId);

            for (CustomerAdvertisementData cN : byCustomerId) {
                for (CustomerAdvertisementData cP : byAdvertisementCampaignId)
                    if((cN.getCustomerId().equals(cP.getCustomerId())) && (cN.getAdvertisementCampaignId().equals(cP.getAdvertisementCampaignId()))) {
                        customerAdvertisementDataRepository.delete(cN);
                        return new ResponseEntity<>("Successfully deleted CustomerAdvertisementData with customerId "+customerId+" and advertisementCampaignId "+advertisementCampaignId, HttpStatus.OK);
                    }
            }
            return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerId "+customerId+" and advertisementCampaignId "+advertisementCampaignId, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "deleteByCustomerId",
            produces = "application/json",
            method = {RequestMethod.DELETE, RequestMethod.GET}

    )
    @ResponseBody
    public ResponseEntity<?> deleteCustomerAdvertisementDataByCustomerId(@RequestParam(value = "customerId") Integer customerId) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByCustomerId(customerId);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerId "+customerId, HttpStatus.NOT_FOUND);
            }
            for (CustomerAdvertisementData cad : customerAdvertisementDataList) {
                customerAdvertisementDataRepository.delete(cad);
            }
            return new ResponseEntity<>("Successfully deleted all CustomerAdvertisementData with customerId "+customerId, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
