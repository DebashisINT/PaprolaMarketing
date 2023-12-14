package com.paprolafsmnew.features.weather.api

import com.paprolafsmnew.features.task.api.TaskApi
import com.paprolafsmnew.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}