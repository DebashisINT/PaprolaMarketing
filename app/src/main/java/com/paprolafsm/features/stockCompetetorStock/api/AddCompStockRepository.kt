package com.paprolafsm.features.stockCompetetorStock.api

import com.paprolafsm.base.BaseResponse
import com.paprolafsm.features.orderList.model.NewOrderListResponseModel
import com.paprolafsm.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.paprolafsm.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}