package com.phalaenopsis.phalaenopsis.service;

import com.phalaenopsis.phalaenopsis.domain.Certificate;
import org.springframework.web.bind.annotation.RequestParam;

public interface CertificateService {
    Certificate saveCertificate(Certificate certificate);
    boolean checkAnswers(String question1,
                         String question2,
                         String question3,
                         String question4,
                         String question5,
                         String question6);
}
