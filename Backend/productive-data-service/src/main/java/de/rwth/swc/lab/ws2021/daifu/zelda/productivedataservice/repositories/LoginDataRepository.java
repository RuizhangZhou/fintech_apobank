package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.LoginData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "loginData", path = "loginData")
public interface LoginDataRepository extends MongoRepository<LoginData, Integer> {

    List<LoginData> findByCustomerNumber(@Param("customer_number") Integer customer_number);
}
