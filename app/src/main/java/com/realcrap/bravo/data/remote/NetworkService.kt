package com.realcrap.bravo.data.remote

import com.realcrap.bravo.data.remote.response.LoginResponse
import com.realcrap.bravo.data.remote.response.RegistrationResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun doLogin(
            @Field("action") action : String,
            @Field("username") username : String,
            @Field("password") password : String
            ): Single<LoginResponse>


    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun doRegistration(
            @Field("action") action : String,
            @Field("name") name : String,
            @Field("email") email : String,
            @Field("mobile") mobile : String,
            @Field("password") password : String
    ): Single<RegistrationResponse>


}