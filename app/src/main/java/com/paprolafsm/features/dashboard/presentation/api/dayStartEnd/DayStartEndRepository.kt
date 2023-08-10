package com.paprolafsm.features.dashboard.presentation.api.dayStartEnd

import com.paprolafsm.app.Pref
import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.dashboard.presentation.model.DaystartDayendRequest
import com.paprolafsm.features.dashboard.presentation.model.StatusDayStartEnd
import com.paprolafsm.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.paprolafsm.features.stockCompetetorStock.api.AddCompStockApi
import io.reactivex.Observable

class DayStartEndRepository (val apiService: DayStartEndApi){
    fun dayStart(daystartDayendRequest: DaystartDayendRequest): Observable<BaseResponse> {
        return apiService.submitDayStartEnd(daystartDayendRequest)
    }

    fun dayStartEndStatus(date:String): Observable<StatusDayStartEnd> {
        return apiService.statusDayStartEnd(Pref.session_token!!, Pref.user_id!!,date)
    }

    fun daystartendDelete(sessionToken:String,usrID:String,date:String,dayStDel:String,dayEndDel:String): Observable<BaseResponse> {
        return apiService.submitDayStartEndDelApi(sessionToken,usrID,date,dayStDel,dayEndDel)
    }


}