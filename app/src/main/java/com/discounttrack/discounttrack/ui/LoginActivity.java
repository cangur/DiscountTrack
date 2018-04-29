package com.discounttrack.discounttrack.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.discounttrack.discounttrack.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvSignUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        initEvent();

    }

    private void initEvent() {
        edtEmail = findViewById( R.id.login_activity_edt_email );
        edtPassword = findViewById( R.id.login_activity_edt_password );

        btnLogin = findViewById( R.id.login_activity_btn_login );
        tvSignUpLink = findViewById( R.id.login_activity_tv_linkSignUp );

        btnLogin.setOnClickListener( this );
        tvSignUpLink.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_btn_login:
                login();
                break;
            case R.id.login_activity_tv_linkSignUp:
                Intent intent = new Intent( this, SignupActivity.class );
                startActivityForResult( intent, REQUEST_SIGNUP );
                overridePendingTransition( R.anim.push_left_in, R.anim.push_left_out );
        }
    }

    private void login() {
        Log.d( TAG, "Login" );

        if (!validate()) {
            onLoginFailed();
        }

        btnLogin.setEnabled( false );

        final ProgressDialog progressDialog = new ProgressDialog( LoginActivity.this, R.style.AppTheme_Dark_Dialog );
        progressDialog.setIndeterminate( true );
        progressDialog.setMessage( "Authenticating..." );
        progressDialog.show();

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000 );
    }

    private void onLoginSuccess() {
        btnLogin.setEnabled( true );
        finish();
    }

    private void onLoginFailed() {
        Toast.makeText( getBaseContext(), "Login failed", Toast.LENGTH_LONG ).show();
        btnLogin.setEnabled( true );
    }

    private boolean validate() {
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            edtEmail.setError( "Enter valid email address!" );
            valid = false;
        } else {
            edtEmail.setError( null );
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError( "Between 4 and 10 alphanumeric characters!" );
            valid = false;
        } else {
            edtEmail.setError( null );
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack( true );
    }
}
