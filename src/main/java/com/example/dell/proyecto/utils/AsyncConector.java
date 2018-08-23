package com.example.dell.proyecto.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.example.dell.proyecto.R;
import com.example.dell.proyecto.http.ConectorHttpJSON;

import org.json.JSONObject;

import java.util.ArrayList;

public class AsyncConector extends AsyncTask<Void, Void, Void> {
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private String url;
    private ProgressDialog pd;
    private Context context;

    public AsyncConector(Context context, ArrayAdapter<String> adapter,
                         String url) {
        this.adapter = adapter;
        this.url = url;
        pd = new ProgressDialog(context);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd.setIndeterminate(true);

        // Mostramos el ProgressDialog.
        pd.show();
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        ConectorHttpJSON conector = new ConectorHttpJSON(url);

        try {
            // Recogemos el documento JSON de Internet.
            JSONObject obj = conector.execute();

            // Analizamos el documento JSON y recogemos todos los links a las
            // fotos. Crearemos esta clase m�s adelante.
            data = new JSONToStringCollection(obj).getArrayList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // A�adimos todos los links al Adapter.
        for (String tmp : data)
            adapter.add(tmp);

        // Indicamos al Adapter que ha cambiado su contenido, para que actualice
        // a su vez los datos mostrados en el ListView.
        adapter.notifyDataSetChanged();

        // Eliminamos el ProgressDialog.
        pd.dismiss();
        super.onPostExecute(result);
    }
}
