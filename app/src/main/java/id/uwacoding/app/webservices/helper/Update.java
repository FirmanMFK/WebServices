package id.uwacoding.app.webservices.helper;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import id.uwacoding.app.webservices.ReadActivity;

/**
 * Created by Firman on 6/7/2017.
 */

public class Update extends AsyncTask<String, Void, String> {

    ReadActivity R = new ReadActivity();

    private Context context;
    private String link = Http.server;
    public Update (Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        String nim          = arg0[0];
        String nama         = arg0[1];
        String alamat       = arg0[2];

        String data;
        BufferedReader bufferedReader;
        String result;
        try {
            data = "?mahasiswa_id=" + URLEncoder.encode(R.MAHASISWA_ID, "UTF-8");
            data += "&nim=" + URLEncoder.encode(nim, "UTF-8");
            data += "&nama_mhs=" + URLEncoder.encode(nama, "UTF-8");
            data += "&tgl_lahir=" + URLEncoder.encode(R.TGL_LAHIR, "UTF-8");
            data += "&prodi_id=" + URLEncoder.encode(R.PRODI_ID, "UTF-8");
            data += "&alamat=" + URLEncoder.encode(alamat, "UTF-8");

            link = link + "update.php" + data;

            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        Log.d("URL ", link);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("result");
                if (query_result.equals("berhasil")) {
                    Toast.makeText(context, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    ((Activity)(context)).finish();
                } else if (query_result.equals("gagal")) {
                    Toast.makeText(context, query_result, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }

}
