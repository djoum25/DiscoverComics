package com.project.djoum.discovercomics.activities;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding mBinding;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        
        mBinding.btnRegister.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mBinding.inputEmail.setError(null);
                mBinding.inputPassword.setError(null);
                mBinding.inputConfirmPassword.setError(null);
                
                View focusView = null;
                boolean cancel = false;
                String email = mBinding.inputEmail.getText().toString();
                String password = mBinding.inputPassword.getText().toString();
                String passwordConfirm = mBinding.inputConfirmPassword.getText().toString();
                
                if (TextUtils.isEmpty(email)) {
                    mBinding.inputEmail.setError("Email Field is required");
                    focusView = mBinding.inputEmail;
                    cancel = true;
                }
                if (!isEmailValid(email)) {
                    mBinding.inputEmail.setError("Please enter a valid email");
                    focusView = mBinding.inputEmail;
                    cancel = true;
                }
                if (TextUtils.isEmpty(password)) {
                    mBinding.inputPassword.setError("Password Field is required");
                    focusView = mBinding.inputPassword;
                    cancel = true;
                }
                if (!isPasswordValid(password)) {
                    mBinding.inputPassword.setError("Password Field is required");
                    focusView = mBinding.inputPassword;
                    cancel = true;
                }
                if (TextUtils.isEmpty(passwordConfirm)) {
                    mBinding.inputConfirmPassword.setError("Please confirm your password");
                    focusView = mBinding.inputConfirmPassword;
                    cancel = true;
                }
                if (!doStringMatch(password, passwordConfirm)) {
                    mBinding.inputConfirmPassword.setError("");
                    mBinding.inputPassword.setError("");
                    cancel = true;
                }
                if (cancel) {
                    if (focusView != null) {
                        focusView.requestFocus();
                    }
                } else {
                    getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    attempRegister(mBinding.inputEmail.getText().toString(),
                            mBinding.inputPassword.getText().toString());
                }
            }
        });
    }
    
    @Override
    protected void onStart() {
        super.onStart();
    }
    
    private void attempRegister(String email, String password) {
        if (mAuth.getCurrentUser() != null) return;
        mBinding.progressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "attempRegister: trying to create the user");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            confirmRegistration();
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "authentification failed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
    
    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this,
                                        "Verification sent successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        "verification email not sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    
    private boolean doStringMatch(String string1, String string2) {
        return string1.equals(string2);
    }
    
    private void hideTheKeyboard() {
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }
    
    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
    
    public void confirmRegistration() {
        mBinding.progressBar.setVisibility(View.GONE);
        new AlertDialog.Builder(this)
                .setMessage("Please confirm the registration in your email to continue")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendVerificationEmail();
                        mAuth.signOut();
                        finish();
                    }
                }).create().show();
    }
}
