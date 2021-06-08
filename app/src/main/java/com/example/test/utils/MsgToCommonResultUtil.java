package com.example.test.utils;

import com.powater.common.util.CommonResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MsgToCommonResultUtil {

    public static CommonResult strToCommonResult(String msg) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(msg);
        int code = Integer.parseInt(jsonObject1.get("code").toString());
        String message = jsonObject1.get("message").toString();
        Object object = jsonObject1.get("object");

        CommonResult commonResult = CommonResult.init(code, message, object);

        return commonResult;


    }


}
