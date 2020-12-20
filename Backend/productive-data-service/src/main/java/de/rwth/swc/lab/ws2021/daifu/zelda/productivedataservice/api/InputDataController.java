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
    public ResponseEntity<?> getInputDataByID_(@RequestParam(value = "ID") Integer ID) {
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
}
