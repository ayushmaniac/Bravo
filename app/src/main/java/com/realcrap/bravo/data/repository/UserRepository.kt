package com.realcrap.bravo.data.repository

import com.realcrap.bravo.data.local.prefs.UserPreferences
import com.realcrap.bravo.data.model.User
import com.realcrap.bravo.data.remote.NetworkService
import com.realcrap.bravo.data.remote.response.*
import com.realcrap.bravo.data.remote.response.list.OrderListResponse
import com.realcrap.bravo.data.remote.response.servicelist.ServiceResponse
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.home.homesalons.HomeSalons
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
        private val networkService: NetworkService,
        private val userPreferences: UserPreferences
) {

    fun saveNewUser(user: RegistrationResponse) {

        userPreferences.setUserEmail(user.email)
        userPreferences.setUserName(user.name)
        userPreferences.saveNewUserMobile(user.mobile)
        userPreferences.setAccessToken(user.usersid)

    }

    fun saveCurrentUser(user: LoginResponse) {
        userPreferences.setUserStatus(user.status)
        userPreferences.setUserText(user.text)
        userPreferences.setAccessToken(user.usersid)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeUserEmail()
        userPreferences.removeAccessToken()
    }


    fun saveUserLoc(location : String){
        userPreferences.setUserLoc(location)

    }

    fun getUserLoc() = userPreferences.getUserLoc()



    fun getCurrentUser(): User? {

        val userStatus = userPreferences.getUserStatus();
        val userId = userPreferences.getAccessToken()
        val userText = userPreferences.getUserText()

        return if ( userStatus != null && userId != null && userText!=null)
            User(userStatus, userText, userId)
        else
            null
    }

    fun doUserLogin(email: String, password: String): Single<LoginResponse> =
            networkService.doLogin("login", email, password).map {

                        LoginResponse(
                                it.status,
                                it.text,
                                it.usersid

                        )
            }

    fun createAccount(name: String, email: String,mobile: String, password: String): Single<RegistrationResponse> =

        networkService.doRegistration("registration", name, email, mobile, password).map {

            RegistrationResponse(
                    it.status,
                    it.text,
                    it.usersid,
                    it.name,
                    it.email,
                    it.mobile

            )
        }


    fun getAllMerchantList(city : String) : Single<List<Salon>> =
            networkService.getAllMerchants(userPreferences.getAccessToken().toString(), "merchantlist", city)
                    .map {it.data}

    fun getAllHomeMerchantList(city : String) : Single<List<HomeSalons>> =
            networkService.getAllHomeMerchants(userPreferences.getAccessToken().toString(), "promotions", city)
                    .map { it.data}



    fun getAllService(merchantId : String) : Single<ServiceResponse> =
            networkService.getMerchantService(userPreferences.getAccessToken().toString(), "servicelist", merchantId)
                    .map {ServiceResponse(
                            it.status,
                            it.merchants,
                            it.servicesData

                    )}


    fun getUserDetails():Single<UserDetailsResponse.Users> =
            networkService.getUserDetails(userPreferences.getAccessToken().toString(), "users")
                    .map  {
                        it.users
                    }


    fun checkOutCash(merchantId: String, serviceId : String, orderDate : String, orderTime: String) : Single<GeneralResponse> =
            networkService.doCashPayment(userPreferences.getAccessToken().toString(), "cash", merchantId, serviceId, orderDate, orderTime)
                    .map {
                        GeneralResponse(
                                it.status,
                                it.text
                        )
                    }


    fun applyCoupon(merchantId : String, couponCode : String, amount : String) : Single<CouponResponse> =
            networkService.applyCoupon(userPreferences.getAccessToken().toString(), "apply-coupon", merchantId, couponCode,amount)
                    .map {
                        CouponResponse(
                                it.status,
                                it.message,
                                it.coupanamt,
                                it.totalamt,
                                it.savingamt
                        )
                    }


    fun getOrderList() : Single<OrderListResponse> =
            networkService.getOrderList(userPreferences.getAccessToken().toString(), "orderlist")
                    .map {
                        OrderListResponse(
                                it.orders,
                                it.status)
                    }

    fun removeCoupon(amount : String) : Single<CouponResponse> =
            networkService.removeCoupon(userPreferences.getAccessToken().toString(), "cancel-coupon",amount)
                    .map {
                        CouponResponse(
                                it.status,
                                it.message,
                                it.coupanamt,
                                it.totalamt,
                                it.savingamt
                        )
                    }

    fun updateProfile(name : String, email : String, mobile : String) =
            networkService.updateProfile(userPreferences.getAccessToken().toString(), "updation", name, email, mobile)
                    .map {
                        GeneralResponse(
                                it.status,
                                it.text
                        )
                    }


    }


