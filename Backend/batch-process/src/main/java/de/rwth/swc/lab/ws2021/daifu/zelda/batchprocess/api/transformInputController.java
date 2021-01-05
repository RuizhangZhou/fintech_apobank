package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.api;

import java.time.LocalDate;
import java.time.Period;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.*;
import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
public class transformInputController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/pelikan")
    private Set<CustomerAdvertisementData> showInputData(){
        return getInputData();
    }


    private Set<CustomerAdvertisementData> getInputData(){
        List<CustomerAdvertisementData> input = new ArrayList<>();
        System.out.println("Abfrage Test");
        Set<Customer> customers = getCustomerFromData();
        Set<AdvertisementCampaign> campaigns = getCampaignsFromData();
        System.out.println("Abfrage Funktioniert");
        for(Customer costumer : customers) {
            for(CustomerAdvertisement advert : costumer.getCustomerAdvertisements()){
                for(AdvertisementCampaign campaign : campaigns) {
                    if(campaign.getId() == advert.getId().getAdvertisementCampaignId() && campaign.getEndDate().compareTo(LocalDate.now()) >= 0) {
                        System.out.println("InputData(costumerId = "+ costumer.getId() + ", campaignId = " + campaign.getId() +")");
                        input.add(new CustomerAdvertisementData(new CustomerAdvertisementKey(costumer.getId(),campaign.getId()),transformIntoInput(costumer, campaign, campaigns)));
                    }
                }
            }
        }
        return new HashSet<>(input);
    }

    @GetMapping("/pinguin")
    public boolean postIntoDatabase(){
        Set<CustomerAdvertisementData> input = getInputData();
        for(CustomerAdvertisementData data : input){

        }
        return true;
    }


    private Set<Customer> getCustomerFromData(){
        String urlString = "http://localhost:8080/api/v1/customers";
        ResponseEntity<CustomerPage> customerResponseEntity = null;
        try {
            customerResponseEntity = restTemplate.getForEntity(urlString, CustomerPage.class);
            return new HashSet<>(customerResponseEntity.getBody().getContent());
        }catch (Exception e){
                System.out.println("Problem!!!" + e.toString());
        }
        return null;
    }

    private Set<AdvertisementCampaign> getCampaignsFromData(){
        String urlString = "http://localhost:8080/api/v1/advertisementCampaigns";
        ResponseEntity<CampaignPage> campaignResponseEntity = null;
        try {
            campaignResponseEntity = restTemplate.getForEntity(urlString, CampaignPage.class);
            return new HashSet<>(campaignResponseEntity.getBody().getContent());
        }catch (Exception e){
            System.out.println("Problem!!!" + e.toString());
        }
        return null;
    }

    public InputData transformIntoInput(Customer c, AdvertisementCampaign aCampaign, Set<AdvertisementCampaign> campaigns){

        InputData input = new InputData();

        input.setAge(getAge(c.getBirthday())+0f);

        //Hier muss vielleicht noch self-employed durch selfemployed getauscht werden.
        //Dann müsste man auch einen zweiten enum erstellen.
        input.setJob(c.getJob());
        input.setMarital(transformRelationshipToMaritalStatus(c.getRelationshipStatus()));
        input.setEducation(c.getEducation());
        input.setDefault_(hasLoanDefault(c.getLoans()));
        input.setBalance(calculateBalanceSum(c.getAccounts()));
        input.setHousing(hasConstructionLoan(c.getLoans()));
        input.setLoan(hasPrivateLoan(c.getLoans()));

        CustomerAdvertisement aAdvert = getCustomerAdvertisementForCampaign(aCampaign,c);
        input.setDay(getDayLastContact(aAdvert));
        input.setMonth(getMonthLastContact(aAdvert));
        input.setCampaign(getContacts(aAdvert)+0f);

        CustomerAdvertisement lAdvert = getCustomerAdvertisementForLastCampaign(campaigns,c);
        input.setPdays(getPdays(lAdvert)+0f);
        input.setPrevious(getPrevious(lAdvert));
        input.setPoutcome(getPoutcome(lAdvert));

        return input;
    }

    private Integer getAge(LocalDate birthDate){
        return (Period.between(birthDate, LocalDate.now()).getYears());
    }

    private MaritalStatus transformRelationshipToMaritalStatus(RelationshipStatus rs){
        if(rs == RelationshipStatus.SINGLE || rs == RelationshipStatus.REGISTERED_PARTNERSHIP){
            return MaritalStatus.SINGLE;
        }
        else if (rs == RelationshipStatus.MARRIED) {
            return  MaritalStatus.MARRIED;
        }
        else if(rs == RelationshipStatus.DIVORCED || rs == RelationshipStatus.WIDOWED) {
            return MaritalStatus.DIVORCED;
        }
        return null;
    }

    private BooleanString hasLoanDefault(Set<Loan> loans){
        for (Loan loan : loans) {
            LoanStatus s = loan.getStatus();
            if(s == LoanStatus.DEFAULT || s == LoanStatus.DEFICIT || s == LoanStatus.IRRECOVERABLE_DEBT){
                return BooleanString.YES;
            }
        }
        return BooleanString.NO;
    }

    private BooleanString hasConstructionLoan(Set<Loan> loans){
        for (Loan loan : loans) {
            if(loan.getType() == LoanType.constructionLoan){
                return BooleanString.YES;
            }
        }
        return BooleanString.NO;
    }

    private BooleanString hasPrivateLoan(Set<Loan> loans){
        for (Loan loan : loans) {
            if(loan.getType() == LoanType.privateLoan){
                return BooleanString.YES;
            }
        }
        return BooleanString.NO;
    }

    private Float calculateBalanceSum(Set<Account> accounts){
        float balanceSum = 0f;
        for(Account account : accounts) {
            balanceSum += account.getBalance();
        }
        return balanceSum;
    }

    private CustomerAdvertisement getCustomerAdvertisementForCampaign(AdvertisementCampaign campaign, Customer c){
        for(CustomerAdvertisement ca : c.getCustomerAdvertisements()){
            if(ca.getId().getAdvertisementCampaignId() == campaign.getId()) {
                return ca;
            }
        }
        return null;
    }

    private Integer getDayLastContact(CustomerAdvertisement ca){
        return ca.getLastDisplayDate().getDayOfMonth();
    }

    private Month getMonthLastContact(CustomerAdvertisement ca){
        return Month.valueOf(ca.getLastDisplayDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase());
    }

    private Integer getContacts(CustomerAdvertisement ca){
        return ca.getNumberOfTimesDisplayed();
    }

    private CustomerAdvertisement getCustomerAdvertisementForLastCampaign(Set<AdvertisementCampaign> campaigns, Customer c){
        int minDaysOver = Integer.MAX_VALUE;
        CustomerAdvertisement advert = null;
        for(CustomerAdvertisement ca : c.getCustomerAdvertisements()){
            for(AdvertisementCampaign campaign : campaigns) {
                if(ca.getId().getAdvertisementCampaignId() == campaign.getId()) {
                    if (campaign.getEndDate().compareTo(LocalDate.now()) < 0) {
                        int daysOver = (int) ChronoUnit.DAYS.between(campaign.getEndDate(), LocalDate.now());
                        if (daysOver < minDaysOver) {
                            minDaysOver = daysOver;
                            advert = ca;
                        }
                    }
                }
            }
        }
        return advert;
    }

    private Integer getPdays(CustomerAdvertisement advert){
        return (int)ChronoUnit.DAYS.between(advert.getLastDisplayDate(),LocalDate.now());
    }

    private Integer getPrevious(CustomerAdvertisement advert){
        return advert.getNumberOfTimesDisplayed();
    }

    private Outcome getPoutcome(CustomerAdvertisement advert){
        CustomerAdvertisementStatus status = advert.getStatus();
        if(status == CustomerAdvertisementStatus.SUCCESS) {
            return Outcome.SUCCESS;
        }
        else if(status == CustomerAdvertisementStatus.FAILURE){
            return Outcome.FAILURE;
        }
        else if(status == CustomerAdvertisementStatus.UNKNOWN){
            return Outcome.UNKNOWN;
        }
        else{
            return Outcome.OTHER;
        }
    }

}
