package com.test.inventory.common.routes;

import static com.test.inventory.common.routes.ApiConstants.PATH_VARIABLE_BY_ID;

public class Router {
    //  ROLE
    public static final String ROLE = ApiConstants.BASE_URL_V1 + "/role";
    public static final String ASSIGN_ROLE = "/assign";
    public static final String ASSIGNED_USERS = "/assigned-users";
    public static final String REVOKE_USER_ROLE = "/revoke" + PATH_VARIABLE_BY_ID;
    //  USER
    public static final String USER = "/user";
    public static final String USER_URL_V1 = ApiConstants.BASE_URL_V1 + USER;
    public static final String USER_SEARCH = USER_URL_V1 + "/search";
    public static final String FORGOTTEN_PASSWORD = USER_URL_V1 + "/req-forget-pass-otp";
    public static final String VALIDATE_OTP = USER_URL_V1 + "/verify-forget-pass-otp";
    public static final String RESET_FORGOTTEN_PASSWORD = USER_URL_V1 + "/set-forgotten-password";
    public static final String CHANGE_PASSWORD = USER_URL_V1 + "/change-password";
    public static final String RESET_PASSWORD = USER_URL_V1 + "/reset-password";
    //  EMPLOYEE
    public static final String EMPLOYEE = ApiConstants.BASE_URL_V1 + "/employee";
    public static final String EMPLOYEE_UPDATE = "/update/{id}";
    public static final String EMPLOYEE_LIST = "/all/list";
    //Designation
    public static final String DESIGNATION = ApiConstants.BASE_URL_V1 + "/designation";
    public static final String BRAND = ApiConstants.BASE_URL_V1 + "/brand";
    public static final String PRODUCT_CATEGORY = ApiConstants.BASE_URL_V1 + "/category";
    public static final String PRODUCT = ApiConstants.BASE_URL_V1 + "/product";
    public static final String PURCHASE = ApiConstants.BASE_URL_V1 + "/purchase";
    public static final String SALE = ApiConstants.BASE_URL_V1 + "/sale";
}