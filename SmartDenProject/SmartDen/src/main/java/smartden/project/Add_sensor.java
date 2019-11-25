package smartden.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Add_sensor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    /*Spinner*/

    Spinner spinner;
    EditText code;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBar actionbar = getSupportActionBar();
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
                            case R.id.add_sensor:
                                Toast.makeText(Add_sensor.this,"You are already in this activity silly!",Toast.LENGTH_SHORT).show();
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




        /*code et*/

        code = findViewById(R.id.et_sensor_code);





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
                       break;

                   case 1:
                       code.setHint("HC-SR04 Code");
                       break;
                   case 2:
                       code.setHint("RGB Code");
                       break;
                   case 3:
                       code.setHint("MOTOR Code");
                       break;
               }


        }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
