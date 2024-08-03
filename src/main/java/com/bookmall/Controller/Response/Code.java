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

    public static final Integer UPDATE_TIME_EXCEPTION = 2005;

    public static final Integer USER_LOGIN_EXCEPTION = 2006;
    public static final Integer LOGIN_GETCODE_SUCCESS = 1016;
    public static final Integer USER_LOGIN_SUCCESS = 1026;
    public static final Integer INDENTIFIED_CODE_INCORRECT = 2026;

    public static final Integer ADD_SHOPCART_SUCCESS = 1007;
    public static final Integer CLEAR_SHOPCART_SUCCESS =1017;
    public static final Integer GETINFO_SHOPCART_SUCCESS = 1027;
    public static final Integer BOOK_NOTFOUND_EXCEPTION = 2007;

}
