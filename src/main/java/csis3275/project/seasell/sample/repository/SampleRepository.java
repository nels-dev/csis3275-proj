package csis3275.project.seasell.sample.repository;

import csis3275.project.seasell.sample.model.Sample;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, UUID> {

}
