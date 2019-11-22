package smartden.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText fullname;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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


                if(name_input.length() == 0)
                {
                    fullname.requestFocus();
                    fullname.setError("Invalid Name");
                }
                else if (isValid(email_input) == false) {
                    email.requestFocus();
                    email.setError("Invalid email");
                }
                else if(password_input.length() <=8)
                {
                    password.requestFocus();
                    password.setError("Must password must contain at least 8 characters");

                }
                else{

                    Intent intent = new Intent(Registration.this, Log_in.class);
                    startActivity(intent);
                }
            }
        });


    }
}
