package com.example.codersmania;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText inpPassword;
    EditText inpEmail;
    TextView forgetButton;
    TextView registerButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inpPassword = findViewById(R.id.editTextTextPassword);
        inpEmail = findViewById(R.id.editTextTextEmailAddress);
        forgetButton = findViewById(R.id.buttonForget);
        registerButton = findViewById(R.id.textRegister);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    loginCall();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

    public boolean validateInput() {
        String email = inpEmail.getText().toString();
        String password = inpPassword.getText().toString();

        return !email.isEmpty() && password.length() >= 6;
    }

    public void loginCall() {
        String email = inpEmail.getText().toString();
        String password = inpPassword.getText().toString();

        // Create login request object
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Make the API call using Retrofit
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        // Navigate to HomeActivity on successful login
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Optional: finish the login activity
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed: Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed: Invalid response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle error
                Toast.makeText(LoginActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
