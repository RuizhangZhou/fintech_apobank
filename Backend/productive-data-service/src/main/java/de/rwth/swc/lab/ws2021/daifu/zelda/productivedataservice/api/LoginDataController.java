package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.LoginData;
import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories.LoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loginData")
public class LoginDataController {

    @Autowired
    LoginDataRepository loginDataRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "getAll"
    )
    @ResponseBody
    public ResponseEntity<?> getAll() {
        try{
            List<LoginData> LoginDataList = loginDataRepository.findAll();
            return new ResponseEntity<>(LoginDataList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "get"
    )
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(value = "customer_number") Integer customer_number) {
        try{
            List<LoginData> LoginDataList = loginDataRepository.findByCustomerNumber(customer_number);
            if(LoginDataList.isEmpty()){
                return new ResponseEntity<>("Could not find LoginData with customer_number "+customer_number, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(LoginDataList.get(0), HttpStatus.OK);
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
    public ResponseEntity<?> create(@RequestBody LoginData LoginData) {
        try{
            return new ResponseEntity<>(loginDataRepository.save(LoginData), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "deleteAll",
            produces = "application/json",
            method = {RequestMethod.DELETE}
    )
    @ResponseBody
    public ResponseEntity<?> deleteAll() {
        try{
            loginDataRepository.deleteAll();
            return new ResponseEntity<>("Successfully deleted all LoginData", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "delete",
            produces = "application/json",
            method = {RequestMethod.DELETE}

    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam(value = "customer_number") Integer customer_number) {
        try{
            List<LoginData> LoginDataList = loginDataRepository.findByCustomerNumber(customer_number);
            if(LoginDataList.isEmpty()){
                return new ResponseEntity<>("Could not find LoginData with customer_number "+customer_number, HttpStatus.NOT_FOUND);
            }
            for (LoginData cad : LoginDataList) {
                loginDataRepository.delete(cad);
            }
            return new ResponseEntity<>("Successfully deleted all LoginData with customer_number "+customer_number, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
