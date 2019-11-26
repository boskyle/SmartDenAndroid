/*Team Name: Humber Elites*/
package smartden.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

public class Light extends AppCompatActivity {



    ToggleButton button2;
    ToggleButton button3;
    ToggleButton button4;
    ToggleButton button5;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("Light");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu1);

        mDrawerLayout = findViewById(R.id.drawer_layout);
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
                                intent = new Intent(Light.this, MainMenu.class);
                                startActivity(intent);
                                break;
                            case R.id.garage:
                                intent = new Intent(Light.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Light.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Light.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.security:
                                intent = new Intent(Light.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Light.this, Sensors.class);
                                startActivity(intent);
                                break;
                            case R.id.log_out:
                                intent = new Intent(Light.this, SplashActivity.class);
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

        button2 = findViewById(R.id.toggleButton2);
        button3 = findViewById(R.id.toggleButton3);
        button4 = findViewById(R.id.toggleButton4);
        button5 = findViewById(R.id.toggleButton5);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button2.setBackgroundColor(getResources().getColor(R.color.green));


                }
                else{

                    button2.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button3.setBackgroundColor(getResources().getColor(R.color.green));


                }
                else{

                    button3.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button4.setBackgroundColor(getResources().getColor(R.color.green));


                }
                else{

                    button4.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button5.setBackgroundColor(getResources().getColor(R.color.green));


                }
                else{

                    button5.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });



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
