package zw.co.econet.servicepromotions.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import zw.co.econet.servicepromotions.domain.DomainMarkerInterface;
import zw.co.econet.servicepromotions.repository.RepositoryMarkerInterface;

@Configuration
@EnableJpaRepositories(basePackageClasses = {RepositoryMarkerInterface.class})
@EntityScan(basePackageClasses = {DomainMarkerInterface.class})
public class DataConfig {


}
