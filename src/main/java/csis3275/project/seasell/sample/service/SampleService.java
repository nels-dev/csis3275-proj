package csis3275.project.seasell.sample.service;

import csis3275.project.seasell.sample.model.Sample;
import csis3275.project.seasell.sample.repository.SampleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  @Autowired
  SampleRepository sampleRepository;

  public List<Sample> getAll(){
    return sampleRepository.findAll();
  }

  public void addOne(){
    sampleRepository.save(new Sample().setName("new sample").setComment("hello"));
  }
}
