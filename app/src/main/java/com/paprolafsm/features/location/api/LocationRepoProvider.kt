package com.paprolafsm.features.location.api

import com.paprolafsm.features.location.shopdurationapi.ShopDurationApi
import com.paprolafsm.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}