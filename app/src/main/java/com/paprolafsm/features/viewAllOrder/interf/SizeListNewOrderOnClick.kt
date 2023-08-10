package com.paprolafsm.features.viewAllOrder.interf

import com.paprolafsm.app.domain.NewOrderProductEntity
import com.paprolafsm.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}