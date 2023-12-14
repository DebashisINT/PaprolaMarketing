package com.paprolafsmnew.features.nearbyuserlist.api

import com.paprolafsmnew.app.Pref
import com.paprolafsmnew.features.nearbyuserlist.model.NearbyUserResponseModel
import com.paprolafsmnew.features.newcollection.model.NewCollectionListResponseModel
import com.paprolafsmnew.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}