package com.mexiti.cronoapp.repository

import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.room.CronosDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDataBaseDao: CronosDataBaseDao){
    suspend fun addCrono(crono: Cronos)= cronosDataBaseDao.insert(crono)
    suspend fun updateCrono(crono: Cronos)= cronosDataBaseDao.update(crono = crono)
    suspend fun deleteCrono(crono: Cronos)= cronosDataBaseDao.delete(crono = crono)

    fun getAllcronos(): Flow<List<Cronos>> = cronosDataBaseDao
        .getCronos()
        .flowOn(Dispatchers.IO)
        .conflate()

    fun getCronoByID(id:Long): Flow<Cronos> = cronosDataBaseDao
        .getCronosById(id)
        .flowOn(Dispatchers.IO)
        .conflate()
}