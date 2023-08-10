package com.paprolafsm.features.stockAddCurrentStock.api

import com.paprolafsm.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.paprolafsm.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}