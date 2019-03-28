package com.example.sandbox;

import com.example.sandbox.login.ILoginActivity;
import com.example.sandbox.login.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class LoginActivityPresenterTest {

    private LoginActivityPresenter presenter;
    private ILoginActivity mockView;

    @Before
    public void setUp() {
        mockView = Mockito.mock(ILoginActivity.class);
        presenter = new LoginActivityPresenter();
        presenter.setView(mockView);
    }

    @Test
    public void should_init_view_inorder_when_setView() {
        presenter.setView(mockView);
        InOrder inOrder = inOrder(mockView);
        inOrder.verify(mockView).findViews();
        inOrder.verify(mockView).addViewListeners();
    }

    @Test
    public void should_call_login_success_when_valid_userid_and_valid_pw() {
        presenter.submitLoginData("guest", "test");
        verify(mockView).loginSuccess();
    }

    @Test
    public void should_displayInvalidUserId_when_empty_userid_and_valid_pw() {
        presenter.submitLoginData("", "test");
        verify(mockView).displayInvalidUserId();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidPassword_when_valid_userid_and_empty_pw() {
        presenter.submitLoginData("guest", "");
        verify(mockView).displayInvalidPassword();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidUserId_when_null_userid_and_valid_pw() {
        presenter.submitLoginData(null, "test");
        verify(mockView).displayInvalidUserId();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidPassword_when_valid_userid_and_null_pw() {
        presenter.submitLoginData("guest", null);
        verify(mockView).displayInvalidPassword();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidUserId_when_null_userid_and_null_pw() {
        presenter.submitLoginData(null, null);
        verify(mockView).displayInvalidUserId();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidUserId_when_invalid_userid_and_valid_pw() {
        presenter.submitLoginData("foo", "test");
        verify(mockView).displayInvalidUserId();
        verify(mockView, never()).displayInvalidPassword();
        verify(mockView, never()).loginSuccess();
    }

    @Test
    public void should_displayInvalidUserId_when_valid_userid_and_invalid_pw() {
        presenter.submitLoginData("guest", "foo");
        verify(mockView).displayInvalidPassword();
        verify(mockView, never()).displayInvalidUserId();
        verify(mockView, never()).loginSuccess();
    }

}