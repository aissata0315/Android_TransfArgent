package sn.simplon.transfargent_android.ui.ajouter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AjouterTask extends AsyncTask<String,Void,JSONObject> {

    final String Url = "http://localhost:8080/";
    private Context context;
    public AjouterTask(Context context){
        this.context = context;
    }
    @Override
    protected JSONObject doInBackground(String[] params) {

      // Log.e("Params",params[0]+ " "+ params[1]);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("nomEmetteur", params[0])
                .add("prenomEmetteur",params[1])
                .add("telEmetteur",params[2])
                .add("cniEmetteur",params[3])
                .add("nomRecepteur",params[4])
                .add("prenomRecepteur",params[5])
                .add("telRecepteur",params[6])
                .add("prenomEmetteur",params[7])
                .add("montant",params[8])
                .build();
        Request request = new Request.Builder()
                .url(Url)
                .method("POST",requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
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

