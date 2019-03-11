package com.example.code_samples.networking;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MemberApi {

    private static final String BASE_URL = "https://dnn32sfzn4.execute-api.us-east-2.amazonaws.com/dev/";
    private static MemberService memberService;
    private static Retrofit retrofit;


    private MemberApi() {
    }

    public static MemberService getInstance() {
        if (memberService != null) {
            return memberService;
        }
        if (retrofit == null) {
            initializeRetrofit();
        }
        memberService = retrofit.create(MemberService.class);
        return memberService;
    }

    private static void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }


}
