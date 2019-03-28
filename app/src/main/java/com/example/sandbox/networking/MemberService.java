package com.example.sandbox.networking;

import com.example.sandbox.model.Member;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MemberService {

    @GET("first-api-test")
    Call<List<Member>> getMembers();

}
