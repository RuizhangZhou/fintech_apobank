package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.api.TransformInputController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NightlyTrigger {

    @Autowired
    private TransformInputController transformInputController;


    @Scheduled(fixedRate = 120000, initialDelay = 20000)
    public void nightlyUpdateDatabaseTest(){
        System.out.println("Test PostIntoDatabase");
        transformInputController.postIntoDatabase();
    }


    @Scheduled(cron = "0 2 * * * ?")
    public void nightlyUpdateDatabase(){
        System.out.println("Nächtliches PostIntoDatabase");
        transformInputController.postIntoDatabase();
    }

}
