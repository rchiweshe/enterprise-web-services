package zw.co.econet.servicewhitelist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.econet.servicewhitelist.domain.Whitelist;

public interface WhitelistRepository extends JpaRepository<Whitelist, Long> {

    public Optional<Whitelist> findByMsisdn(String msisd);

    public List<Whitelist> findAll();

    @Query(value = "SELECT MAX(id)  FROM whitelist", nativeQuery = true)
    Long lastInsertedId();

}
