package smartden.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

public class Security extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    ToggleButton button;
    ToggleButton button2;
    ToggleButton button3;
    ToggleButton button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("Security");
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
                            intent = new Intent(Security.this, MainMenu.class);
                            startActivity(intent);
                            break;

                            case R.id.garage:
                                intent = new Intent(Security.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Security.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Security.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.security:
                                intent = new Intent(Security.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Security.this, Sensors.class);
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



        button = findViewById(R.id.toggleButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button.setBackgroundColor(getResources().getColor(R.color.yellow));


                }
                else{

                    button.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });



        button2 = findViewById(R.id.toggleButton6);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button2.setBackgroundColor(getResources().getColor(R.color.yellow));


                }
                else{

                    button2.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });

        button3 = findViewById(R.id.toggleButton7);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button3.setBackgroundColor(getResources().getColor(R.color.yellow));


                }
                else{

                    button3.setBackgroundResource(android.R.drawable.btn_default);


                }



            }
        });


        button4 = findViewById(R.id.toggleButton8);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((ToggleButton)v).isChecked();

                if(checked) {

                    button4.setBackgroundColor(getResources().getColor(R.color.yellow));


                }
                else{

                    button4.setBackgroundResource(android.R.drawable.btn_default);


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
