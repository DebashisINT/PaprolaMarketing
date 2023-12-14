package com.paprolafsmnew.features.location.api

import com.paprolafsmnew.app.Pref
import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.location.model.AppInfoInputModel
import com.paprolafsmnew.features.location.model.AppInfoResponseModel
import com.paprolafsmnew.features.location.model.GpsNetInputModel
import com.paprolafsmnew.features.location.model.ShopDurationRequest
import com.paprolafsmnew.features.location.shopdurationapi.ShopDurationApi
import io.reactivex.Observable

/**
 * Created by Saikat on 17-Aug-20.
 */
class LocationRepo(val apiService: LocationApi) {
    fun appInfo(appInfo: AppInfoInputModel?): Observable<BaseResponse> {
        return apiService.submitAppInfo(appInfo)
    }

    fun getAppInfo(): Observable<AppInfoResponseModel> {
        return apiService.getAppInfo(Pref.session_token!!, Pref.user_id!!)
    }

    fun gpsNetInfo(appInfo: GpsNetInputModel?): Observable<BaseResponse> {
        return apiService.submitGpsNetInfo(appInfo)
    }
}