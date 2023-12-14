package com.paprolafsmnew.features.stockCompetetorStock.api

import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.orderList.model.NewOrderListResponseModel
import com.paprolafsmnew.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.paprolafsmnew.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}