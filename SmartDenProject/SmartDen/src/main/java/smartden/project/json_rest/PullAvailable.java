package smartden.project.json_rest;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import smartden.project.Add_sensor;
import smartden.project.R;
import smartden.project.Sensors;

public class PullAvailable {


    RequestQueue rq_pull;
    RequestQueue req_eliminate;
    JsonObjectRequest req;

    String showUrl = "http://boswellkyle.com/smartden/showAvailable.php";

    String qr_from_camera;

    private String qr_used;


    public Context myContext;
    public LayoutInflater li;
    public int sp_layout;

    public Context getContext()
    {
        return myContext;
    }

    public void setMyContext(Context myContext)
    {
        this.myContext = myContext;
    }

    public LayoutInflater getInflater() {return  li;}

    public void setInflater(LayoutInflater li) {this.li = li;}

    public void set_sp_layout (int sp_layout) {this.sp_layout = sp_layout;}


    public String get_qr_used() {return qr_from_camera;}

    public void setQr (String qr_from_camera)
    {
     this.qr_from_camera = qr_from_camera;
    }








    /*A method definition => only invoked if the code is available (qr_scan succeed match with database)*/
    public void locationChoices ()
    {
        String[] s = { "India ", "Arica", "India ", "Arica", "India ", "Arica",
                "India ", "Arica", "India ", "Arica" };
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(myContext,
                sp_layout,s);

        final Spinner spn = new Spinner(myContext);
        spn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setMessage("Location");
        builder.setView(spn);
        builder.create().show();

    }







    public void pullAvailable ()

    {

        rq_pull = Volley.newRequestQueue(myContext);



         req = new JsonObjectRequest (Request.Method.GET, showUrl, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    /*retreiving the json array of serial codes (**This changes also depending on the selection of the spinner**)*/
                    JSONArray jsn_array = response.getJSONArray("jsn_codes");



                    for (int i=0;i<jsn_array.length();i++)
                    {
                        /*iterating through individual elements inside the array from beginning to last*/
                        JSONObject serial_code = jsn_array.getJSONObject(i);

                        if (serial_code.getString("qr_generated_code").trim().equalsIgnoreCase(qr_from_camera))
                        {
                            qr_used = qr_from_camera;
                            locationChoices();
                            break;
                        } else {/*IF QR SCAN CODE IS NOT AVAILABLE IN OUR QR CODES DATABASE*/}

                    } // end of for loop (looping through json objects)


                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }


        });


        rq_pull.add(req);

    }













}
