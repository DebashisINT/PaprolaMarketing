package com.paprolafsm.features.weather.api

import com.paprolafsm.features.task.api.TaskApi
import com.paprolafsm.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}