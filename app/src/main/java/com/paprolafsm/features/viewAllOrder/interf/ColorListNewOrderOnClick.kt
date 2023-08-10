package com.paprolafsm.features.viewAllOrder.interf

import com.paprolafsm.app.domain.NewOrderColorEntity
import com.paprolafsm.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}