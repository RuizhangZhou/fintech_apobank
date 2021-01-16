package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.*;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.AdvertismentStatus;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Education;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Job;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.RelationshipStatus;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "/{customer_number}"
            //TODO add authentication header here
    )
    @ResponseBody
    public ResponseEntity<?> getCustomer(@PathVariable(value = "customer_number") String customer_number ) {
        String urlString = "http://localhost:8080/api/v1/customers/"+ customer_number + "?getBy=customer_number";
        if(customer_number.equals("0")){
            return new ResponseEntity<>("Error "+ HttpStatus.BAD_REQUEST.toString() + ": No customer_number was provided" , HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Customer> customerResponseEntity;
        try {
            customerResponseEntity = restTemplate.getForEntity(urlString, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid customer_number", HttpStatus.NOT_FOUND);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerResponseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "login"
    )
    @ResponseBody
    public ResponseEntity<?> loginTest(@RequestParam(value = "customer_number", required = false, defaultValue = "0") String customer_number ) {
        //TODO if sucessful return {sucessfull:true}, else return {sucess}
        ResponseEntity<?> response = getCustomer(customer_number);
        if( ( response.getStatusCode() == HttpStatus.OK ) && ( !response.getBody().toString().toLowerCase().contains("error") ) ){
            return new ResponseEntity<>("Login, sucessfull", HttpStatus.OK);
        }
        else{
            return response;
        }
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            value = "register"
    )
    @ResponseBody
    /*public ResponseEntity<?> registerCustomer(@RequestBody Customer customer){*/
    public ResponseEntity<?> registerCustomer(@RequestParam(value = "first_name", required = false, defaultValue = "")@ApiParam(value = "first_name", example = "Test") String firstName,
                                              @RequestParam(value = "last_name", required = false, defaultValue = "")@ApiParam(value = "last_name", example = "Tester") String lastName,
                                              @RequestParam(value = "birthday", required = false, defaultValue = "")@ApiParam(value = "birthday", example = "2021-01-20") String birthday,
                                              @RequestParam(value = "street", required = false, defaultValue = "")@ApiParam(value = "street", example = "Fakestreet") String street,
                                              @RequestParam(value = "house_number", required = false, defaultValue = "0")@ApiParam(value = "house_number", example = "1") String houseNumber,
                                              @RequestParam(value = "zip_code", required = false, defaultValue = "-1")@ApiParam(value = "zip_code", example = "12345") Integer zipCode,
                                              @RequestParam(value = "city", required = false, defaultValue = "")@ApiParam(value = "city", example = "Faketown") String city){
        String urlString = "http://localhost:8080/api/v1/customers";


        if(firstName.equals("")){
            return new ResponseEntity<>("Error: No first name was provided.", HttpStatus.BAD_REQUEST);
        }
        if(lastName.equals("")){
            return new ResponseEntity<>("Error: No last name was provided.", HttpStatus.BAD_REQUEST);
        }
        if(birthday.equals("")){
            return new ResponseEntity<>("Error: No birthday was provided.", HttpStatus.BAD_REQUEST);
        }
        if(street.equals("")){
            return new ResponseEntity<>("Error: No street was provided.", HttpStatus.BAD_REQUEST);
        }
        if(houseNumber.equals("")){
            return new ResponseEntity<>("Error: No house number was provided.", HttpStatus.BAD_REQUEST);
        }
        if(zipCode == -1){
            return new ResponseEntity<>("Error: No zip code was provided.", HttpStatus.BAD_REQUEST);
        }
        if(city.equals("")){
            return new ResponseEntity<>("Error: No city was provided.", HttpStatus.BAD_REQUEST);
        }


        Customer customer = new Customer();
        customer.setId(0);
        customer.setCustomerNumber(0);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(birthday, formatter);
        customer.setBirthday(localDate);

        //set Variables for the new Customer
        Address address = new Address();
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setZipCode(zipCode);
        address.setCity(city);
        customer.setAddress(address);
        customer.setEducation(Education.UNKNOWN);
        customer.setRelationshipStatus(RelationshipStatus.SINGLE);
        customer.setJob(Job.UNKNOWN);
        customer.setMonthlyIncome(0f);
        customer.setNumberOfChildren(0);
        customer.setCustomerAdvertisements(new HashSet<>());
        customer.setAccounts(new HashSet<>());
        customer.setLoans(new HashSet<>());
        customer.setInvestments(new HashSet<>());

        //create customer
        ResponseEntity<Customer> customerResponseEntity;
        try {
            customerResponseEntity = restTemplate.postForEntity(urlString, customer, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error: customer not created3", HttpStatus.BAD_REQUEST);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString() + ", " + customerResponseEntity.getBody(), HttpStatus.BAD_REQUEST);
        }

        Customer savedCustomer = customerResponseEntity.getBody();
        //create customer advertiesements for the savedCustomer
        savedCustomer.setCustomerAdvertisements(new HashSet<>(createCAsForCustomer(savedCustomer)));

        //update the customer
        String updateString = urlString + "/" + savedCustomer.getId();
        ResponseEntity<Customer> updatedCustomerRE;
        RequestEntity<Customer> entity = new RequestEntity<>(savedCustomer, HttpMethod.PUT, URI.create(updateString));
        try {
            //updatedCustomerRE = restTemplate.postForEntity(updateString, customer, Customer.class);
            updatedCustomerRE = restTemplate.exchange(updateString, HttpMethod.PUT, entity, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error: customer not created4", HttpStatus.BAD_REQUEST);
        }

        if(!updatedCustomerRE.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + updatedCustomerRE.getStatusCode().toString() + ", " + updatedCustomerRE.getBody(), HttpStatus.BAD_REQUEST);
        }

        if(genereateProductiveData(savedCustomer.getId())){
            return new ResponseEntity<>(updatedCustomerRE.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: " + "couln't generate productive data!", HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * create CustomerAdvertisements for every Campaign and the give customer
     * @param customer the customer, for which the CAs have to be created
    * */
    private List<CustomerAdvertisement> createCAsForCustomer(Customer customer){

        //get all AdvertisementCampaigns
        //customer.setCustomerAdvertisements(new HashSet<>());
        ResponseEntity<AdvertisementCampaignPage> advertisementCampaignResponseEntity;
        String urlAdvString = "http://localhost:8080/api/v1/advertisementCampaigns";
        advertisementCampaignResponseEntity = restTemplate.getForEntity(urlAdvString, AdvertisementCampaignPage.class);
        try {

        }catch (Exception e){
            //return new ResponseEntity<>("Error: customer not created1", HttpStatus.BAD_REQUEST);
        }
        if(!advertisementCampaignResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            //return new ResponseEntity<>("Error: " + advertisementCampaignResponseEntity.getStatusCode().toString() + ", " + advertisementCampaignResponseEntity.getBody(), HttpStatus.BAD_REQUEST);
        }
        List<AdvertisementCampaign> advCamp = new ArrayList<>(advertisementCampaignResponseEntity.getBody().getContent());
        List<CustomerAdvertisement> custAdv = new ArrayList<>();
        //create CustomerAdvertisements for every Campaign
        for(int i=0;i<advCamp.size();i++){
            CustomerAdvertisement newCustAdv = new CustomerAdvertisement();
            newCustAdv.setCustomer(customer);
            newCustAdv.setStatus(AdvertismentStatus.UNKNOWN);
            newCustAdv.setNumberOfTimesDisplayed(0);
            newCustAdv.setAdvertisementCampaign(advCamp.get(i));

            CustomerAdvertisementKey key = new CustomerAdvertisementKey();
            key.setAdvertisementCampaignId(advCamp.get(i).getId());
            key.setCustomerId(customer.getId());

            newCustAdv.setId(key);

            newCustAdv.setLastDisplayDate(LocalDate.now());
            //upload custAdv
            ResponseEntity<CustomerAdvertisement> custAdvRespEnt = null;
            String uploadCustAdvString = "http://localhost:8080/api/v1/customerAdvertisements";
            try {
                custAdvRespEnt = restTemplate.postForEntity(uploadCustAdvString, newCustAdv, CustomerAdvertisement.class);
            }catch (Exception e){
                //return new ResponseEntity<>("Error: customer not created2", HttpStatus.BAD_REQUEST);
            }
            if(!custAdvRespEnt.getStatusCode().equals(HttpStatus.OK)){
                //return new ResponseEntity<>("Error: " + custAdvRespEnt.getStatusCode().toString() + ", " + custAdvRespEnt.getBody(), HttpStatus.BAD_REQUEST);
            }
            custAdv.add(custAdvRespEnt.getBody());
        }
        return custAdv;
    }

    private boolean genereateProductiveData(Integer customerId){
        String urlString = "http://localhost:8083/batch-process/v1/updateCustomerProduktiveKennzahlen?customerId=" + customerId;

        ResponseEntity<?> customerResponseEntity = null;
        try {
            customerResponseEntity = restTemplate.getForEntity(urlString,Void.class);
        }catch (Exception e){
            //return new ResponseEntity<>("Error: customer not created3", HttpStatus.BAD_REQUEST);
            System.out.println("Error1: Productive data for user couldn't be geerated");
            return false;
        }

        if(customerResponseEntity!=null && !customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            System.out.println("Error2: Productive data for user couldn't be geerated");
            return false;
        }

        return true;
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json",
            value = "/{customer_number}/address"
    )
    public ResponseEntity<?> updateAddress(@PathVariable(value = "customer_number")int customer_number,
                                           @RequestBody Address address){
        ResponseEntity<?> customerResponse = getCustomer(String.valueOf(customer_number));
        if(customerResponse.getStatusCode()!=HttpStatus.OK || !(customerResponse.getBody() instanceof Customer)){
            return new ResponseEntity<>(customerResponse.getBody(), customerResponse.getStatusCode());
        }
        Customer customer = (Customer) customerResponse.getBody();
        customer.setAddress(address);

        String updateUrl = "http://localhost:8080/api/v1/customers/"+ customer.getId();

        ResponseEntity<Customer> customerResponseEntity;
        RequestEntity<Customer> entity = new RequestEntity<>(customer, HttpMethod.PUT, URI.create(updateUrl));
        try {
            customerResponseEntity = restTemplate.exchange(updateUrl, HttpMethod.PUT, entity, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid customer_number", HttpStatus.NOT_FOUND);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerResponseEntity.getBody(), HttpStatus.OK);

        //return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json",
            value = "/{customer_number}/personal-info"
    )
    public ResponseEntity<?> updatePersonalInfo(@PathVariable(value = "customer_number")String customer_number,
                                           @RequestParam(value = "education", required = true, defaultValue = "UNKNOWN") Education education,
                                           @RequestParam(value = "job", required = true, defaultValue = "UNKNOWN") Job job,
                                           @RequestParam(value = "monthly_income", required = true, defaultValue = "0") Float monthlyIncome,
                                           @RequestParam(value = "relationship_status", required = true, defaultValue = "SINGLE") RelationshipStatus relationshipStatus,
                                           @RequestParam(value = "number_of_children", required = true, defaultValue = "0") Integer numberOfChildren){
        ResponseEntity<?> customerResponse = getCustomer(String.valueOf(customer_number));
        if(customerResponse.getStatusCode()!=HttpStatus.OK || !(customerResponse.getBody() instanceof Customer)){
            return new ResponseEntity<>(customerResponse.getBody(), customerResponse.getStatusCode());
        }
        Customer customer = (Customer) customerResponse.getBody();
        customer.setEducation(education);
        customer.setJob(job);
        customer.setMonthlyIncome(monthlyIncome);
        customer.setRelationshipStatus(relationshipStatus);
        customer.setNumberOfChildren(numberOfChildren);

        String updateUrl = "http://localhost:8080/api/v1/customers/"+ customer.getId();

        ResponseEntity<Customer> customerResponseEntity;
        RequestEntity<Customer> entity = new RequestEntity<>(customer, HttpMethod.PUT, URI.create(updateUrl));
        try {
            customerResponseEntity = restTemplate.exchange(updateUrl, HttpMethod.PUT, entity, Customer.class);
        }catch (Exception e){
            return new ResponseEntity<>("Error "+ HttpStatus.NOT_FOUND.toString()+": Invalid customer_number", HttpStatus.NOT_FOUND);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerResponseEntity.getBody(), HttpStatus.OK);

        //return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
    }

}
