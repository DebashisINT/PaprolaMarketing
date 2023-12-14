package com.paprolafsmnew.features.location.shopRevisitStatus

import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.location.model.ShopDurationRequest
import com.paprolafsmnew.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}