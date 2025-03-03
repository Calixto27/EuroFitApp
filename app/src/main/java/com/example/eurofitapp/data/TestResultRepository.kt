package com.example.eurofitapp.data

import com.example.eurofitapp.model.TestResult
import kotlinx.coroutines.flow.Flow

class TestResultRepository(private val testResultDao: TestResultDao) {

    fun getAllResults(): Flow<List<TestResult>> = testResultDao.getAllResults()

    suspend fun insertResult(testResult: TestResult) {
        testResultDao.insertResult(testResult)
    }

    suspend fun deleteAllResults() {
        testResultDao.deleteAllResults()
    }
}
