package br.com.acessibilidade.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.acessibilidade.map.models.Local;
import br.com.acessibilidade.map.network.EndpointClient;
import br.com.acessibilidade.map.network.Response;
import br.com.acessibilidade.map.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

   private EditText emailInput;
   private EditText passwordInput;
   private Button btnLogin;

   private String email;
   private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        btnLogin = findViewById(R.id.email_sign_in_button);


        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                password = passwordInput.getText().toString();
                email = emailInput.getText().toString();

                validation();
            }
        });
    }

    public void validation() {
        Toast toast;
        Context context = getApplicationContext();

        if(email == null || email.trim().equals("")) {

            toast = Toast.makeText( context,"O campo email é obrigatório", Toast.LENGTH_SHORT);
            toast.show();

        } else if (password == null || password.trim().equals("")) {

            toast = Toast.makeText( context,"O campo senha é obrigatório", Toast.LENGTH_SHORT);
            toast.show();

        } else  {
            sendLogin();
        }
    }

    public void sendLogin() {

        EndpointClient endpointClient = ServiceGenerator
                .createService(EndpointClient.class, null);

        HashMap<String, Object> data = new HashMap<>();
        data.put("nome", email);
        data.put("senha", password);

        Call<Response<String>> call = endpointClient.login(data);
        call.enqueue(new Callback<Response<String>>() {
            @Override
            public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {

                Toast toast;

                if(response.isSuccessful()) {
                    //Log.d("", "isSuccessful" + response.body());


                    if(response.code() == 200) {
                        if( response.body().isSuccess()) {
                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("logged", true);
                            editor.commit();

                            Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<String>> call, Throwable t) {

                Toast toast = Toast.makeText( getApplicationContext(),"Dados não conferem", Toast.LENGTH_LONG);
                toast.show();

            }
        });



    }
}

