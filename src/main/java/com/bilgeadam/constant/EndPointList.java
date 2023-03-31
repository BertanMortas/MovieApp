package com.bilgeadam.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@AllArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EndPointList {
    public static final String USER="/user";
    public static final String UPDATE_DTO="/update-dto";
    public static final String UPDATE_MAPPER="/update-mapper";
    public static final String REGISTER_MAPPER="/register-mapper";
    public static final String REGISTER_DTO="/register-dto";
    public static final String REGISTER="/register";
    public static final String LOGIN_MAPPER="/login-mapper";
    public static final String LOGIN_DTO="/login-dto";
    public static final String LOGIN="/login";
    public static final String CUSTOM_LOGIN="/custom-login";
    public static final String FIND_ALL="/find-all";
    public static final String FIND_BY_ID="/find-by-id";
    public static final String DELETE="/delete";
    public static final String ORDER_BY_USER="/order-by-user";
    public static final String FIND_BY_NAME="/find-by-name";
    public static final String FIND_BY_NAME_CONTAINING="/find-by-name-containing";
    public static final String EXISTS_NAME="/exits-name";
    public static final String FIND_BY_EMAIL="/find-by-email";
    public static final String FIND_PASSWORD_GREATERTHAN_JQSQL="/find-password-greaterthan-jpql";
    public static final String FIND_PASSWORD_GREATERTHAN_NATIVE="/find-password-greaterthan-native";
    public static final String FIND_EMAIL_ENDSWITH="/find-email-endswith";
    public static final String FIND_BY_ORDER_NAME_ASC="/find-by-order-name-asc";
    public static final String FIND_PASSWORD_LENGTH="/find-password-length";
    public static final String FIND_PASSWORD_LENGTH_NATIVE="/find-password-length-native";

}
