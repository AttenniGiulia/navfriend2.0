package com.example.lsg.navfriend20.navfriend;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import com.example.lsg.navfriend20.navfriend.async.LogInConnection;
import com.example.lsg.navfriend20.navfriend.data.User;
import com.nightfall.navfriend.R;


public class Main extends ActionBarActivity {


private  EditText emailText;
private  EditText pwdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.submit);
        emailText = (EditText) findViewById(R.id.email) ;
        pwdText = (EditText) findViewById(R.id.pwd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(emailText);
                System.out.println(pwdText);

                String email = emailText.getText().toString();
                String pwd = pwdText.getText().toString();

                User utente=new User(email,pwd);

                LogInConnection connection = new LogInConnection(utente, Main.this);
                connection.execute();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
