package com.ogonzalezm.companydirectory.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ogonzalezm.companydirectory.data.Company

@Dao
interface CompanyDAO {

    @Query("SELECT * FROM company")
    fun getAll(): List<Company>

    @Query("SELECT * FROM company WHERE id = :id LIMIT 1")
    fun getCompany(id: Int): Company

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun create(company: Company)

    @Query("DELETE FROM company WHERE id = :id")
    fun deleteCompany(id: Int)

}