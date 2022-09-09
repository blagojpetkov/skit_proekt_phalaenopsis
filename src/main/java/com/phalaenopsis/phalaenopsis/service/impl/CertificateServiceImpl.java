package com.phalaenopsis.phalaenopsis.service.impl;

import com.phalaenopsis.phalaenopsis.domain.Certificate;
import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.CertificateRepository;
import com.phalaenopsis.phalaenopsis.service.CertificateService;
import com.phalaenopsis.phalaenopsis.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final UserService userService;

    public CertificateServiceImpl(CertificateRepository certificateRepository, UserService userService) {
        this.certificateRepository = certificateRepository;
        this.userService = userService;
    }

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public boolean checkAnswers(String question1, String question2, String question3, String question4, String question5, String question6) {
        if(question1.equals("2") || question2.equals("1") || question3.equals("2") ||
                question4.equals("1") || question5.equals("1") || question6.equals("2")){
            User user = this.userService.getAuthenticatedUser();
            Certificate certificate = new Certificate(user.getUsername(), LocalDate.now(), user.getName(), user.getSurname());
            user.certificate = this.saveCertificate(certificate);
            userService.save(user);
            return true;
        }
        return false;
    }
}
