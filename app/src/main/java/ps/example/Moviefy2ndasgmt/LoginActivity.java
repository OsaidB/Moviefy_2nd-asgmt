package ps.example.Moviefy2ndasgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static final String USERS_LIST = "USERS LIST";

    private EditText edtLogEmail;
    private EditText edtLogPassword;
    private CheckBox rmmbrme;


    private Button btnToRegisterAct;
    //////////////////////////////////////////
    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    //////////////////////////////////////////

    private boolean flag = false;

    ///////////////////////////////////The Main Two Important Objects To Use "Shared Preferences":
    private SharedPreferences prefs;            //To Read
    private SharedPreferences.Editor editor;    //To Write
    ///////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();           //just making the hooks

        setupSharedPrefs();             //siting SharedPrefs up (making the app ready to Read/Write)

        checkPrefs();           //Checking if "Remember me" was previously enabled by the user, if yes-> bring the data and fill it

        btnToRegisterAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupViews() { //just making the hooks
        edtLogEmail = findViewById(R.id.edtLogEmail);
        edtLogPassword = findViewById(R.id.edtLogPassword);
        rmmbrme = findViewById(R.id.rmmbrme);

        btnToRegisterAct = findViewById(R.id.btnToRegisterAct);
    }

    private void setupSharedPrefs() {   //siting SharedPrefs up (making the app ready to Read/Write)
//        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs = getSharedPreferences(USERS_LIST, 0);
        editor = prefs.edit();  //to Write
    }

    private void checkPrefs() { //Checking if "Remember me" was previously enabled by the user, if yes-> bring the data and fill it
        flag = prefs.getBoolean(FLAG, false);   //if "FLAG" is true => flag=true // other than that=>flag=false by default

        //flag=true means that last user checked "Remember me" while logging in the last time
        //so we need to log him in now without the need from him to reenter his data
        if (flag) {   //if there are data stored ("Remember me" was previously enabled)
            String mail = prefs.getString(EMAIL, "");
            String password = prefs.getString(PASS, "");


            edtLogEmail.setText(mail);
            edtLogPassword.setText(password);
            rmmbrme.setChecked(true);
        }
    }

    ///////////////////////////////////Last Thing: the process of logging in
    public void btnLoginOnClick(View view) {//AFTER pressing on Login
        String mail = edtLogEmail.getText().toString();
        String password = edtLogPassword.getText().toString();

        //After entering the user his email and password
        //when he press login

        //we check his data
        //if valid      ==> We check if he checked "remember me" before pressing "login"
        //if yes    ->save his data in sharedPrefs to future login  && Log him in
        //if no     ->just log him in

        //if not valid  ==> reset all & Toast( reEnter data )

        //getting existing registered users info
        for (User u : RegisterActivity.users) {
            if (u.getEmail().equals(mail) && u.getPassword().equals(password)) {
                if (rmmbrme.isChecked()) {
                    if (!flag) {//
                        //saving user's info for future login
                        editor.putString(EMAIL, mail);
                        editor.putString(PASS, password);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                    }
                }

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }

    }


    @Override
    protected void onStart() {

        loadUsers();

        //creating the arrayList of users, and making the needed ArrayAdapter to contain it
//        users = new ArrayList<>();


        super.onStart();
    }

    private void loadUsers() {

//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();

        String json = prefs.getString(USERS_LIST, null);

        //getting the type of users arrayList
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();

        //saving data to users arrayList, after converting it from json using gson
        RegisterActivity.users = gson.fromJson(json, type);//main line

        //checking if DueTasks arrayList is not declared to make one
        if (RegisterActivity.users == null) {
            RegisterActivity.users = new ArrayList<>();
        }
//        DueTasksAdapter.notifyDataSetChanged();
        Toast.makeText(this, "users arrayList filled from Shared preferences. ", Toast.LENGTH_SHORT).show();

    }
}