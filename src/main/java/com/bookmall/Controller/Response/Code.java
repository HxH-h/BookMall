package com.bookmall.Controller.Response;

//前表示响应类型  后表示功能类型
public class Code {
    public static final Integer GET_SUCCESS = 1101;
    public static final Integer GET_FAILURE = 2201;

    public static final Integer POST_SUCCESS = 1102;
    public static final Integer POST_FAILURE = 2202;

    public static final Integer Login_SUCCESS = 1003;
    public static final Integer Login_FAILURE = 2003;
    public static final Integer NEEDLOGIN = 3003;


    public static final Integer ADD_BOOK_SUCCESS = 1014;
    public static final Integer ADD_BOOK_EXCEPTION = 2014;

    public static final Integer DEL_BOOK_SUCCESS = 1024;
    public static final Integer DEL_BOOK_EXCEPTION = 2024;

    public static final Integer UPDATE_BOOK_SUCCESS = 1034;
    public static final Integer UPDATE_BOOK_EXCEPTION = 2034;

    public static final Integer SEARCH_BOOK_SUCCESS = 1044;
    public static final Integer SEARCH_BOOK_EXCEPTION = 2044;
    public static final Integer GETSUM_SUCCESS = 1054;

    public static final Integer UPDATE_TIME_EXCEPTION = 2005;

    public static final Integer USER_LOGIN_EXCEPTION = 2006;
    public static final Integer INDENTIFIED_CODE_INCORRECT = 2026;
    public static final Integer LOGIN_GETCODE_SUCCESS = 1016;
    public static final Integer USER_LOGIN_SUCCESS = 1026;

    public static final Integer ADD_ADDRESS_SUCCESS = 1036;
    public static final Integer UPDATE_DEFAULT_SUCCESS = 1046;
    public static final Integer DELETE_ADDRESS_SUCCESS = 1056;
    public static final Integer SEARCH_ADDRESS_SUCCESS = 1066;

    public static final Integer ADD_SHOPCART_SUCCESS = 1007;
    public static final Integer CLEAR_SHOPCART_SUCCESS =1017;
    public static final Integer GETINFO_SHOPCART_SUCCESS = 1027;
    public static final Integer ORDER_SUCCESS = 1037;
    public static final Integer PAY_SUCCESS = 1047;
    public static final Integer REMINDER_SUCCESS = 1057;
    public static final Integer GET_ORDER_SUCCESS = 1067;
    public static final Integer CONFIRM_ORDER_SUCCESS = 1077;
    public static final Integer CANCEL_ORDER_SUCCESS = 1087;
    public static final Integer UPDATE_ORDERSTATUS_SUCCESS = 1097;


    public static final Integer BOOK_NOTFOUND_EXCEPTION = 2007;
    public static final Integer COUNT_NOT_ENOUGH = 2017;
    public static final Integer SHOPCART_EMPTY = 2027;
    public static final Integer ADDRESS_NOTFOUND = 2037;
    public static final Integer BOOK_SHORTAGE = 2047;
    public static final Integer NUMBER_ILLEGAL = 2057;
    public static final Integer ORDER_NOTFOUND = 2067;

    public static final Integer GET_STAT_SUCCESS = 1008;




}
