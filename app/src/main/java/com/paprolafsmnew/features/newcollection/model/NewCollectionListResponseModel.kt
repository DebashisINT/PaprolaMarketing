package com.paprolafsmnew.features.newcollection.model

import com.paprolafsmnew.app.domain.CollectionDetailsEntity
import com.paprolafsmnew.base.BaseResponse
import com.paprolafsmnew.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}