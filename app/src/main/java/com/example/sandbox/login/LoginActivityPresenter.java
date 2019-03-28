package com.example.sandbox.login;

public class LoginActivityPresenter {

    private ILoginActivity view;

    public void setView(ILoginActivity view) {
        this.view = view;
        initializeViews();
    }

    private void initializeViews() {
        view.findViews();
        view.addViewListeners();
    }

    public void submitLoginData(String userId, String password) {
        if (!isValidUser(userId)) {
            view.displayInvalidUserId();
        } else if (!isValidPassword(password)) {
            view.displayInvalidPassword();
        }else{
            view.loginSuccess();
        }
    }

    private boolean isValidUser(String userId) {
        return isValidText(userId) && userId.equals("guest");
    }

    private boolean isValidPassword(String password) {
        return isValidText(password) && password.equals("test");
    }

    private boolean isValidText(String text) {
        return text != null && !text.isEmpty();
    }


}
