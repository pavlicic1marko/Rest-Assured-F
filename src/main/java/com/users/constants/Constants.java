package com.users.constants;

public class Constants {

    public static class UrlConstants{
        public static final String REGISTER_URL = "/users/register/";
        public static final String USER_LOGIN = "/users/login";
        public static final String USER = "/users/";
        public static final String USER_PROFILE = "/users/profile/";
        public static final String USER_PROFILE_UPDATE = "/users/profile/update/";
        public static final String USER_DELETE = "/users/delete/";
        public static final String USER_UPDATE="/users/update/";
    }


    public static class ErrorMessages{
        public static final String ERROR_403_MESSAGE = "{\"detail\""+":"+"\"You do not have permission to perform this action.\"}".toString();
    }

}
