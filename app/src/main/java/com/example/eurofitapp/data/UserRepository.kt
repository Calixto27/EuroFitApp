package com.example.eurofitapp.data

import com.example.eurofitapp.model.UserData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    // Obtener los datos del usuario desde la base de datos
    fun getUserData(): Flow<UserData?> {
        return userDao.getUser()
    }

    // Insertar o actualizar los datos del usuario en la base de datos
    suspend fun updateUserData(age: Int, height: Double, weight: Double, gender: String) {
        val userData = UserData(
            age = age,
            height = height,
            weight = weight,
            gender = gender,
            password = "1234" // Valor por defecto en caso de no existir
        )
        userDao.insertUser(userData)
    }

    // Obtener la contraseña actual
    fun getPassword(): Flow<String> {
        return userDao.getPassword()
    }

    // Actualizar la contraseña
    suspend fun updatePassword(newPassword: String) {
        userDao.updatePassword(newPassword)
    }
}
