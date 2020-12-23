package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.CustomerAdvertisementData;
import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories.CustomerAdvertisementDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customerAdvertisementData")
public class CustomerAdvertisementDataController {

    @Autowired
    CustomerAdvertisementDataRepository customerAdvertisementDataRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "get"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementData(@RequestParam(value = "customerNumber") Integer customerNumber, @RequestParam(value = "productId") Integer productId) {
        try{
            List<CustomerAdvertisementData> byCustomerNumber = customerAdvertisementDataRepository.findByCustomerNumber(customerNumber);
            List<CustomerAdvertisementData> byProductId = customerAdvertisementDataRepository.findByProductId(productId);

            for (CustomerAdvertisementData cN : byCustomerNumber) {
                for (CustomerAdvertisementData cP : byProductId)
                    if((cN.getCustomerNumber().equals(cP.getCustomerNumber())) && (cN.getProductId().equals(cP.getProductId()))) {
                        return new ResponseEntity<>(cN, HttpStatus.OK);
                    }
            }
            return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerNumber "+customerNumber+" and productId "+productId, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getByCustomerNumber"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementDataByCustomerNumber(@RequestParam(value = "customerNumber") Integer customerNumber) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByCustomerNumber(customerNumber);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerNumber "+customerNumber, HttpStatus.NOT_FOUND);
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
            value = "getByProductId"
    )
    @ResponseBody
    public ResponseEntity<?> getCustomerAdvertisementDataByProductId(@RequestParam(value = "productId") Integer productId) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByProductId(productId);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with productId "+productId, HttpStatus.NOT_FOUND);
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
            method = RequestMethod.DELETE,
            produces = "application/json",
            value = "delete"
    )
    @ResponseBody
    public ResponseEntity<?> deleteCustomerAdvertisementData(@RequestParam(value = "customerNumber") Integer customerNumber, @RequestParam(value = "productId") Integer productId) {
        try{
            List<CustomerAdvertisementData> byCustomerNumber = customerAdvertisementDataRepository.findByCustomerNumber(customerNumber);
            List<CustomerAdvertisementData> byProductId = customerAdvertisementDataRepository.findByProductId(productId);

            for (CustomerAdvertisementData cN : byCustomerNumber) {
                for (CustomerAdvertisementData cP : byProductId)
                    if((cN.getCustomerNumber().equals(cP.getCustomerNumber())) && (cN.getProductId().equals(cP.getProductId()))) {
                        customerAdvertisementDataRepository.delete(cN);
                        return new ResponseEntity<>("Successfully deleted CustomerAdvertisementData with customerNumber "+customerNumber+" and productId "+productId, HttpStatus.OK);
                    }
            }
            return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerNumber "+customerNumber+" and productId "+productId, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = "application/json",
            value = "deleteByCustomerNumber"
    )
    @ResponseBody
    public ResponseEntity<?> deleteCustomerAdvertisementDataByCustomerNumber(@RequestParam(value = "customerNumber") Integer customerNumber) {
        try{
            List<CustomerAdvertisementData> customerAdvertisementDataList = customerAdvertisementDataRepository.findByCustomerNumber(customerNumber);
            if(customerAdvertisementDataList.isEmpty()){
                return new ResponseEntity<>("Could not find CustomerAdvertisementData with customerNumber "+customerNumber, HttpStatus.NOT_FOUND);
            }
            for (CustomerAdvertisementData cad : customerAdvertisementDataList) {
                customerAdvertisementDataRepository.delete(cad);
            }
            return new ResponseEntity<>("Successfully deleted all CustomerAdvertisementData with customerNumber "+customerNumber, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
