package csis3275.project.seasell.configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

    @Bean
    @ConditionalOnProperty(name = "storage.type", havingValue = "cloud")
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }
}
