package com.discounttrack.discounttrack.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.discounttrack.discounttrack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {

    private static final String TAG = "SignupActivity";

    private EditText edtUsername;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtRePassword;
    private EditText edtPhone;
    private Button btnSignup;
    private TextView tvLinkToLogin;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        initEvent();
    }

    private void initEvent() {
        edtUsername = findViewById( R.id.signup_activity_edt_username );
        edtAddress = findViewById( R.id.signup_activity_edt_address );
        edtEmail = findViewById( R.id.signup_activity_edt_email );
        edtPassword = findViewById( R.id.signup_activity_edt_password );
        edtRePassword = findViewById( R.id.signup_activity_edt_rePassword );
        edtPhone = findViewById( R.id.signup_activity_edt_phone );
        tvLinkToLogin = findViewById( R.id.signup_activity_tv_linkLogin );
        btnSignup = findViewById( R.id.signup_activity_btn_signup );
        linearLayout = findViewById( R.id.signup_activity_linearlayout_top );

        tvLinkToLogin.setOnClickListener( this );
        btnSignup.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_activity_btn_signup:
                signUp();
                break;
            case R.id.signup_activity_tv_linkLogin:
                Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
                startActivity( intent );
                finish();
                overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
        }
    }

    private void signUp() {
        Log.d( TAG, "Signup" );

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btnSignup.setEnabled( false );

        progressDialog = new ProgressDialog( SignupActivity.this, R.style.AppTheme_Dark_Dialog_Signup );
        progressDialog.setIndeterminate( true );
        progressDialog.setMessage( "Create Account..." );
        progressDialog.show();

        String name = edtUsername.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String mobile = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        String reEnterPassword = edtRePassword.getText().toString();

        newUserRegistration( email, password );

    }

    private void newUserRegistration(String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener( this );
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {
            onSignupSuccess();
        } else {
            onSignupFailed( task );
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onSignupSuccess() {
        btnSignup.setEnabled( true );
        Toast.makeText( getBaseContext(), "User Registered! ", Toast.LENGTH_LONG ).show();
        progressDialog.dismiss();
        setResult( RESULT_OK, null );
        //finish();
    }

    public void onSignupFailed(Task<AuthResult> task) {
        progressDialog.dismiss();
        Snackbar snackbar = Snackbar.make( linearLayout, "Login failed: " + task.getException().getMessage(), Snackbar.LENGTH_LONG );
        snackbar.show();
        btnSignup.setEnabled( true );
    }

    private void onSignupFailed() {
        Toast.makeText( getBaseContext(), "Login failed: Not validate!", Toast.LENGTH_LONG ).show();

        btnSignup.setEnabled( true );
    }

    public boolean validate() {
        boolean valid = true;

        String name = edtUsername.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String mobile = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        String reEnterPassword = edtRePassword.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            edtUsername.setError( "at least 3 characters" );
            valid = false;
        } else {
            edtUsername.setError( null );
        }

        if (address.isEmpty()) {
            edtAddress.setError( "Enter Valid Address" );
            valid = false;
        } else {
            edtAddress.setError( null );
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            edtEmail.setError( "enter a valid email address" );
            valid = false;
        } else {
            edtEmail.setError( null );
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            edtPhone.setError( "Enter Valid Mobile Number" );
            valid = false;
        } else {
            edtPhone.setError( null );
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError( "between 4 and 10 alphanumeric characters" );
            valid = false;
        } else {
            edtPassword.setError( null );
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals( password ))) {
            edtRePassword.setError( "Password Do not match" );
            valid = false;
        } else {
            edtRePassword.setError( null );
        }

        return valid;
    }


}
