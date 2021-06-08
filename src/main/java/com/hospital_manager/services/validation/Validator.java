package com.hospital_manager.services.validation;

import com.hospital_manager.entities.UserInfo;

public class Validator {

    private static final String LOGIN_REGEX = "[A-Za-zА-Яа-я0-9_]+";

    private static final String PASSWORD_REGEX = "^(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-ZА-Я])(?=.*[a-zа-я]).*$";

    private static final String ID_REGEX = "[0-9]+";


    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isLoginValid(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public static boolean isRegistrationInfoValid(UserInfo reg) {

        return isLoginValid(reg.getLogin()) && isPasswordValid(reg.getPassword());
    }

    public static boolean isIdValid(long id) {
        return String.valueOf(id).matches(ID_REGEX);
    }

    public static boolean isTitleValid(String title) {
        return title != null;
    }
}
