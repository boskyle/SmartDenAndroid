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

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Sensors extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ArrayList<Sensor_Info>  sensorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SensorAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        mDrawerLayout = findViewById(R.id.drawer_layout);

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



    Sensor_Info sensor = new Sensor_Info();
    sensor.setname("Garage");
    sensor.setlocation("Kitchen");
    sensor.setUid(3);
    sensor.setqrcode("DAWIAWDHIAHWD123");
    sensorList.add(sensor);

        Sensor_Info sensor1 = new Sensor_Info();
        sensor1.setname("Light");
        sensor1.setlocation("BedRoom");
        sensor1.setUid(4);
        sensor1.setqrcode("ADWUYGWDUAWBD");
        sensorList.add(sensor1);

    mAdapter.notifyDataSetChanged();

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
