package com.paprolafsmnew.features.location.api

import com.paprolafsmnew.features.location.shopdurationapi.ShopDurationApi
import com.paprolafsmnew.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}