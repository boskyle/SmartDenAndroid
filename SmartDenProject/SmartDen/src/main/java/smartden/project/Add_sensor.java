/*Team Name: Humber Elites*/

package smartden.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Add_sensor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {




    final String temp22 = Log_in.mUid;



    /*Spinner*/

    Spinner spinner;


    EditText code;
    private DrawerLayout mDrawerLayout;

    ImageView v;

    int CAMERA_PERMISSION_CODE = 1;

    /*QR Scan stuff*/

    private IntentIntegrator qrScan;


    /*DB Connection defines*/
    RequestQueue rq;
    RequestQueue rq2;
    String showUrl;
    String eliminateDHTUrl="http://boswellkyle.com/ceng319_smartden/eliminate_dht_code.php";


    /**/
    Button rg_btn;
    TextView tv_temp;



    String sensor_name;
    String sc_et;
    String ifSensor;
    String jsn_arrays;
    String poly_serial;
    Spinner location_spinner;
    String location;





    protected void init()
    {
        if (ContextCompat.checkSelfPermission(Add_sensor.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)
        {
            Snackbar.make(findViewById(R.id.as_main),"Camera Permission Granted!",Snackbar.LENGTH_SHORT).show();
            scanQRCode(findViewById(R.id.as_main));
        }

        else {requestCameraPermission();}
    }


protected  void requestCameraPermission ()
{
    if (ActivityCompat.shouldShowRequestPermissionRationale(Add_sensor.this,Manifest.permission.CAMERA))
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_sensor.this).setTitle("Camera Access Permission Required");
        AlertDialog d1;
        builder.setMessage("Can I get permission to access your Camera?");
        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                /*INVOKE PERMISSION REQ AGAIN*/
                ActivityCompat.requestPermissions(Add_sensor.this, new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

            }
        });

        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        d1 = builder.create();
        d1.show();


    } else
    {
        ActivityCompat.requestPermissions(Add_sensor.this, new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
    }


}



public void scanQRCode(View view)
{
    qrScan.initiateScan();
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(Add_sensor.this,"Result not found",Toast.LENGTH_SHORT).show();
            }
            else
            {


                    Snackbar.make(findViewById(R.id.as_main),"Code: "+result.getContents(),Snackbar.LENGTH_SHORT).show();
                    code.setText(result.getContents());


            }

        } else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        mDrawerLayout = findViewById(R.id.drawer_layout);



        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("Add Sensor");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu1);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        v = findViewById(R.id.iv_scan);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Snackbar.make(findViewById(R.id.as_main),"Scan",Snackbar.LENGTH_SHORT).show();
                    init();
            }
        });


        qrScan = new IntentIntegrator(Add_sensor.this);

        rq = Volley.newRequestQueue(Add_sensor.this);
        rq2 = Volley.newRequestQueue(Add_sensor.this);




        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

//
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);

                        switch (menuItem.getItemId())
                        {

                            case R.id.home:
                                intent = new Intent(Add_sensor.this, MainMenu.class);
                                startActivity(intent);
                                break;

                            case R.id.garage:
                                intent = new Intent(Add_sensor.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Add_sensor.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Add_sensor.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.security:
                                intent = new Intent(Add_sensor.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Add_sensor.this, Sensors.class);
                                startActivity(intent);
                                break;
                            case R.id.log_out:
                                intent = new Intent(Add_sensor.this, SplashActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.setting:
                                intent = new Intent(Add_sensor.this, Setting.class);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });




        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );




        /*Spinner logic*/
        spinner = (Spinner) findViewById(R.id.spn_sensors);
        spinner.setOnItemSelectedListener(Add_sensor.this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Add_sensor.this,R.array.list_of_sensors,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        location_spinner = (Spinner) findViewById(R.id.spinner2);
        location = location_spinner.getSelectedItem().toString();






        /*code et*/
        code = findViewById(R.id.et_sensor_code);

        rg_btn = (Button) findViewById(R.id.btn_reg);

        tv_temp = (TextView) findViewById(R.id.tv_temp);





        rg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(Add_sensor.this,poly_serial +temp22,Toast.LENGTH_SHORT).show();
                checkDHTCodes();
            }
        });





    } // end of onCreate




    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
               switch (position)
               {
                   case 0:
                       code.setHint("DHT-11 Code");
                       sensor_name = "temperature&humidity sensor";
                       ifSensor="dht_pick";
                       showUrl="http://boswellkyle.com/ceng319_smartden/show_dht11_codes.php";
                       jsn_arrays="jsn_dht_codes";
                       poly_serial="dht11_serial_code";
                       break;

                   case 1:
                       code.setHint("HC-SR04 Code");
                       sensor_name = "distance sensor";
                       ifSensor="hcs_pick";
                       showUrl="http://boswellkyle.com/ceng319_smartden/show_hcs_codes.php";
                       jsn_arrays="jsn_hcs_codes";
                       poly_serial="hcs_serial_code";
                       break;
                   case 2:
                       code.setHint("RGB Code");
                       sensor_name= "rgb light";
                       ifSensor="rgb_pick";
                       showUrl="http://boswellkyle.com/ceng319_smartden/show_rgb_codes.php";
                       jsn_arrays="jsn_rgb_codes";
                       poly_serial="rgb_serial_code";
                       break;
                   case 3:
                       code.setHint("MOTOR Code");
                       sensor_name= "motor sensor";
                       ifSensor="motor_pick";
                       showUrl="http://boswellkyle.com/ceng319_smartden/show_uln_codes.php";
                       jsn_arrays="jsn_uln_codes";
                       poly_serial="uln_serial_code";
                       break;
               }


        }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }



    protected void checkDHTCodes()
    {


        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, showUrl, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONArray dht_codes_array = response.getJSONArray(jsn_arrays);
                             sc_et = code.getText().toString();


                            for (int i=0;i<dht_codes_array.length();i++)
                            {
                                JSONObject serial_code = dht_codes_array.getJSONObject(i);
                                if (serial_code.getString(poly_serial).trim().equalsIgnoreCase(sc_et))
                                {
                                    //tv_temp.setText("Code: "+sc_et+" exists");


                                    StringRequest req2 = new StringRequest(Request.Method.POST, eliminateDHTUrl, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            Toast.makeText(Add_sensor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    {

                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<String, String>();
                                            String temp = code.getText().toString();

                                            if (ifSensor.equalsIgnoreCase("dht_pick")) {
                                                params.put("sensor_location",location);
                                                params.put("s_name",sensor_name);
                                                params.put("user_id",temp22);
                                                params.put("used", temp);
                                                params.put("dhtC","dht_pick");
                                                params.put("poly",poly_serial);
                                                params.put("poly_two","DHT_CODES");

                                            }

                                            else if (ifSensor.equalsIgnoreCase("hcs_pick")) {
                                                params.put("sensor_location",location);
                                                params.put("s_name",sensor_name);
                                                params.put("user_id",temp22);
                                                params.put("used", temp);
                                                params.put("hcsC","hcs_pick");
                                                params.put("poly",poly_serial);
                                                params.put("poly_two","HCS_CODES");
                                            }
                                          else  if (ifSensor.equalsIgnoreCase("rgb_pick")) {
                                                params.put("sensor_location",location);
                                                params.put("s_name",sensor_name);
                                                params.put("user_id",temp22);
                                                params.put("used", temp);
                                                params.put("rgbC","rgb_pick");
                                                params.put("poly",poly_serial);
                                                params.put("poly_two","RGB_CODES");
                                            }
                                           else  if (ifSensor.equalsIgnoreCase("motor_pick")) {
                                                params.put("sensor_location",location);
                                                params.put("s_name",sensor_name);
                                                params.put("user_id",temp22);
                                                params.put("used", temp);
                                                params.put("motorC","motor_pick");
                                                params.put("poly",poly_serial);
                                                params.put("poly_two","ULN_CODES");
                                            }

                                            return params;
                                            }
                                    };
                                    rq2.add(req2);
                                    Toast.makeText(Add_sensor.this,"Request has been made",Toast.LENGTH_SHORT).show();
                                    Intent myIntent = new Intent(Add_sensor.this, Sensors.class);
                                    startActivity(myIntent);

                                } // end of if statement

                            } // end of for loop (looping through json objects)


                        } catch (JSONException e) {e.printStackTrace(); Toast.makeText(Add_sensor.this,e.getMessage(),Toast.LENGTH_SHORT).show();}

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }


                });
            rq.add(req);





    } // end of method






}
