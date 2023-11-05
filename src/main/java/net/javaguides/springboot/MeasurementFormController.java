package net.javaguides.springboot.mes;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MeasurementFormController {
    @Autowired
    private MeasurementDataService measurementDataService;
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("measurementData", new MeasurementData());
        return "mes"; // HTML form template name
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute MeasurementData measurementData, HttpSession session) {
        // Save form data to the database
        measurementDataService.saveMeasurementData(measurementData);

        // Send an email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("amruthag46@gmail.com"); // Replace with the recipient's email
        message.setSubject("Measurement Form Submission");
        message.setText("Bust Size: " + measurementData.getBustSize() + "\n"
                + "Waist Size: " + measurementData.getWaistSize() + "\n"
                + "Hips Size: " + measurementData.getHipsSize() + "\n"
                + "Inseam Length: " + measurementData.getInseamLength() + "\n"
                + "Name: " + measurementData.getName() + "\n"
                + "Phone Number: " + measurementData.getPhoneNumber() + "\n"
                + "Email: " + measurementData.getEmail() + "\n"
                + "Address: " + measurementData.getAddress() + "\n"
                + "Suggestion: " + measurementData.getSuggestion());

        javaMailSender.send(message);

        session.setAttribute("msg", "Measurement Form Submitted Successfully...");

        return "redirect:/thankyou"; // Redirect to a thank you page
    }
}
