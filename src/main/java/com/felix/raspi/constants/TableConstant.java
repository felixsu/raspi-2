package com.felix.raspi.constants;

/**
 * Created by fsoewito on 2/26/2016.
 */
public class TableConstant {
    //    sequence
    public static final String SEQ_AUTHORITIES = "seq_authorities_id";
    public static final String SEQ_USERS = "seq_users_id";
    public static final String SEQ_TEMPERATURES = "seq_temperature_id";

    //    table name
    public static final String TABLE_USERS = "users";
    public static final String TABLE_AUTHORITIES = "authorities";
    public static final String TABLE_USERS_AUTHORITIES = "users_authorities";
    public static final String TABLE_TEMPERATURE = "temperature";
    public static final String TABLE_DEVICE_DETAILS = "device_details";

    //    COL NAME
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_AUTHORITY = "authority";
    public static final String COL_PASSWORD = "password";

    //    LEN
    public static final int LEN_USERNAME = 50;
    public static final int LEN_NAME = 50;
    public static final int LEN_EMAIL = 50;
    public static final int LEN_PASSWORD = 500;
    public static final int LEN_AUTHORITY = 50;
}
