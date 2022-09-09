package com.phalaenopsis.phalaenopsis.repository;

import com.phalaenopsis.phalaenopsis.domain.Certificate;
import com.phalaenopsis.phalaenopsis.service.CertificateService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
