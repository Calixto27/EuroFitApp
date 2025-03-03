package com.example.eurofitapp.data

import androidx.room.*
import com.example.eurofitapp.model.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE id = 1")
    fun getUser(): Flow<UserData?> // Obtener datos del usuario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserData)

    @Query("SELECT password FROM user_table WHERE id = 1")
    fun getPassword(): Flow<String> // Obtener la contraseña actual

    @Query("UPDATE user_table SET password = :newPassword WHERE id = 1")
    suspend fun updatePassword(newPassword: String) // Cambiar la contraseña
}
