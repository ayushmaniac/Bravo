package com.realcrap.bravo.data.remote

import com.realcrap.bravo.data.remote.response.GeneralResponse
import com.realcrap.bravo.data.remote.response.LoginResponse
import com.realcrap.bravo.data.remote.response.RegistrationResponse
import com.realcrap.bravo.data.remote.response.UsersIdResponse
import com.realcrap.bravo.data.remote.response.list.MerchantListResponse
import com.realcrap.bravo.data.remote.response.servicelist.ServiceResponse
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


    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun generateOtp(
            @Field("action") action : String,
            @Field("username") username : String
    ): Single<UsersIdResponse>

    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun verifyOtp(
            @Field("action") action : String,
            @Field("usersid") usersid : String,
            @Field("otp") otp : String

            ): Single<UsersIdResponse>


    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun updateNewPassword(
            @Field("action") action : String,
            @Field("usersid") usersid : String,
            @Field("password") password: String

    ): Single<GeneralResponse>


    @FormUrlEncoded
    @POST(Endpoints.LOC)
    fun getAllMerchants(
            @Header("Authorization") auth : String,
            @Field("action") action : String,
            @Field("city") city : String

    ): Single<MerchantListResponse>


    @FormUrlEncoded
    @POST(Endpoints.LOC)
    fun getMerchantService(
            @Header("Authorization") auth : String,
            @Field("action") action : String,
            @Field("merchantid") merchantid : String

    ): Single<ServiceResponse>




}
