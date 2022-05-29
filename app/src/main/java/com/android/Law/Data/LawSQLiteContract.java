package com.android.Law.Data;

import android.provider.BaseColumns;

public class LawSQLiteContract {

    static public class TableDocumentEntry{
        /**
         * Name of database table for Follow and Seen
         */
        static public final String TABLE_NAME_FOLLOW = "FollowDocument";
        static public final String TABLE_NAME_SEEN = "SeenDocument";


        public final static String COLUMNS_DOC_ID = "docId";

        public final static String COLUMNS_DOC_TITLE = "docTitle";

        public final static String COLUMNS_DOC_DESCRIPTION = "docDescription";

        public final static String COLUMNS_DOC_URL = "docUrl";
    }
}
