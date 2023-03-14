package com.rimark.retrofitgetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText login,pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.userid);
        pass=findViewById(R.id.password);

        btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if (login.getText().toString().trim().isEmpty()){
            login.setError("Please enter vallid user id");
            login.requestFocus();
        }else if (pass.getText().toString().trim().isEmpty()){
            pass.setError("Please enter vallid user id");
            pass.requestFocus();
        }else {
            doLogin(login.getText().toString().trim(),
                    pass.getText().toString().trim()
            );
        }
    }

    private void doLogin(String login, String pass) {
        Apis myApi = RetrofitInstanse.getRetrofitInstance().create(Apis.class);
        Call<JsonObject> call = myApi.login("1",login,pass,"2.1");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    String s=response.body().toString();
                    try {
                        Log.e("dlsfif", "onResponse: "+response.body().toString() );
                        JSONObject jsonObject=new JSONObject(s);
                        if (jsonObject.getString("result_status").equalsIgnoreCase("1")){

                            Toast.makeText(getApplicationContext(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), ""+jsonObject.getString("userId"), Toast.LENGTH_LONG).show();


                        }else{
                            Toast.makeText(getApplicationContext(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("shgcfsghc", "onFailure: "+t.getLocalizedMessage() );

            }
        });
    }
}