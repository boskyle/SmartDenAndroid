package smartden.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Garage extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    Button button;
    Button button2;
    Button stop;
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);


        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("Garage");
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
                                intent = new Intent(Garage.this, MainMenu.class);
                                startActivity(intent);
                                break;
                            case R.id.garage:
                                intent = new Intent(Garage.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Garage.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Garage.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.security:
                                intent = new Intent(Garage.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Garage.this, Sensors.class);
                                startActivity(intent);
                                break;
                            case R.id.log_out:
                                intent = new Intent(Garage.this, SplashActivity.class);
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
        state = findViewById(R.id.textView2);
        button = findViewById(R.id.open);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                button.setBackgroundColor(getResources().getColor(R.color.green));
                state.setText("Open");
                state.setTextColor(getResources().getColor(R.color.red));
                button2.setBackgroundResource(android.R.drawable.btn_default);

            }
        });

        button2 = findViewById(R.id.close);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                button2.setBackgroundColor(getResources().getColor(R.color.red));
                state.setText("Close");
                state.setTextColor(getResources().getColor(R.color.red));
                button.setBackgroundResource(android.R.drawable.btn_default);

            }
        });
        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                stop.setBackgroundColor(getResources().getColor(R.color.red));
                state.setText("STOP");
                state.setTextColor(getResources().getColor(R.color.red));

                button.setBackgroundResource(android.R.drawable.btn_default);
                button2.setBackgroundResource(android.R.drawable.btn_default);
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
