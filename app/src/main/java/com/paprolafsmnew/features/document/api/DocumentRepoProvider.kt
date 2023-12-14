package com.paprolafsmnew.features.document.api

import com.paprolafsmnew.features.dymanicSection.api.DynamicApi
import com.paprolafsmnew.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}