package com.paprolafsm.features.nearbyuserlist.api

import com.paprolafsm.app.Pref
import com.paprolafsm.features.nearbyuserlist.model.NearbyUserResponseModel
import com.paprolafsm.features.newcollection.model.NewCollectionListResponseModel
import com.paprolafsm.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}