package zw.co.econet.servicepromotions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.servicepromotions.domain.Promotions;

public interface PromotionsRepository extends JpaRepository<Promotions, Long>, JpaSpecificationExecutor<Promotions> {

}
