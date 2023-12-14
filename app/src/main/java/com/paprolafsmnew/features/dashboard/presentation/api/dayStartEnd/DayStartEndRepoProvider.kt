package com.paprolafsmnew.features.dashboard.presentation.api.dayStartEnd

import com.paprolafsmnew.features.stockCompetetorStock.api.AddCompStockApi
import com.paprolafsmnew.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}