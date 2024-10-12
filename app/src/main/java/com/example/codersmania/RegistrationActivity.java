package  com.example.codersmania;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codersmania.ApiClient;
import com.example.codersmania.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText inpEmail, inpPassword, inpPhone,inpConfirmPassword,inpName;
    private CheckBox termsAndCondition;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        inpEmail = findViewById(R.id.editTextEmailAddress);
        inpPassword = findViewById(R.id.editTextPassword);
        inpConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        inpPhone = findViewById(R.id.editTextPhone);
        inpName = findViewById(R.id.editTextName);
        registerButton = findViewById(R.id.buttonRegister);
        termsAndCondition = findViewById(R.id.checkBoxPolicy);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput() == true){
                    registerUser();
                }
            }
        });
    }

    private void registerUser() {
        String email = inpEmail.getText().toString().trim();
        String password = inpPassword.getText().toString().trim();
        String phone = inpPhone.getText().toString().trim();
        String confirmPassword = inpConfirmPassword.getText().toString().trim();
        String name = inpName.getText().toString().trim();

        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setConfirmPassword(confirmPassword);
        user.setName(name);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<UserModel> call = apiService.registerUser(user);



        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(RegistrationActivity.this,LoginActivity.class);
                    RegistrationActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration failed: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("API_CALL", "Error: " + t.getMessage());
                Toast.makeText(RegistrationActivity.this, "Error occurred during registration", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateInput() {
        String email = inpEmail.getText().toString().trim();
        String password = inpPassword.getText().toString().trim();
        String confirmPassword = inpConfirmPassword.getText().toString().trim();
        String phone = inpPhone.getText().toString().trim();

        // Validate email
        if (email.isEmpty()) {
            inpEmail.setError("Email is required");
            inpEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inpEmail.setError("Please enter a valid email address");
            inpEmail.requestFocus();
            return false;
        }

        // Validate password
        if (password.isEmpty()) {
            inpPassword.setError("Password is required");
            inpPassword.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            inpPassword.setError("Password should be at least 6 characters long");
            inpPassword.requestFocus();
            return false;
        }

        // Validate confirm password
        if (!password.equals(confirmPassword)) {
            inpConfirmPassword.setError("Passwords do not match");
            inpConfirmPassword.requestFocus();
            return false;
        }

        // Validate phone number
        if (phone.isEmpty()) {
            inpPhone.setError("Phone number is required");
            inpPhone.requestFocus();
            return false;
        }
        if (!Patterns.PHONE.matcher(phone).matches() || phone.length() != 10) {
            inpPhone.setError("Please enter a valid 10-digit phone number");
            inpPhone.requestFocus();
            return false;
        }

        // Validate terms and conditions
        if (!termsAndCondition.isChecked()) {
            Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_LONG).show();
            return false;
        }

        return true; // All validations passed
    }

}
