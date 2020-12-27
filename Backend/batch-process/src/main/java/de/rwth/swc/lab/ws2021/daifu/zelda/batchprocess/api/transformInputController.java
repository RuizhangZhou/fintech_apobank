package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.api;

import java.time.LocalDate;
import java.time.Period;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.*;
import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.*;

import java.time.temporal.ChronoUnit;
import java.util.Set;


public class transformInputController {

    public InputData transformIntoInput(Customer c, Set<AdvertisementCampaign> campaigns){

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

        Integer contact_day = 0;
        String contact_month = "";
        Integer campaign = 0;

        input.setPdays(getPdays(campaigns)+0f);
        input.setPrevious(getPrevious(campaigns));
        input.setPoutcome(getPoutcome(campaigns));

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

    private AdvertisementCampaign getLastCampaign(Set<AdvertisementCampaign> campaigns){
        AdvertisementCampaign last = null;
        int minDaysOver = Integer.MAX_VALUE;
        for(AdvertisementCampaign campaign : campaigns){
            if(campaign.getEndDate().compareTo(LocalDate.now()) < 0){
                int daysOver = (int)ChronoUnit.DAYS.between(campaign.getEndDate(), LocalDate.now());
                if(daysOver<minDaysOver){
                    minDaysOver=daysOver;
                    last = campaign;
                }
            }
        }
        return last;
    }


    private Integer getPdays(Set<AdvertisementCampaign> campaigns){
        return (int)ChronoUnit.DAYS.between(getLastCampaign(campaigns).getEndDate(), LocalDate.now());
    }

    private Integer getPrevious(Set<AdvertisementCampaign> campaigns){
        return 0;
    }

    private Outcome getPoutcome(Set<AdvertisementCampaign> campaigns){
        return Outcome.SUCCESS;
    }

}
