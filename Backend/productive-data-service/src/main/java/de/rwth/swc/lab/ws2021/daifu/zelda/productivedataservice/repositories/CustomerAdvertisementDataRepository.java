package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.CustomerAdvertisementData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.stream.Collectors;

@RepositoryRestResource(collectionResourceRel = "customerAdvertisementData", path = "customerAdvertisementData")
public interface CustomerAdvertisementDataRepository extends MongoRepository<CustomerAdvertisementData, Integer> {

    List<CustomerAdvertisementData> findByCustomerNumber(@Param("customerNumber") Integer customerNumber);
    List<CustomerAdvertisementData> findByProductId(@Param("productId") Integer productId);
}
