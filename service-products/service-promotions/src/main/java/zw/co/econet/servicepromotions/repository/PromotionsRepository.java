package zw.co.econet.servicepromotions.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.econet.servicepromotions.domain.Promotion;

public interface PromotionsRepository extends JpaRepository<Promotion, Long>, JpaSpecificationExecutor<Promotion> {
    Promotion save(Promotion promotion);
    Optional<Promotion> findByName(String name);

    List<Promotion> findAll();
}
