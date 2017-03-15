package com.gmobile.githubapiproject.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mark on 3/13/17.
 */

public class H {
    static ProgressDialog Pdialog;
    private static SharedPreferences mSharedPreferences;
    private static HttpClient client  = new DefaultHttpClient();

    public static void showLoadingDialog(Context mContext) {
        Pdialog = new ProgressDialog(mContext);
        Pdialog.setMessage("Please wait...");
        Pdialog.setIndeterminate(true);
        Pdialog.setCancelable(false);
        Pdialog.show();
    }

    public static void hideLoadingDialog() {
        Pdialog.dismiss();
    }

    public static void T(Context mContext, String Message) {
        Toast.makeText(mContext, Message, Toast.LENGTH_SHORT).show();
    }


    public static String simpleGETRequest(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        return getContent(response);
    }

    public static String getContent(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent()));
        String body = "";
        String content = "";
        while ((body = rd.readLine()) != null) {
            content += body + "\n";
        }
        return content.trim();
    }


}
