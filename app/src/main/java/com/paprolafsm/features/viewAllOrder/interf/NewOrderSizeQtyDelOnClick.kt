package com.paprolafsm.features.viewAllOrder.interf

import com.paprolafsm.app.domain.NewOrderGenderEntity
import com.paprolafsm.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}