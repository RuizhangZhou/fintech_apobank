package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.api;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.InputData;
import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories.InputDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inputData")
public class InputDataController {

    @Autowired
    InputDataRepository inputDataRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "/{id}"
    )
    @ResponseBody
    public ResponseEntity<?> getInputDataByID(@RequestParam(value = "ID") Integer ID) {
        try{
            return new ResponseEntity<>(inputDataRepository.findByID(ID), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<?> createInputData(@RequestBody InputData inputData) {
        try{
            return new ResponseEntity<>(inputDataRepository.save(inputData), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = "application/json",
            value = "/{id}"
    )
    public ResponseEntity<?> deleteInputData(@RequestParam(value = "ID") Integer ID) {
        try{
            inputDataRepository.delete(inputDataRepository.findByID(ID));
            return new ResponseEntity<>("InputData with ID "+ID+" successfully deleted.", HttpStatus.OK);
        }
        catch (Exception e){
            if (e.toString().equals("java.lang.IllegalArgumentException: The given entity must not be null!")){
                return new ResponseEntity<>("No InputData with ID "+ID+" found.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
