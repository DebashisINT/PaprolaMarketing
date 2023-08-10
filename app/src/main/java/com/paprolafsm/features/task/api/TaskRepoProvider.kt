package com.paprolafsm.features.task.api

import com.paprolafsm.features.timesheet.api.TimeSheetApi
import com.paprolafsm.features.timesheet.api.TimeSheetRepo

/**
 * Created by Saikat on 12-Aug-20.
 */
object TaskRepoProvider {
    fun taskRepoProvider(): TaskRepo {
        return TaskRepo(TaskApi.create())
    }
}