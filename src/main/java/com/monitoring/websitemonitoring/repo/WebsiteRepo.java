package com.monitoring.websitemonitoring.repo;

import com.monitoring.websitemonitoring.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WebsiteRepo extends JpaRepository<Website, Long> {
}
