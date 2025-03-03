package com.example.eurofitapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eurofitapp.model.TestResult
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TestResult)

    @Query("SELECT * FROM test_results")
    fun getAllResults(): Flow<List<TestResult>> // Cambiado a Flow<List<TestResult>>

    @Query("DELETE FROM test_results")
    suspend fun deleteAllResults() // Agregado método para borrar todos los resultados
}
