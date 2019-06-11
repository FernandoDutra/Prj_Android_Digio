package br.com.fernandodutra.prj_android_digio.activity;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandodutra.prj_android_digio.R;
import br.com.fernandodutra.prj_android_digio.adapter.CashAdapter;
import br.com.fernandodutra.prj_android_digio.adapter.ProductsAdapter;
import br.com.fernandodutra.prj_android_digio.adapter.SpotlightAdapter;
import br.com.fernandodutra.prj_android_digio.model.Cash;
import br.com.fernandodutra.prj_android_digio.model.Products;
import br.com.fernandodutra.prj_android_digio.model.Spotlight;
import br.com.fernandodutra.prj_android_digio.utils.JSONParser;
import br.com.fernandodutra.prj_android_digio.utils.Permissions;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 16/05/2019
 * Time: 20:44
 * Prj_Android_Digio
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";
    ViewPager vp_spotlight;
    ViewPager vp_cash;
    ViewPager vp_products;

    SpotlightAdapter spotlightAdapter;
    ProductsAdapter productsAdapter;
    CashAdapter cashAdapter;

    List<Spotlight> spotlights;
    List<Products> products;
    List<Cash> cash;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    String URL_JSON = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/products";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPermissions();
        initiateVariables();

        new GetJSON().execute();
    }

    private void setCashAdapter(List<Cash> cash) {
        cashAdapter = new CashAdapter(cash, MainActivity.this);
        vp_cash.setAdapter(cashAdapter);
        vp_cash.setPadding(100, 0, 100, 0);
    }

    private void setProductsAdapter(List<Products> products) {
        productsAdapter = new ProductsAdapter(products, MainActivity.this);
        vp_products.setAdapter(productsAdapter);
        vp_products.setPadding(100, 0, 100, 0);
    }

    private void setSpotlightAdapter(List<Spotlight> spotlights) {
        spotlightAdapter = new SpotlightAdapter(spotlights, MainActivity.this);
        vp_spotlight.setAdapter(spotlightAdapter);
        vp_spotlight.setPadding(100, 0, 100, 0);
    }

    private void initiateVariables() {
        vp_spotlight = findViewById(R.id.activity_main_vp_spotlight);
        vp_cash = findViewById(R.id.activity_main_vp_cash);
        vp_products = findViewById(R.id.activity_main_vp_products);

        spotlights = new ArrayList<>();
        products = new ArrayList<>();
        cash = new ArrayList<>();
    }

    private void setupPermissions() {
        // Request Permissions
        String[] permissoes = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
        };
        Permissions.validate(this, 0, permissoes);
    }

    private class GetJSON extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JSONParser sh = new JSONParser();
            String jsonStr = sh.makeServiceCall(URL_JSON);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = null;

                    jsonArray = jsonObj.getJSONArray("spotlight");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        spotlights.add(new Spotlight(jsonArray.getJSONObject(i)));
                    }

                    jsonArray = jsonObj.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        products.add(new Products(jsonArray.getJSONObject(i)));
                    }

                    cash.add(new Cash(jsonObj.getJSONObject("cash")));

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setSpotlightAdapter(spotlights);
            setProductsAdapter(products);
            setCashAdapter(cash);
        }
    }
}
