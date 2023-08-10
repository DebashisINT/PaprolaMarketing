package com.paprolafsm.features.location.shopRevisitStatus

import com.paprolafsm.features.location.shopdurationapi.ShopDurationApi
import com.paprolafsm.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}