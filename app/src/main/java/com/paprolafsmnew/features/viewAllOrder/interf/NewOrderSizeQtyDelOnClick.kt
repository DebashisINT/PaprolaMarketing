package com.paprolafsmnew.features.viewAllOrder.interf

import com.paprolafsmnew.app.domain.NewOrderGenderEntity
import com.paprolafsmnew.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}