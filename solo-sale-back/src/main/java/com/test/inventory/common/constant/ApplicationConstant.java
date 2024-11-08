package com.test.inventory.common.constant;

public class ApplicationConstant {
    public static final String AUTHORIZATION_TYPE_BEARER = "Bearer";
    public static final int DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT = "id";
    /**
     * Minimum character is 8, only alphabet & number. It also allows symbol. and within character & number & symbol, at least use 2 item mandatory.
     */
    public static final String VALID_PASSWORD_REGEX = "^((((?=.*?[A-Z])|(?=.*?[a-z]))(?=.*?[0-9]))" +
            "|((?=.*?[0-9])(?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]))|" +
            "((?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])((?=.*?[A-Z])|(?=.*?[a-z])))).{8,256}$";
    public static final String VALID_CONTACT_NO_REGEX = "^01\\d{9}$";
    public static final String DATE_TYPE_VALIDATION_REGEX = "[-/.]";
    public static final String DEFAULT_PAGE_NUMBER = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DELETED_SUCCESSFULLY = "Deleted Successfully";
    public static final int FIRST_INDEX = 0;
    public static final String EMPTY_STRING = "";

    public static final String NULL = null;
    public static final String ID = "id";
    public static final String MESSAGE_SEPARATOR = "###";
    public static final String DATE_TYPE = "dateType";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String DASH = "-";
    public static final String HYPHEN = "_";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String REVISED = "R";
    public static final char SLASH_CHAR = '/';
    public static final String FOUR_DIGIT_FORMAT = "%04d";
    public static final Long ONE = 1L;
    public static final String NOT_FOUND = "Not found";
    public static final String USER_NAME_EXIST_MSG = "Login Id already exist. ";
    public static final String USER_NAME_EXIST_MSG_ARCHIVE = "Login Id already exist in archive.";
    public static final String USER_EMAIL_ALREADY_EXIST = "User email already exist.";
    public static final String USER_EMAIL_ALREADY_EXIST_ARCHIVE = "User email already exist in archive.";
    public static final String ADMIN_USER_NAME = "admin";
    public static final String PHONE_NUMBER_PATTERN = "^\\+?(?:[0-9] ?){6,14}[0-9]$";
    public static final String DESIGNATION_NAME_ALREADY_EXIST = "This Designation name is already exist";
    public static final String DESIGNATION_NAME_ALREADY_EXIST_IN_ARCHIVE = "This Designation name is already exist in archive";
    public static final String ROLE_NAME_DUPLICATE_MSG = "Role name already exist.";
    public static final Boolean ACTIVE_TRUE = true;
    public static final String OTP_CHAR_SET = "0123456789";
    public static final String EMAIL_SUCCESS = "email sent successful";
    public static final String EMAIL_FAIL = "email sent failed";
    public static final long FORGET_PASS_OTP_EXPIRE_MINUTE = 15;
    public static class NUMBERS {
        public static final Integer ZERO = 0;
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;
        public static final Integer THREE = 3;
        public static final Integer FOUR = 4;
        public static final Integer FIVE = 5;
        public static final Integer SIX = 6;
        public static final Integer SEVEN = 7;
        public static final Integer EIGHT = 8;
        public static final Integer NINE = 9;
    }
}