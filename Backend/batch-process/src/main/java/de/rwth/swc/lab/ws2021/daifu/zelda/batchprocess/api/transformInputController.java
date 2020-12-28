package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.api;

import java.time.LocalDate;
import java.time.Period;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.*;
import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.*;

import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Set;


public class transformInputController {

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
        return Month.valueOf(ca.getLastDisplayDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toLowerCase());
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
