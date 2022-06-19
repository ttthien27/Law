package com.android.Law.firebase;

public class LawDatabaseContract {
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
        public final static String IS_LOGIN_ARM = "login";

        /**
         * User's account email.
         * <p>
         * Type: Boolen
         */
        public final static String EMAIL_ARM = "email";


    }

    static public class DocumentEntry {
        /**
         * Name of the root of database entry for User
         */
        static public final String ROOT_NAME = "documents";

        /**
         * id of the document
         * <p>
         * Type: String
         */
        public final static String ID_ARM = "docId";

        /**
         * url of the document
         * <p>
         * Type: String
         */
        public final static String URL_ARM = "docUrl";

        /**
         * title of the document
         * <p>
         * Type: String
         */
        public final static String TITLE_ARM = "docTitle";

        /**
         * description of the document
         * <p>
         * Type: String
         */
        public final static String DESCRIPTION_ARM = "docDescription";

        /**
         * view of the document
         * <p>
         * Type: int
         */
        public final static String VIEW_ARM = "view";



    }
}
