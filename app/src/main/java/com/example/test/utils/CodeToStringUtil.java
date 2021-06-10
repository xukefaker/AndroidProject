package com.example.test.utils;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.example.test.constrant.ResponseCode;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
/*
* 响应码转 提示字符串
* */
public class CodeToStringUtil {
    private static final Keccak.Digest512 digest512 = new Keccak.Digest512();

    public static String encrypt(String origin) {
        return new String(Hex.encode(digest512.digest(origin.getBytes(StandardCharsets.UTF_8))));
    }

    public static String getResponseMessage(int code) {
        String message = "";
        switch (code) {
            case ResponseCode.SIGN_IN_SUCCESS:
                message = "登录成功";
                break;
            case ResponseCode.SIGN_UP_SUCCESS:
                message = "注册成功";
                break;
            case ResponseCode.SIGN_IN_FAILED:
                message = "用户名或密码错误";
                break;
            case ResponseCode.SIGN_UP_FAILED:
                message = "注册失败";
                break;
            case ResponseCode.DELETE_FAILED:
                message = "删除失败";
                break;
            case ResponseCode.DELETE_SUCCESS:
                message = "删除成功,自动退出";
                break;
            case ResponseCode.UPDATE_SUCCESS:
                message = "更新成功";
                break;
            case ResponseCode.UPDATE_FAILED:
                message = "更新失败";
                break;
            case ResponseCode.EMPTY_RESPONSE:
                message = "响应体为空";
                break;
            case ResponseCode.SERVER_ERROR:
                message = "服务器错误";
                break;
            case ResponseCode.JSON_SERIALIZATION:
                message = "JSON序列化错误";
                break;
            case ResponseCode.EXIT_SUCCESS:
                message = "退出成功";
                break;
            case ResponseCode.REQUEST_FAILED:
                message = "请求发送失败";
                break;
            case ResponseCode.UNCHANGED_INFORMATION:
                message = "未修改信息";
                break;
        }
        return message;
    }

    public static void showMessage(Context context, Message message) {
        Toast.makeText(context, getResponseMessage(message.what), Toast.LENGTH_SHORT).show();
    }
}