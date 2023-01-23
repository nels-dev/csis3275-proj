package csis3275.project.seasell.sample;

import csis3275.project.seasell.sample.model.Sample;
import csis3275.project.seasell.sample.service.SampleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samples")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping
    public List<Sample> getAll() {
        return sampleService.getAll();
    }

    @PostMapping
    public void addOne(){
        sampleService.addOne();
    }
}
