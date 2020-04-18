package io.naimi.covid19.DAO;

import io.naimi.covid19.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsStatusByLastUpdated(String Last_Updated);
}
