package net.myeverlasting.webpost;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.WeakHashMap;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    EditText ama;
    Button dopay;
    TextView tryit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ama = (EditText) findViewById(R.id.ama);
        dopay = (Button) findViewById(R.id.pay);
        tryit = (TextView) findViewById(R.id.mesag);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void send(View v){
        final String disama = ama.getText().toString();
        if(disama.length() > 0){
            String url = "https://stageserv.interswitchng.com/test_paydirect/pay";
            //String url = "https://myeverlasting.net/api/addmerchant/";
                StringRequest postrequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String resp) {
                                tryit.setText(resp);
                              
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("Error ["+volleyError+"]");
                                volleyError.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new WeakHashMap<>();
                        // the POST parameters:
                        params.put("product_id", "6205");
                        params.put("pay_item_id", "101");
                        params.put("currency", "566");
                        params.put("txn_ref", "1234567YU");
                        params.put("site_redirect_url", "http://localhost/lotto/tpay.php");
                        params.put("hash", "566");
                        params.put("cust_name", "Demo Test");
                        params.put("amount", disama);
                        return params;
                    }
                };
                RequestQueue rque = Volley.newRequestQueue(this);
                rque.add(postrequest);





        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Towebpay extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            return null;
        }
      
    }
}
