package com.example.test.utils;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.example.test.dto.CurrentUserDTO;
import com.powater.common.util.CommonResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequestUtil {

    private static final int SUCCESS = 665;
    private static final int FALL = 894;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    String text = (String) msg.obj;

                    break;
                case FALL:
                    //Toast.makeText(MainActivity.this, "你还没有网络连接，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    static String res = "";


    /*
     * 登录请求
     * */
    public static String loginPost(String url, String json, SharedPreferences sharedPreferences) throws IOException, InterruptedException {

        String sessionId = sharedPreferences.getString("token", "");


        RequestBody body = RequestBody.create(JSON, json);
        Request request;

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                res = String.valueOf(" {\n" +
                        " code : 0,\n" +
                        " message : \"你还没网络连接\",\n" +
                        " object : null\n" +
                        "\n" +
                        "}");
                handler.sendEmptyMessage(FALL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message msg = Message.obtain();
                msg.obj = string;
                msg.what = SUCCESS;
                res = String.valueOf(string);
                try {
                    CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(string);
                    JSONObject jsonObject = new JSONObject(commonResult.getObject().toString());

                    String token =  jsonObject.getString("token");
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("token", token);
                    edit.commit();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                handler.sendMessage(msg);

//                if (TextUtils.isEmpty(sessionId)) {
//                    Headers headers = response.headers();
//                    List<String> cookies = headers.values("Set-Cookie");
//                    if (cookies.size() > 0) {
//                        String session = cookies.get(0);
//                        String sessionId = session.substring(0, session.indexOf(";"));
//                        SharedPreferences.Editor edit = sharedPreferences.edit();
//                        edit.putString("sessionId", sessionId);
//                        edit.commit();
//                    }
//                }


            }
        });
        Thread.sleep(200);
        String temp = res;
        res = "";
        return temp;
    }

    /*
     * 普通的 post请求
     * */
    public static String commonPost(String url, String json, SharedPreferences sharedPreferences) throws IOException, InterruptedException {

        RequestBody body = RequestBody.create(JSON, json);
        Request request;


        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = Message.obtain();
                msg.obj = "";
                msg.what = FALL;

                res = String.valueOf(" {\n" +
                        " code : 0,\n" +
                        " message : \"你还没网络连接\",\n" +
                        " object : null\n" +
                        "\n" +
                        "}");
//                handler.sendEmptyMessage(FALL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message msg = Message.obtain();
                msg.obj = string;
                msg.what = SUCCESS;
                res = String.valueOf(string);
                handler.sendMessage(msg);

            }
        });
        Thread.sleep(300);
        String temp = res;
        res = "";
        return temp;
    }


}
