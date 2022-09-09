package com.phalaenopsis.phalaenopsis.web;

import com.lowagie.text.DocumentException;
import com.phalaenopsis.phalaenopsis.domain.Certificate;
import com.phalaenopsis.phalaenopsis.domain.Topic;
import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.service.CertificateService;
import com.phalaenopsis.phalaenopsis.service.TopicService;
import com.phalaenopsis.phalaenopsis.service.UserService;
import com.phalaenopsis.phalaenopsis.service.pdf.PDFExporter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    private final UserService userService;
    private final TopicService topicService;
    private final CertificateService certificateService;

    public MainController(UserService userService, TopicService topicService, CertificateService certificateService) {
        this.userService = userService;
        this.topicService = topicService;
        this.certificateService = certificateService;
    }

    @GetMapping({"/", "/index"})
    public String getHomePage(Model model) {
        model.addAttribute("a1", true);
        model.addAttribute("hasCertificate", this.userService.getAuthenticatedUser() != null && this.userService.getAuthenticatedUser().certificate != null);
        return "index";
    }


    @PostMapping("/register")
    public String registerUser(String username, String password, String repeatPassword, String name, String surname) {
        try {
            userService.create(username, password, repeatPassword, "ROLE_USER", name, surname);
        } catch (RuntimeException exception) {
            return "redirect:/register?error=true";
        }
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/updateprofile")
    public String updateProfile(/*@RequestParam String username,*/
            @RequestParam String password,
            @RequestParam String repeatPassword,
            @RequestParam String name,
            @RequestParam String surname) {
        try {
            userService.updateProfile(/*String username, */ password, repeatPassword, "ROLE_USER", name, surname);
        } catch (RuntimeException exception) {
            return "redirect:/profile?error=true";
        }
        return "redirect:/profile?success=true";
    }


    @GetMapping("/login")
    public String login(String error) {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/certificate")
    public String certificate(Model model, Authentication authentication) {
        User user = this.userService.getAuthenticatedUser();
        model.addAttribute("name", user.certificate.getName());
        model.addAttribute("surname", user.certificate.getSurname());
        model.addAttribute("date", user.certificate.getDateIssued());
        return "certificate";
    }

    @GetMapping("/certification")
    public String certification() {
        return "certification";
    }


    @GetMapping("/flowers")
    public String flowers(Model model) {
        model.addAttribute("a5", true);
        return "flowers";
    }


    @GetMapping("/leaves")
    public String leaves(Model model) {
        model.addAttribute("a3", true);
        return "leaves";
    }

    @GetMapping("/roots")
    public String roots(Model model) {
        model.addAttribute("a2", true);
        return "roots";
    }


    @GetMapping("/roots_air")
    public String roots_air(Model model) {
        model.addAttribute("a2", true);
        return "roots_air";
    }

    @GetMapping("/roots_black")
    public String roots_black(Model model) {
        model.addAttribute("a2", true);
        return "roots_black";
    }


    @GetMapping("/roots_brown")
    public String roots_brown(Model model) {
        model.addAttribute("a2", true);
        return "roots_brown";
    }

    @GetMapping("/roots_green")
    public String roots_green(Model model) {
        model.addAttribute("a2", true);
        return "roots_green";
    }


    @GetMapping("/roots_white")
    public String roots_white(Model model) {
        model.addAttribute("a2", true);
        return "roots_white";
    }

    @GetMapping("/roots_yellow")
    public String roots_yellow(Model model) {
        model.addAttribute("a2", true);
        return "roots_yellow";
    }


    @GetMapping("/stem")
    public String stem(Model model) {
        model.addAttribute("a4", true);
        return "stem";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchString, Model model) {
        List<Topic> topics = topicService.getTopicsWithKeyword(searchString);
        model.addAttribute("topics", topics);
        return "search";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/certification")
    public String test_certification(@RequestParam String question1,
                                     @RequestParam String question2,
                                     @RequestParam String question3,
                                     @RequestParam String question4,
                                     @RequestParam String question5,
                                     @RequestParam String question6
    ) {

        if (certificateService.checkAnswers(question1, question2, question3, question4, question5, question6)) {
            return "certification_passed";
        }
        return "redirect:/certification?error=true";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        User user = this.userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/download_certificate")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        User user = this.userService.getAuthenticatedUser();
        if(user.certificate == null) return;

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=certificate_phalaenopsis_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        PDFExporter exporter = new PDFExporter(user);
        exporter.export(response);


    }
}
