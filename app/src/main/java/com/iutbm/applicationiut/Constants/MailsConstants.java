package com.iutbm.applicationiut.Constants;


public class MailsConstants {

    public static final String KEY_PREFERENCES = "MAIL_SHARED_PREFERENCES";

    public static final String HOST = "imaps-edu.univ-fcomte.fr";
    public static final String PROTOCOL = "imaps";

    public static final String KEY_USER = "MAIL_USER";
    public static final String KEY_PASSWORD = "MAIL_PASSWORD";
    public static final String KEY_IS_CONNECTED = "BOOLEAN_CONNECTION_RESULT";
    public static final String KEY_MAILS_RECEIVED = "LIST_MAILS_RECEIVED";
    public static final String KEY_NUMBER_MAILS = "NUMBER_MAILS_RECEIVED";
    public static final String KEY_LAST_MAIL = "LAST_MAIL_RECEIVED";
    public static final String KEY_STOP_SERVICE = "BOOL_STOP_SERVICE";

    public static final String ACTION_CONNECT = "MAIL_CONNECT_INTENT";
    public static final String ACTION_RECEIVE = "MAIL_RECEIVE_INTENT";
    public static final String ACTION_CHECK = "MAIL_CHECK_INTENT";
    public static final String ACTION_STOP_SERVICE = "STOP_CHECK_SERVICE";

    public static final int PERIOD_INTERVAL = 600000;

}
