package com.example.blueberry.spider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class register extends ActionBarActivity {
    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputFullName;
    EditText inputEmail;
    EditText inputPassword;
    TextView registerErrorMsg;
    EditText inputCity;
    EditText inputArea;
    EditText inputStreet;
    EditText inputHouse;
    EditText inputCnic;


    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this, "BSsnrA9fZtQ6rRoXx8ppE7jQbkX9CgCn7JW993PB", "7VE81U3NyIMrAWrthh0MEvk6Iupv2qR0XejhKoec");

        //SQLiteDatabase db = new DatabaseHandlerClass(this);
        // db.onCreate(null);
        // Importing all assets like buttons, text fields
        inputFullName = (EditText) findViewById(R.id.registerName);
        inputEmail = (EditText) findViewById(R.id.registerEmail);
        inputPassword = (EditText) findViewById(R.id.registerPassword);
        inputCity = (EditText) findViewById(R.id.registercity);
        inputArea = (EditText) findViewById(R.id.registerArea);
        inputStreet = (EditText) findViewById(R.id.registerStreet);
        inputHouse = (EditText) findViewById(R.id.registerHouse);
        inputCnic = (EditText) findViewById(R.id.registerCNIC);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
        db=openOrCreateDatabase("Spider", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS pasha(name VARCHAR,email VARCHAR,password VARCHAR,city VARCHAR,area VARCHAR,street VARCHAR,house VARCHAR,cnic VARCHAR);");

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String city = inputFullName.getText().toString();
                String area = inputEmail.getText().toString();
                String street = inputPassword.getText().toString();
                String house = inputFullName.getText().toString();
                String cnic = inputEmail.getText().toString();
                if(inputFullName.getText().toString().trim().length()==0||
                        inputEmail.getText().toString().trim().length()==0||
                        inputPassword.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }
                db.execSQL("INSERT INTO pasha VALUES('"+inputFullName.getText()+"','"+inputEmail.getText()+
                        "','"+inputPassword.getText()+
                        "','"+inputCity.getText()+"','"+inputArea.getText()+
                        "','"+inputStreet.getText()+"','"+inputHouse.getText()+"','"+inputCnic.getText()+"');");
                showMessage("Success", "Record added");
                clearText();

                //******************************Parse//**********************
                // create the new user!
                setProgressBarIndeterminateVisibility(true);
                ParseUser data = new ParseUser();
                data.put("objectId", "sarwan");
                data.put("Full_Name", name);
                data.put("createdAT", "Feb 19, 2015, 12:07");
                data.put("updatedAT", "Feb 19, 2015, 12:07");
                data.put("ACL", "Public Read and Write");
                data.put("Email", email);
                data.put("Password", password);
                data.put("City", city);
                data.put("Area", area);
                data.put("Street_number", street);
                data.put("House_Number", house);
                data.put("CNIC",cnic);

//                data.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        setProgressBarIndeterminateVisibility(false);
//                        if (e == null) {
//                            // Success!
//                            Intent intent = new Intent(register.this,MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                        } else {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
//                            builder.setMessage(e.getMessage()).setTitle(R.string.signup_error_title)
//                                    .setPositiveButton(android.R.string.ok,
//                                            null);
//                            AlertDialog dialog = builder.create();
//                            dialog.show();
//                        }
//                    }
//                });
                data.pinInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
//                            tcouples.getText().clear();
//                            tchild.getText().clear();
//                            tchild9.getText().clear();
//                            tchild5.getText().clear();
                            Toast.makeText(getApplicationContext(), "submitiing done", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "error occur in submission" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //******************************Parse//**********************

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                // Close Registration View
                finish();
            }
        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        inputFullName.setText("");
        inputEmail.setText("");
        inputPassword.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
