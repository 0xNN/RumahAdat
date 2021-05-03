package com.sendi.rumahadat;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    static final String URL_DATA = "https://json.konsultasikomputer.com/provinsi.php";
    static final String URL_IMAGE = "https://json.konsultasikomputer.com/images/";
    static final int CONNECTION_TIMEOUT = 10000;
    static final int READ_TIMEOUT = 15000;

    RecyclerView recyclerView;
    ArrayList<RumahAdat> arrayList = new ArrayList<>();

    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.rv_rumahadat);
        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutAnimation(animation);

        new AsyncFetch().execute();

        return view;
    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewRumahAdapter adapter = new CardViewRumahAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("Loading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(URL_DATA);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    RumahAdat rumahAdat = new RumahAdat();
                    rumahAdat.setNama(jsonObject.getString("nama_rumah_adat"));
                    rumahAdat.setDetail(jsonObject.getString("penjelasan"));
                    rumahAdat.setProvinsi(jsonObject.getString("nama_prov"));
                    rumahAdat.setFoto(String.format("%s%s", URL_IMAGE, jsonObject.getString("foto")));

                    arrayList.add(rumahAdat);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            showRecyclerCardView();
        }
    }
}