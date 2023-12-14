package com.paprolafsmnew.features.viewAllOrder.interf

import com.paprolafsmnew.app.domain.NewOrderGenderEntity
import com.paprolafsmnew.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}