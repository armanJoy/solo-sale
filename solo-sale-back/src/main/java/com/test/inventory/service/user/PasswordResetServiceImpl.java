package com.test.inventory.service.user;

import com.test.inventory.common.Utils.Helper;
import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.config.email.EmailService;
import com.test.inventory.config.email.EmailStatus;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.model.user.OTPEntries;
import com.test.inventory.model.user.User;
import com.test.inventory.repository.user.OTPEntriesRepository;
import com.test.inventory.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final OTPEntriesRepository otpEntriesRepository;
    private final AuthenticationManager authenticationManager;
    private static final Short OTP_LENGTH = 6;

    @Override
    @Transactional
    public Boolean sendOtpForForgotPassword(String email) {
        User user = getUserFromEmail(email);
        if (Objects.isNull(user)) {
            throw CommonServerException.notFound("User not found");
        }
        String otp = generateOtp();

        String emailSubject = "Solo Sale Forget password OTP";
        String content = "<p>Hello " + user.getFullName() + ",</p>"
                + "Your OTP to reset forgotten password is </p>"
                + "<p><b>" + otp + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in " + ApplicationConstant.FORGET_PASS_OTP_EXPIRE_MINUTE
                + " minutes. Don't share your OTP with others.</p>"
                + "<br> <br>"
                + "<p>Regards,</p>"
                + "<p>Solo Sale Authority</p>";

        // removing any existing OTP for user to avoid security ambiguity
        otpEntriesRepository.deleteByUserEmailIgnoreCase(user.getEmail());
        // saving new OTP
        saveGeneratedOtp(user.getEmail(), otp);
        // sending OTP to user through mail
        EmailStatus emailStatus = emailService.sendHtmlMail(email, emailSubject, content);
        return Objects.nonNull(emailStatus) && Objects.equals(emailStatus.getStatus(),
                ApplicationConstant.EMAIL_SUCCESS);
    }

    private void saveGeneratedOtp(String mail, String otp) {
        OTPEntries otpEntry = new OTPEntries();
        otpEntry.setUserEmail(mail);
        otpEntry.setIsValid(false);
        otpEntry.setOneTimePassword(otp);
        otpEntry.setOtpRequestedTime(Instant.now());
        otpEntriesRepository.save(otpEntry);
    }

    @Override
    public Boolean validateOTP(String otp, String email) {
        User user = getUserFromEmail(email);
        if (Objects.isNull(user)) {
            throw CommonServerException.notFound(ApplicationConstant.NOT_FOUND);
        }
        return isOTPValid(otp, user);
    }

    private Boolean isOTPValid(String otp, User user) {
        OTPEntries otpEntry = otpEntriesRepository.findByUserEmail(user.getEmail());
        if (Objects.nonNull(otpEntry) && !otpEntry.getIsValid()) {
            Instant currentTime = Instant.now();
            Instant otpRequestedTime = otpEntry.getOtpRequestedTime();
            Duration difference = Duration.between(otpRequestedTime, currentTime);
            if (difference.toMinutes() <= ApplicationConstant.FORGET_PASS_OTP_EXPIRE_MINUTE) {
                if (otp.equals(otpEntry.getOneTimePassword())) {
                    otpEntry.setIsValid(true);
                    otpEntriesRepository.save(otpEntry);
                    return true;
                } else {
                    return false;
                }
            } else {
                otpEntriesRepository.delete(otpEntry);
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean resetForgottenPassword(String email, String otp, String newPassword) {
        User user = getUserFromEmail(email);
        if (Objects.isNull(user)) {
            return false;
        } else {
            OTPEntries otpEntry = otpEntriesRepository.findByUserEmail(email);
            if (Objects.nonNull(otpEntry) && Objects.equals(otpEntry.getOneTimePassword(), otp) && otpEntry.getIsValid()) {
                String newEncodedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(newEncodedPassword);
                userRepository.save(user);
                otpEntriesRepository.delete(otpEntry);
                return true;

            }
        }
        return false;
    }

    @Override
    public Boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = Helper.getCurrentUser();
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (Objects.nonNull(user) && userOptional.isPresent() && user.getId().equals(userOptional.get().getId())) {
            user = userOptional.get();
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), oldPassword));
                if (Objects.equals(user.getEmail(), email) && Objects.nonNull(authentication) && authentication.isAuthenticated()) {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> resetUserPasswordByAdmin(Long userId) {

        User requestedUser = userRepository.findByIdAndIsActiveTrue(Helper.getCurrentUser().getId()).orElse(null);
        User targetedUser = userRepository.findByIdAndIsActiveTrue(userId).orElse(null);

        if (Objects.nonNull(requestedUser) && requestedUser.getRole().getName().equalsIgnoreCase(ApplicationConstant.ADMIN_USER_NAME) && Objects.nonNull(targetedUser) && (Objects.isNull(targetedUser.getRole()) || (Objects.nonNull(targetedUser.getRole()) && Objects.nonNull(targetedUser.getRole().getName()) && !ApplicationConstant.ADMIN_USER_NAME.toLowerCase().contains(targetedUser.getRole().getName().toLowerCase())))) {
            try {
                String newPass = Helper.generateAlphaNumericCode(6);
                targetedUser.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(targetedUser);

                String emailSubject = "Solo Sale Password Reset";
                String content = "<p>Hello " + targetedUser.getFullName() + ",</p>"
                        + "Your password has been reset by an Admin. Your new password is </p>"
                        + "<p><b>" + newPass + "</b></p>"
                        + "<br>"
                        + "<p>Please change this password after you login to your account."
                        + "<br> <br>"
                        + "<p>Regards,</p>"
                        + "<p>Solo Sale Authority</p>";
                EmailStatus emailStatus = emailService.sendHtmlMail(targetedUser.getEmail(), emailSubject, content);
                boolean mailStatus = Objects.nonNull(emailStatus) && Objects.equals(emailStatus.getStatus(),
                        ApplicationConstant.EMAIL_SUCCESS);
                if (mailStatus) {
                    return new ResponseEntity<>(new MessageResponse("Password Reset Successful."),
                            HttpStatus.OK);
                } else {
                    throw new RuntimeException("Mail sent unsuccessful.");
                }
            } catch (Exception e) {
                return new ResponseEntity<>(new MessageResponse("Couldn't reset password at this moment. " +
                        "Try later."),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            if (Objects.isNull(requestedUser) || Objects.isNull(targetedUser)) {
                return new ResponseEntity<>(new MessageResponse("Invalid user."), HttpStatus.BAD_REQUEST);
            } else if (Objects.nonNull(requestedUser) && !requestedUser.getRole().getName().equalsIgnoreCase(ApplicationConstant.ADMIN_USER_NAME)) {
                return new ResponseEntity<>(new MessageResponse("You are not authorized to reset password."),
                        HttpStatus.BAD_REQUEST);
            } else if (Objects.nonNull(targetedUser) && Objects.nonNull(targetedUser.getRole()) && Objects.nonNull(targetedUser.getRole().getName()) && ApplicationConstant.ADMIN_USER_NAME.toLowerCase().contains(targetedUser.getRole().getName().toLowerCase())) {
                return new ResponseEntity<>(new MessageResponse("You cannot reset other Admin user's " +
                        "password."),
                        HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new MessageResponse("Couldn't reset password. Try later."),
                        HttpStatus.BAD_REQUEST);
            }
        }
    }

    private User getUserFromEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    private String generateOtp() {
        Random random = new Random();
        String characters = ApplicationConstant.OTP_CHAR_SET;
        return random.ints(OTP_LENGTH, 0, characters.length())
                .mapToObj(characters::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }


}