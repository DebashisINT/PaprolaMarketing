package com.paprolafsm.features.dashboard.presentation.api.dayStartEnd

import com.paprolafsm.features.stockCompetetorStock.api.AddCompStockApi
import com.paprolafsm.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}