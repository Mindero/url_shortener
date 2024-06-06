package org.example.repo.url;

import org.example.repo.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {
    List<String> getLongurlByShorturl(String shorturl);
    boolean existsByShorturl(String shorturl);

    @Query(nativeQuery = true,
            value = "SELECT short_url FROM urls WHERE created_at < :createdDate")
    List<String> findAllByUpdatedDateBefore(Instant createdDate);
}
