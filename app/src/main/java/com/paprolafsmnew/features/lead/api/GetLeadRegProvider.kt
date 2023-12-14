package com.paprolafsmnew.features.lead.api

import com.paprolafsmnew.features.NewQuotation.api.GetQuotListRegRepository
import com.paprolafsmnew.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}