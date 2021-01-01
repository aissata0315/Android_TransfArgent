package sn.simplon.transfargent_android.ui.ajouter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TransactionTask extends AsyncTask<String,Void,JSONArray> {

    final String Url = "http://192.168.1.2:8080/envoi/save";
    private Context context;
    public TransactionTask(Context context){
        this.context = context;
    }
    @Override
    protected JSONArray doInBackground(String[] param) {

      // Log.e("Params",params[0]+ " "+ params[1]);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("montant",param[0])
                .build();
        Request request = new Request.Builder()
                .url(Url)
                .method("POST",requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }


    protected void onPostExecute(JSONObject response) {
        Toast toast = null;
        try {
            if(response.getString("success").equals("true"))
                toast = Toast.makeText(context, "Transaction Reussie!   &", Toast.LENGTH_SHORT);
            else
                toast = Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

