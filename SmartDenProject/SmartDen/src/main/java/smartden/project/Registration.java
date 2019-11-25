package smartden.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText fullname;

    /*Connections DB*/
    RequestQueue rq;
    String insertUrl = "http://boswellkyle.com/ceng319_smartden/register_users.php";

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isNameValid(String name)
    {
        String nameRegex = "[A-Z][a-z]*";

        Pattern pat = Pattern.compile(nameRegex);
        if (name == null)
            return false;
        return pat.matcher(name).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rq = Volley.newRequestQueue(Registration.this);


        TextView tv_reg = (TextView)findViewById(R.id.lnkLogin);
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registration.this, Log_in.class);
                startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                fullname = (EditText)findViewById(R.id.fullname);
                final String name_input = fullname.getText().toString();

                email = (EditText)findViewById(R.id.emailtext);
                final String email_input = email.getText().toString();


                password = (EditText)findViewById(R.id.passwordtext);
                final String password_input = password.getText().toString();


//                if(isNameValid(name_input) == false)
//                {
//                    fullname.requestFocus();
//                    fullname.setError("Invalid Name");
//                }

                 if (isValid(email_input) == false) {
                    email.requestFocus();
                    email.setError("Invalid email");
                }
                else if(password_input.length() <=8)
                {
                    password.requestFocus();
                    password.setError("Must password must contain at least 8 characters");

                }
                else{
                    StringRequest req = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Registration.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", email.getText().toString());
                            params.put("password", password.getText().toString());
                            params.put("fullname", fullname.getText().toString());
                            return params;
                        }
                    };
                    rq.add(req);

                    Intent intent = new Intent(Registration.this, Log_in.class);
                    startActivity(intent);
                }
            }
        });


    }
}
