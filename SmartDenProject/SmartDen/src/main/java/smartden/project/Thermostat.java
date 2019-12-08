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
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Random;

public class Thermostat extends AppCompatActivity {
    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            final TextView tv_simulate = (TextView) findViewById(R.id.temp_simulate);
            final TextView tv2_simulate = (TextView) findViewById(R.id.hum_simulate);
            Random rand;
            Random rand2;
            int rnd;
            int rnd2;

            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.." + e);
                }

                rand = new Random();
                rand2 = new Random();
                rnd = rand.nextInt(30);
                rnd += 25;
                rnd2 = rand2.nextInt(60);
                rnd2 += 45;
                final String rnd_string = Integer.toString(rnd);
                final String rnd_string2 = Integer.toString(rnd2);

                tv_simulate.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_simulate.setText(rnd_string + "°C");
                        tv2_simulate.setText(rnd_string2 + "%");
                    }
                });

            }
        }
    };

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setTitle("Thermostat");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu1);

        Thread myThread = new Thread(myRunnable);
        myThread.start();

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
                                intent = new Intent(Thermostat.this, MainMenu.class);
                                startActivity(intent);
                                break;

                            case R.id.garage:
                                intent = new Intent(Thermostat.this, Garage.class);
                                startActivity(intent);
                                break;
                            case R.id.thermostat:
                                intent = new Intent(Thermostat.this, Thermostat.class);
                                startActivity(intent);
                                break;
                            case R.id.light:

                                intent = new Intent(Thermostat.this, Light.class);
                                startActivity(intent);
                                break;
                            case R.id.list_sensor:
                                intent = new Intent(Thermostat.this, Sensors.class);
                                startActivity(intent);
                                break;

                            case R.id.security:
                                intent = new Intent(Thermostat.this, Security.class);
                                startActivity(intent);
                                break;
                            case R.id.log_out:
                                intent = new Intent(Thermostat.this, SplashActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.setting:
                                intent = new Intent(Thermostat.this, Setting.class);
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
