package org.homely;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck; // Import VerificationCheck

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VerificationController {

    public static final String ACCOUNT_SID = "AC81da557652ce9fdffc259a77d20e4af7";
    public static final String AUTH_TOKEN = "cf985887a5a61d81ffb1d86a4ba88b8b";

    // Initialize Twilio with your credentials
    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @GetMapping("/createService")
    public String createService() {
        Service service = Service.creator("My First Verify Service").create();
        return "Service SID: " + service.getSid();
    }

    @PostMapping("/sendVerification")
    public String sendVerification(@RequestBody VerificationCheckRequest verificationRequest) {
        Verification verification = Verification.creator(
                        "VA0f6b736a8204c057ae924af91a86535e",
                        verificationRequest.getPhoneNumber(),
                        "sms")
                .create();
        return "Verification status: " + verification.getStatus();
    }

    @PostMapping("/checkVerification")
    public ResponseEntity<String> checkVerification(@RequestBody VerificationCheckRequest request) {
        final String verificationSid = "VA0f6b736a8204c057ae924af91a86535e"; // Replace with your verification SID

        VerificationCheck verificationCheck = VerificationCheck.creator(verificationSid, request.getCode())
                .setTo(request.getPhoneNumber())
                .create();

        String message = "Verification check status: " + verificationCheck.getStatus();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
