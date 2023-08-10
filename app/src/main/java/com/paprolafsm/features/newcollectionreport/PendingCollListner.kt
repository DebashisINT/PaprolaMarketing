package com.paprolafsm.features.newcollectionreport

import com.paprolafsm.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}