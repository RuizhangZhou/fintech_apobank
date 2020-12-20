package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.repositories;

import de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models.InputData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "inputData", path = "inputData")
public interface InputDataRepository extends MongoRepository<InputData, Integer> {

    InputData findByID(@Param("ID") Integer ID);

}
