package com.test.inventory.common.Utils;


import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.config.authentication.service.CustomUserDetails;
import com.test.inventory.model.role.Role;
import com.test.inventory.model.user.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;


@Component
public class Helper {

    public static void throwCaseSpecificDuplicateValidationMessage(String fieldName, boolean isActive){
        throw new RuntimeException(isActive ? fieldName + " is already exist" : fieldName + " is already exist in archive");
    }

    public static User getCurrentUser() {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            user.setId(userDetails.getId());
            user.setFullName(userDetails.getFullName());
            user.setRole(new Role(userDetails.getRoleId(), userDetails.getRole()));
            return user;
        }
        return null;
    }

    public static String dateDifference(LocalDate inputDate) {
        Period difference = Period.between(inputDate, LocalDate.now());

        return difference.getYears() + "Y "
                + difference.getMonths() + "M " +
                +difference.getDays() + "D";
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String generateSixDigitOtp() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000;
        return Integer.toString(number);
    }

    public static String generateAlphaNumericCode(int targetStringLength) {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57) || (i >= 65 && i <= 90))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String encodeUrl(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrl(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static double formatDoubleValue(Double value) {
        double checkedValue = (value != null) ? value : 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(checkedValue));
    }

    public static String spaceRemoveAndLowerCase(String text) {
        return text.replace(ApplicationConstant.SPACE, ApplicationConstant.EMPTY_STRING).toLowerCase();
    }

    public static String encodeToString(String generatedId) {
        String encodeToString = Base64.getEncoder().encodeToString
                (Base64.getEncoder().encodeToString(generatedId.getBytes()).getBytes());
        return encodeToString;
    }
    public static String decodeToString(String generatedId) {
        String decodeToString = new String(Base64.getDecoder().decode(Base64.getDecoder().decode(generatedId)));
        return decodeToString;
    }

}