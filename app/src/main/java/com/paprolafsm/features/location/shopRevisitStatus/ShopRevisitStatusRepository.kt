package com.paprolafsm.features.location.shopRevisitStatus

import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.location.model.ShopDurationRequest
import com.paprolafsm.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}