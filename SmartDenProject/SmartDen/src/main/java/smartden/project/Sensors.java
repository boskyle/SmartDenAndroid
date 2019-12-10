/*Team Name: Humber Elites*/
package smartden.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Sensors extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ArrayList<Sensor_Info>  sensorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SensorAdapter mAdapter;

    String showUrl="http://boswellkyle.com/ceng319_smartden/display_sensors.php";
    RequestQueue rq;

    final String temp23 = Log_in.mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        rq = Volley.newRequestQueue(Sensors.this);

        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("My Sensor");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu1);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

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
                                intent = new Intent(Sensors.this, MainMenu.class);
                                startActivity(intent);
                                break;

                            case R.id.garage:
                                intent = new Intent(Sensors.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Sensors.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Sensors.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.security:
                                intent = new Intent(Sensors.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Sensors.this, Sensors.class);
                                startActivity(intent);
                                break;
                            case R.id.log_out:
                                intent = new Intent(Sensors.this, SplashActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.setting:
                                intent = new Intent(Sensors.this, Setting.class);
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

        FloatingActionButton fab1 = findViewById(R.id.action1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sensors.this, Add_sensor.class);

                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.add_sensor_recycleview);
        mAdapter = new SensorAdapter(sensorList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareSensorData();

    }

    private void prepareSensorData() {

        Toast.makeText(Sensors.this,temp23,Toast.LENGTH_SHORT).show();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,showUrl,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray listings = response.getJSONArray("sensor_listings");
                    for (int i=0;i<listings.length();i++) {
                        JSONObject user =listings.getJSONObject(i);
                        String str_uid = user.getString("uid");

                        if (str_uid.equalsIgnoreCase(temp23))
                        {
                            String str_sensor_code = user.getString("sensor_code");
                            String str_sensor_name = user.getString("sensor_name");
                            String str_sensor_loc = user.getString("sensor_location");
                            Sensor_Info sensor = new Sensor_Info();
                            sensor.setname(str_sensor_name);
                            sensor.setqrcode(str_sensor_code);
                            sensor.setlocation(str_sensor_loc);
                           // sensor.setUid( Integer.parseInt(str_uid));
                            sensorList.add(sensor);
                            mAdapter.notifyDataSetChanged();
                        }


                    }






                } catch (JSONException e) {e.printStackTrace(); Toast.makeText(Sensors.this,e.getMessage(),Toast.LENGTH_SHORT).show();}

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sensors.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(req);


// Pull information from the database




    }







    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
