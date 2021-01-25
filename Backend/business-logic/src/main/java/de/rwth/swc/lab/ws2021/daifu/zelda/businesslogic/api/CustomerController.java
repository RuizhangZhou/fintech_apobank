package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.api;


import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.*;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.RequestBody.RegisterCustomer;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.RequestBody.UpdatePersonalInfo;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.accounts.Account;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.AdvertismentStatus;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Education;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.Job;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.RelationshipStatus;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.loans.Loan;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData.LoginData;
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
import java.util.*;

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
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>("Error: " + customerResponseEntity.getStatusCode().toString(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createCustomerFE(customerResponseEntity.getBody()), HttpStatus.OK);
    }


    private CustomerFE createCustomerFE(Customer c){
        CustomerFE cFE = new CustomerFE();
        cFE.setId(c.getId());
        cFE.setAddress(c.getAddress());
        cFE.setCustomerNumber(c.getCustomerNumber());
        cFE.setAccounts(sortedAccountsList(c.getAccounts()));
        cFE.setInvestments(sortedInvestmentsList(c.getInvestments()));
        cFE.setLoans(sortedLoansList(c.getLoans()));
        cFE.setFirstName(c.getFirstName());
        cFE.setLastName(c.getLastName());
        cFE.setEducation(c.getEducation());
        cFE.setBirthday(c.getBirthday());
        cFE.setJob(c.getJob());
        cFE.setMonthlyIncome(c.getMonthlyIncome());
        cFE.setRelationshipStatus(c.getRelationshipStatus());
        cFE.setNumberOfChildren(c.getNumberOfChildren());
        cFE.setCustomerAdvertisements(c.getCustomerAdvertisements());
        return cFE;
    }

    private ArrayList<Account> sortedAccountsList(Set<Account> accounts){
        ArrayList aList = new ArrayList<Account>();
        for(Account a: accounts){
            aList.add(a);
        }
        Collections.sort(aList, new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                return a1.getAccountNumber() - a2.getAccountNumber();
            }
        });
        return aList;
    }

    private ArrayList<Investment> sortedInvestmentsList(Set<Investment> investments){
        ArrayList aList = new ArrayList<Investment>();
        for(Investment a: investments){
            aList.add(a);
        }
        Collections.sort(aList, new Comparator<Investment>() {
            @Override
            public int compare(Investment a1, Investment a2) {
                return a1.getId() - a2.getId();
            }
        });
        return aList;
    }

    private ArrayList<Loan> sortedLoansList(Set<Loan> loans){
        ArrayList aList = new ArrayList<Loan>();
        for(Loan a: loans){
            aList.add(a);
        }
        Collections.sort(aList, new Comparator<Loan>() {
            @Override
            public int compare(Loan a1, Loan a2) {
                return a1.getId() - a2.getId();
            }
        });
        return aList;
    }



    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json",
            value = "login"
    )
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam(value = "customer_number") Integer customer_number, @RequestParam(value = "password") String password ) {
        // check if customer number is valid
        try{
            ResponseEntity test = restTemplate.getForEntity("http://localhost:8080/api/v1/customers/"+ customer_number + "?getBy=customer_number", Customer.class);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error: No customer found with customer number "+customer_number, HttpStatus.NOT_FOUND);
        }
        // get loginData
        String url = "http://localhost:8082/productive-data-service/v1/loginData/get?customer_number="+customer_number;
        ResponseEntity<?> responseEntity = new ResponseEntity<>("Unerkannter Fehler",HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            responseEntity = restTemplate.getForEntity(url, LoginData.class);
        }catch (Exception e){
            // post loginData
            String postUrl = "http://localhost:8082/productive-data-service/v1/loginData/create";
            try {
                responseEntity = restTemplate.postForEntity(postUrl, new LoginData(customer_number, password), LoginData.class);
            }catch (Exception e2){
                return new ResponseEntity<>(e.toString()+"\n"+e2.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        if(responseEntity.getStatusCode()!=HttpStatus.OK){
            return responseEntity;
        }
        LoginData loginData = (LoginData) responseEntity.getBody();
        if (password.equals(loginData.getPassword())){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json",
            value = "register"
    )
    @ResponseBody
    /*public ResponseEntity<?> registerCustomer(@RequestBody Customer customer){*/
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterCustomer registerCustomer){

        // reconstruct variables from Request Body

        String firstName = registerCustomer.getFirstName();
        String lastName = registerCustomer.getLastName();
        String birthday = registerCustomer.getBirthday();
        String street = registerCustomer.getStreet();
        String houseNumber = registerCustomer.getHouseNumber();
        Integer zipCode = registerCustomer.getZipCode();
        String city = registerCustomer.getCity();


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

        //savedCustomer.setCustomerAdvertisements(new HashSet<>(createCAsForCustomer(savedCustomer))); NOT WORKING AND NO ERROR HANDLING!

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
            //newCustAdv.setCustomer(customer);
            newCustAdv.setStatus(AdvertismentStatus.UNKNOWN);
            newCustAdv.setNumberOfTimesDisplayed(0);
            //newCustAdv.setAdvertisementCampaign(advCamp.get(i));

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



        /*
                #   Calling this method throws an internal server error in batch process.
                #   So i just call post into dataBase below

        String urlString = "http://localhost:8083/batch-process/v1/updateCustomerProduktiveKennzahlen?customerId=" + customerId;

        ResponseEntity<?> customerResponseEntity = null;
        try {
            customerResponseEntity = restTemplate.getForEntity(urlString,Void.class);
        }catch (Exception e){
            //return new ResponseEntity<>("Error: customer not created3", HttpStatus.BAD_REQUEST);
            System.out.println("Error1: Productive data for user couldn't be geerated: "+e.toString());
            return false;
        }

        if(customerResponseEntity!=null && !customerResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            System.out.println("Error2: Productive data for user couldn't be geerated");
            return false;
        }

        return true;
        */

        try {
            return restTemplate.getForEntity("http://localhost:8083/batch-process/v1/updateProduktiveKennzahlen",Boolean.class).getBody();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
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
                                                @RequestBody UpdatePersonalInfo updatePersonalInfo){
        // reconstruct variables from Request Body
        Education education = updatePersonalInfo.getEducation();
        Job job = updatePersonalInfo.getJob();
        Float monthlyIncome = updatePersonalInfo.getMonthlyIncome();
        RelationshipStatus relationshipStatus = updatePersonalInfo.getRelationshipStatus();
        Integer numberOfChildren = updatePersonalInfo.getNumberOfChildren();

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
