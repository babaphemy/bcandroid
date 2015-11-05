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

        View.OnClickListener webpaylistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tryit.setText("oya baby komole !!!");
                String disama = ama.getText().toString();
                if(disama.length() > 0){
                    HttpsURLConnection conn;

                    try{
                        URL url=new URL("https://myeverlasting.net/api/addmerchant/");

                      //  String param="businesscategory=" + URLEncoder.encode("6205", "UTF-8")+
                             //   "&companyname="+URLEncoder.encode("101","UTF-8")+
                            //    "&lastname="+URLEncoder.encode(disama, "UTF-8")+
                           //     "&firstname="+URLEncoder.encode("566","UTF-8");
                        conn = (HttpsURLConnection) url.openConnection();
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        // conn.setRequestProperty("Content-Length", length);
                     //   conn.setFixedLengthStreamingMode(param.getBytes().length);
                       // conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                       // PrintWriter out = new PrintWriter(conn.getOutputStream());
                       // out.print(param);
                      //  out.close();


                        //conn.setReadTimeout(10000);
                        //conn.setConnectTimeout(15000);

                        //conn.setDoInput(true);

               Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("product_id", "6205")
                        .appendQueryParameter("pay_item_id", "101")
                        .appendQueryParameter("currency", "566")
                        .appendQueryParameter("amount", disama)
                        .appendQueryParameter("txn_ref", "3453001")
                        .appendQueryParameter("site_redirect_url", "webpost");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writter = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );
                writter.write(query);
                writter.flush();
                writter.close();
                os.close();

                conn.connect();

                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }



            }
        };
        //dopay.setOnClickListener(webpaylistener);






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
        String disama = ama.getText().toString();
        if(disama.length() > 0){
            String url = "https://stageserv.interswitchng.com/test_paydirect/pay";
            //String url = "https://myeverlasting.net/api/addmerchant/";
                StringRequest postrequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String resp) {
                                try {
                                    JSONObject jsonresponse = new JSONObject(resp).getJSONObject("form");
                                    String site = jsonresponse.getString("site"),
                                            network = jsonresponse.getString("network");
                                    System.out.println("Site: " + site + "\nNetwork: " + network);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                volleyError.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new WeakHashMap<>();
                        // the POST parameters:
                        params.put("firstname", "6205");
                        params.put("company", "200000");
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
        public void sendme(View v){
            String disama = "2000";
            if(disama.length() > 0){
                HttpsURLConnection conn;

             //   try{
                    //URL url=new URL("https://stageserv.interswitchng.com/test_paydirect/pay");
                    String url = "https://stageserv.interswitchng.com/test_paydirect/pay";
                    StringRequest postrequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String resp) {
                                    try {
                                        JSONObject jsonresponse = new JSONObject(resp).getJSONObject("form");
                                        String site = jsonresponse.getString("site"),
                                                network = jsonresponse.getString("network");
                                        System.out.println("Site: " + site + "\nNetwork: " + network);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
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
                            params.put("amount", "200000");
                            return params;
                        }
                    };
                   // RequestQueue rque = Volley.newRequestQueue(this);
                   // rque.add(postrequest);



                  /*  String param="businesscategory=" + URLEncoder.encode("6205", "UTF-8")+
                            "&companyname="+URLEncoder.encode("101","UTF-8")+
                            "&lastname="+URLEncoder.encode(disama, "UTF-8")+
                            "&firstname="+URLEncoder.encode("566","UTF-8");
                    conn = (HttpsURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    // conn.setRequestProperty("Content-Length", length);
                    conn.setFixedLengthStreamingMode(param.getBytes().length);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    PrintWriter out = new PrintWriter(conn.getOutputStream());
                    out.print(param);
                    out.close();


                    //conn.setReadTimeout(10000);
                    //conn.setConnectTimeout(15000);

                    //conn.setDoInput(true);

               /* Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("product_id", "6205")
                        .appendQueryParameter("pay_item_id", "101")
                        .appendQueryParameter("currency", "566")
                        .appendQueryParameter("amount", disama)
                        .appendQueryParameter("txn_ref", "3453001")
                        .appendQueryParameter("site_redirect_url", "webpost");
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writter = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );
                writter.write(query);
                writter.flush();
                writter.close();
                os.close();

                conn.connect();*/

             //   } catch (MalformedURLException e){
               //     e.printStackTrace();
              //  } catch (IOException e) {
                //    e.printStackTrace();
              //  }



            }


        }
    }
}
