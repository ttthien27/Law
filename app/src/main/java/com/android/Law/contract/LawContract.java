package com.android.Law.contract;

public class LawContract {

    static public class UserEntry {
        /**
         * Name of the root of database entry for User
         */
        static public final String ROOT_NAME = "users";

        /**
         * Phone of the user
         * <p>
         * Type: String
         */
        public final static String PHONE_ARM = "phone";

        /**
         * User's full name.
         * <p>
         * Type: String
         */
        public final static String FULL_NAME_ARM = "fullName";

        /**
         * User's full name.
         * <p>
         * Type: String
         */
        public final static String EMAIL_ARM = "email";

        /**
         * User's password.
         * <p>
         * Type: String
         */
        public final static String PASSWORD_ARM = "password";

        /**
         * User's account create date.
         * <p>
         * Type: LONG (TIMESTAMP)
         */
        public final static String CREATE_DATE_ARM = "createDate";

        /**
         * Check if user is login.
         * <p>
         * Type: Boolen
         */
        public final static String IS_LOGIN_ARM = "isLogin";


    }


}
