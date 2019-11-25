package smartden.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Log_in extends AppCompatActivity {

    EditText email;
    EditText password;

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
        setContentView(R.layout.activity_log_in);

        TextView tv_reg = (TextView)findViewById(R.id.lnkRegister);
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Log_in.this, Registration.class);
                startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email= (EditText)findViewById(R.id.editText);
                password = (EditText)findViewById(R.id.editText2);
                final String password_input = password.getText().toString();
                final String email_input = email.getText().toString();

                if (isValid(email_input) == false) {
                    email.requestFocus();
                    email.setError("Invalid Username");
                }
                else if(password_input.length() <=8)
                {
                    password.requestFocus();
                    password.setError("Invalid password");

                }
                else {
                    Intent intent = new Intent(Log_in.this, MainMenu.class);

                    startActivity(intent);
                }



         //// This code below is for testing, when you are done testing, delete the code below and replace it with code above ( the one I commented out)
//                Intent intent = new Intent(Log_in.this, MainMenu.class);
//                startActivity(intent);
        //// This is for testing only



            }
        });



    }
}
