package sn.simplon.transfargent_android.ui.ajouter;

import android.content.Context;
import android.os.AsyncTask;
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

public class EmetteurTask extends AsyncTask<String,Void, JSONArray> {

    final String Url = "http://192.168.1.2:8080/emetteur/save";
    private Context context;
    public EmetteurTask(Context context){
        this.context = context;
    }
    @Override
    protected JSONArray doInBackground(String[] params) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("nom", params[0])
                .add("prenom",params[1])
                .add("telephone",params[2])
                .add("cni",params[3])
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
    protected void onPostExecute(JSONArray response) {
        Toast toast = null;
        if(response.length() > 0)
            toast = Toast.makeText(context, "Emetteur enregistré!   &", Toast.LENGTH_SHORT);
        else
            toast = Toast.makeText(context,"erreur ", Toast.LENGTH_SHORT);
        toast.show();



    }
}
