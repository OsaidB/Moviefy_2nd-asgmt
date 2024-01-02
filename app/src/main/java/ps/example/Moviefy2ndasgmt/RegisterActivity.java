package ps.example.Moviefy2ndasgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    static ArrayList<User> users;

    private EditText edtRegEmail;
    private EditText edtDateOfBirth;
    private EditText edtPhone;
    private EditText edtRegPassword;

    private RadioGroup radioGroupGender;
    //=================================================================
    private Button btnCancel;
    //////////////////////////////////////////
    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";


    public static final String PHONE = "PHONE";
    public static final String BIRTH = "BIRTH";

    public static final String GENDER = "GENDER";
    //////////////////////////////////////////
    ///////////////////////////////////The Main Two Important Objects To Use "Shared Preferences":
    private SharedPreferences prefs;            //To Read
    private SharedPreferences.Editor editor;    //To Write
    ///////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupViews();           //just making the hooks

        setupSharedPrefs();             //siting SharedPrefs up (making the app ready to Read/Write)


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                resetAll( );


                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);









            }
        });
    }

    private void setupViews() { //just making the hooks

        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        edtPhone = findViewById(R.id.edtPhone);

        edtRegPassword = findViewById(R.id.edtRegPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        btnCancel = findViewById(R.id.btnCancel);
    }

    private void setupSharedPrefs() {   //siting SharedPrefs up (making the app ready to Read/Write)
        prefs = getSharedPreferences(LoginActivity.USERS_LIST, 0);
        editor = prefs.edit();  //to Write
    }

//
//    @Override
//    protected void onPause() {
//        saveUsers();
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        saveUsers();
//        super.onStop();
//    }

    public void btnRegisterOnClick(View view) {
        String mail = edtRegEmail.getText().toString();
        String dateOfBirth = edtDateOfBirth.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtRegPassword.getText().toString();

        RadioButton chkdBTN = findViewById(radioGroupGender.getCheckedRadioButtonId());
        String gender = chkdBTN.getText().toString();









        if (mail.length() > 1 && password.length() > 1) {

            User user=new User(mail,dateOfBirth,phone,password,gender);

            users.add(user);



//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            Gson gson = new Gson();

            //getting data from gson and storing it in a string.
            String json = gson.toJson(users);

            //saving data in sharedPrefs
            editor.putString(LoginActivity.USERS_LIST, json);//main line

            editor.commit();

            Toast.makeText(this, "Saved users arrayList to Shared preferences.", Toast.LENGTH_SHORT).show();








//            editor.putString(EMAIL, mail);
//
//            editor.putString(BIRTH, dateOfBirth);
//            editor.putString(PHONE, phone);
//
//            editor.putString(PASS, password);
//            editor.putString(GENDER, gender);
//
//            editor.commit();
            Toast.makeText(this, "User Registered Successfully.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Email and password are required fields", Toast.LENGTH_SHORT).show();
        }

    }

    public void resetAll( ) {
        edtRegEmail.setText("");
        edtDateOfBirth.setText("");
        edtPhone.setText("");

        edtRegPassword.setText("");

    }



//
//    public void saveUsers() {
//
//    }
}