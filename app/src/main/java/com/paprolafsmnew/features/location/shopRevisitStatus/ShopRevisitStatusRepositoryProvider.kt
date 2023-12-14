package com.paprolafsmnew.features.location.shopRevisitStatus

import com.paprolafsmnew.features.location.shopdurationapi.ShopDurationApi
import com.paprolafsmnew.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}