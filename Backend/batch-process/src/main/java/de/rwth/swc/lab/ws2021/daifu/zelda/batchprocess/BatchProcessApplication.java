package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.api.transformInputController;

import java.util.Set;

@SpringBootApplication
public class BatchProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessApplication.class, args);
	}

}
