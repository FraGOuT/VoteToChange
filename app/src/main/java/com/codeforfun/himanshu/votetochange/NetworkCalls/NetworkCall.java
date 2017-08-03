package com.codeforfun.himanshu.votetochange.NetworkCalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.codeforfun.himanshu.votetochange.AppConstants;
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
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Himanshu on 12-07-2017.
 */

public class NetworkCall {

    public static void execute(Context context, String URL , List<String> queryData, String progressMessage, NetworkCallInterrface netCallInt){
        try {
            new Background(context, netCallInt, progressMessage).execute(URL, Encode.generateEncodedString(queryData));
        } catch (IncorrectEncodingData incorrectEncodingData) {
            incorrectEncodingData.printStackTrace();
        }
        //Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }
}

class Background extends AsyncTask<String,Void,String> {

    private Context context;
    private ProgressDialog progressDialog;
    private NetworkCallInterrface networkCallInterrface;
    private String progressMessage;

    Background(Context context, NetworkCallInterrface networkCallInterrface, String progressMessage) {
        this.context = context;
        this.networkCallInterrface = networkCallInterrface;
        this.progressMessage = progressMessage;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(progressMessage);
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {
        HttpURLConnection httpURLConnection=null;
        try {
            URL url = new URL(args[0]);
            Log.i(AppConstants.TAG,"Network call for url = "+args[0]+ ""+args[1]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(10*1000);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            bufferedWriter.write(args[1]);
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

            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            return  response;

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
        networkCallInterrface.onResultReturn(s);
        super.onPostExecute(s);
    }
}
