package com.paprolafsm.features.photoReg.present

import com.paprolafsm.app.domain.ProspectEntity
import com.paprolafsm.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}