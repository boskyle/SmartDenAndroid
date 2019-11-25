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

    EditText username;
    EditText password;


    public static boolean isNameValid(String name)
    {
        String nameRegex = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";

        Pattern pat = Pattern.compile(nameRegex);
        if (name == null)
            return false;
        return pat.matcher(name).matches();
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
                username = (EditText)findViewById(R.id.editText);
                password = (EditText)findViewById(R.id.editText2);
                final String password_input = password.getText().toString();
                final String username_input = username.getText().toString();

                if (isNameValid(username_input) == false) {
                    username.requestFocus();
                    username.setError("Invalid Username");
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
