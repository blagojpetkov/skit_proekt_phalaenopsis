package com.phalaenopsis.phalaenopsis.MutationTesting;

import com.phalaenopsis.phalaenopsis.domain.Certificate;
import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.CertificateRepository;
import com.phalaenopsis.phalaenopsis.service.CertificateService;
import com.phalaenopsis.phalaenopsis.service.UserService;
import com.phalaenopsis.phalaenopsis.service.impl.CertificateServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CertificateServiceImplMutationTest {

    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private UserService userService;
    private CertificateService certificateService;

    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);
        User user = new User();
        user.certificate = new Certificate();
        Certificate certificate = new Certificate("username", LocalDate.now(), "name", "surname");
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        certificateService = Mockito.spy(new CertificateServiceImpl(certificateRepository, userService));
    }


    @Test
    public void TestAllAnswersWrong(){
        Assert.assertFalse(certificateService.checkAnswers("3", "3", "3", "3", "3", "3"));
    }
    @Test
    public void TestAllAnswersCorrect(){
        Assert.assertTrue(certificateService.checkAnswers("2", "1", "2", "1", "1", "2"));
    }

    @Test
    public void TestSaveCertificate(){
        Certificate certificate = new Certificate("username", LocalDate.now(), "name", "surname");
        Certificate secondCertificate = certificateService.saveCertificate(certificate);
        Assert.assertEquals(certificate, secondCertificate);
    }
}
