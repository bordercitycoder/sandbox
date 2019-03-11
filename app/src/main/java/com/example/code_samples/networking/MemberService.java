package com.example.code_samples.networking;

import com.example.code_samples.model.Member;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MemberService {

    @GET("first-api-test")
    Call<List<Member>> getMembers();

}
