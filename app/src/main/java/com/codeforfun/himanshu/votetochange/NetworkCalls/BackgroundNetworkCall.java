package com.codeforfun.himanshu.votetochange.NetworkCalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.codeforfun.himanshu.votetochange.NetworkHelper.Encode;
import com.codeforfun.himanshu.votetochange.NetworkHelper.IncorrectEncodingData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Himanshu on 13-03-2017.
 */

public class BackgroundNetworkCall {

    public Context mContext;

    public String execute(String URL , List<String> queryData, Context c) throws ExecutionException, InterruptedException {
        mContext = c;
        String str = null;
        try {
            str = new BackgroundCall(mContext).execute(URL, Encode.generateEncodedString(queryData)).get();
        } catch (IncorrectEncodingData incorrectEncodingData) {
            incorrectEncodingData.printStackTrace();
        }
        //Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
        return str;
    }

}

class BackgroundCall extends AsyncTask<String,Void,String>{

    Context context;
    ProgressDialog progressDialog;

    public BackgroundCall(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... args) {
        HttpURLConnection httpURLConnection=null;
        try {
            URL url = new URL(args[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(10*1000);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String data = args[1];

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line;
            while((line=bufferedReader.readLine())!=null){
                response+=line;
            }

            bufferedWriter.close();
            inputStream.close();
            return  response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null ){
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
    }
}
