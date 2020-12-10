package de.rwth.swc.lab.ws2021.daifu.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.businesslogic.sqlapiconsume.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @GetMapping("/customer")
    public Customer getCustomer(@Autowired RestTemplate restTemplate , @RequestParam(value = "id", defaultValue = "1") String id ) {
        String urlString = "http://localhost:8080/api/v1/customers/"+ id + "?getBy=customer_number";
        Customer customer = restTemplate.getForObject(urlString, Customer.class);
        return customer;
    }
/*
    @RequestMapping(
        method = RequestMethod.GET,
        produces = "application/json")
    public String createCustomer()
       */
}
