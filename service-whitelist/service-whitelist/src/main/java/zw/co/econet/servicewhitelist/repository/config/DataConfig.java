package zw.co.econet.servicewhitelist.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import zw.co.econet.servicewhitelist.domain.DomainMarkerInterface;
import zw.co.econet.servicewhitelist.repository.RepositoryMarkerInterface;



@Configuration
@EnableJpaRepositories(basePackageClasses = {RepositoryMarkerInterface.class})
@EntityScan(basePackageClasses = {DomainMarkerInterface.class})
public class DataConfig {

}
