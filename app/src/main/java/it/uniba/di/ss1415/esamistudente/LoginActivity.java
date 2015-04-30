package it.uniba.di.ss1415.esamistudente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {


    Button loginButton, registerButton;
    EditText idET, passwordET;
    CheckBox checkBox;
    SharedPreferences impostazioni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idET = (EditText) findViewById(R.id.editText);
        passwordET = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        impostazioni = getSharedPreferences("esami", Context.MODE_PRIVATE);

        String defaultID = impostazioni.getString("ID", "");
        String defaultPW = impostazioni.getString("PW", "");
        idET.setText(defaultID);
        passwordET.setText(defaultPW);

        boolean toBeChecked = defaultID != "" && defaultPW != "";
        checkBox.setChecked(toBeChecked);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idET.getText().toString();
                String pw = passwordET.getText().toString();
                DatabaseConnector databaseConnector = new DatabaseConnector(LoginActivity.this);
                if (databaseConnector.login(id, pw)){
                    if (checkBox.isChecked()){
                        SharedPreferences.Editor editor = impostazioni.edit();
                        editor.putString("ID", id);
                        editor.putString("PW", pw);
                        editor.apply();
                    }
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                    //Toast.makeText(LoginActivity.this, "Complimenti ti sei loggato", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Dati errati", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
