package com.example.sandbox.member_list;

import android.util.Log;

import com.example.sandbox.model.Member;
import com.example.sandbox.networking.MemberApi;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private final MutableLiveData<List<Member>> members = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private Call<List<Member>> memberCall;

    public ListViewModel() {
        fetchMembers();
    }


    LiveData<List<Member>> getMembers() {
        return members;
    }

    LiveData<Boolean> getError() {
        return loadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchMembers() {
        loading.setValue(true);
        memberCall = MemberApi.getInstance().getMembers();

        memberCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                loadError.setValue(false);
                members.setValue(response.body());
                loading.setValue(false);
                memberCall = null;
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading members", t);
                loadError.setValue(true);
                loading.setValue(false);
                memberCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {

        if (memberCall != null) {
            memberCall.cancel();
            memberCall = null;
        }
    }
}
