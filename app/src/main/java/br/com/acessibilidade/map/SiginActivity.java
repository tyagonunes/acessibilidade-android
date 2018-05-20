package br.com.acessibilidade.map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import br.com.acessibilidade.map.network.EndpointClient;
import br.com.acessibilidade.map.network.Response;
import br.com.acessibilidade.map.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;

public class SiginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button btnSignin;

    private String email;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        emailInput = findViewById(R.id.signin_email);
        passwordInput = findViewById(R.id.signin_password);
        confirmPasswordInput = findViewById(R.id.signin_confirm_password);
        btnSignin = findViewById(R.id.signin_button);



        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                confirmPassword = confirmPasswordInput.getText().toString();

                validateForm();
            }
        });
    }

    public void validateForm() {
        Toast toast;
        Context context = getApplicationContext();

        if(email == null || email.trim().equals("")) {

            toast = Toast.makeText( context,"O campo email é obrigatório", Toast.LENGTH_SHORT);
            toast.show();

        } else if (password == null || password.trim().equals("")) {

            toast = Toast.makeText( context,"O campo senha é obrigatório", Toast.LENGTH_SHORT);
            toast.show();

        } else if (!password.equals(confirmPassword)) {
            toast = Toast.makeText( context,"As senhas não conferem", Toast.LENGTH_SHORT);
            toast.show();
        } else {
           sendUser();
        }
    }

    public void sendUser() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Cadastrar");
        progress.setMessage("Enviando cadastro...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        EndpointClient endpointClient = ServiceGenerator
                .createService(EndpointClient.class, null);

        HashMap<String, Object> data = new HashMap<>();
        data.put("nome", email);
        data.put("senha", password);

        Call<Response<String>> call = endpointClient.sigin(data);
        call.enqueue(new Callback<Response<String>>() {
            @Override
            public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {
                progress.dismiss();
                Toast toast;

                if(response.isSuccessful()) {
//                    Log.d("", "isSuccessful" + response.body());
                    progress.dismiss();

                    if(response.code() == 200) {
                        if( response.body().isSuccess()) {

                            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intentLogin);
//                            finish();

                        } else {
                            toast = Toast.makeText( getApplicationContext(),response.body().getMessage(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<String>> call, Throwable t) {
                progress.dismiss();
                Toast toast = Toast.makeText( getApplicationContext(),"Erro de resposta do servidor", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

}
