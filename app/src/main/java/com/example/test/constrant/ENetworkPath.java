package com.example.test.constrant;

public class ENetworkPath {
    //访问地址
    private static final String HOST = "192.168.220.186";
    //端口号
    private static final String PORT = "8888";
    private static final String BASE_PATH="http://"+ HOST +":"+PORT;
    //以下为具体接口的访问地址

    //用户登录 接口地址
    public static final String USER_LOGIN = "http://"+ HOST +":"+PORT + "/user/doLogin";



    public static final String USER_TEST = "http://"+ HOST +":"+PORT + "/user/test";

    //用户注册
    public static final String USER_REGISTER=BASE_PATH+"/user/addOrUpdateUser";

    //获取当前用户
    public  static final String USER_GET=BASE_PATH+"/user/getCurUser";


    //获取所有问题类型 访问地址
     public  static  final  String QUESTION_TYPE_ALL=BASE_PATH+"/question/selectAllQuestionType";

     //获取指定问题类型，个数  访问地址
    public static  final  String LIST_QUESTION_BY_QUERY=BASE_PATH+"/question/selectAllQuestionType";



}
