package com.example.code_samples.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.code_samples.MainActivity;
import com.example.code_samples.R;
import com.example.code_samples.utils.UiUtil;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    private TextInputLayout loginPasswordTextInputLayout;
    private TextInputLayout loginUserIdTextInputLayout;
    private EditText loginPasswordEditText;
    private EditText loginUserIdEditText;

    private LoginActivityPresenter presenter;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        presenter = new LoginActivityPresenter();
        presenter.setView(this);
    }

    public void findViews() {
        loginUserIdEditText = findViewById(R.id.loginUserIdEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        loginUserIdTextInputLayout = findViewById(R.id.userid_text_input_layout);
        loginPasswordTextInputLayout = findViewById(R.id.password_text_input_layout);
        loginButton = findViewById(R.id.loginButton);
    }

    public void addViewListeners() {

        loginPasswordEditText.setOnEditorActionListener((view, actionId, event) -> {
            if ((event != null
                    && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                    || (actionId == EditorInfo.IME_ACTION_DONE)) {
                submitLogin();
            }
            return false;
        });

        loginButton.setOnClickListener(v -> submitLogin());

    }

    private void submitLogin(){
        loginUserIdTextInputLayout.setErrorEnabled(false);
        loginPasswordTextInputLayout.setErrorEnabled(false);
        presenter.submitLoginData(loginUserIdEditText.getText().toString(),
                loginPasswordEditText.getText().toString());
    }

    public void displayInvalidUserId() {
        loginUserIdTextInputLayout.setErrorEnabled(true);
        loginUserIdTextInputLayout.setError(getString(R.string.msg_invalid_userid));
        UiUtil.shakeEditText(loginUserIdEditText);
    }

    public void displayInvalidPassword() {
        loginPasswordTextInputLayout.setErrorEnabled(true);
        loginPasswordTextInputLayout.setError(getString(R.string.msg_invalid_password));
        UiUtil.shakeEditText(loginPasswordEditText);
    }

    public void loginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
