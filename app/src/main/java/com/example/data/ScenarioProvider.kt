package com.example.data

import com.example.model.ScenarioData

object ScenarioProvider {
    private val allScenarios: List<ScenarioData> by lazy {
        ScenariosChapter1to4.getList() +
        ScenariosChapter5to7.getList() +
        ScenariosChapter8to10.getList()
    }

    fun getAll(): List<ScenarioData> = allScenarios

    fun getById(id: Int): ScenarioData? = allScenarios.find { it.scenarioId == id }

    fun getByChapter(chapter: Int): List<ScenarioData> = allScenarios.filter { it.chapter == chapter }

    val totalCount: Int
        get() = allScenarios.size
}
