package com.ogonzalezm.companydirectory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.database.dao.CompanyDAO

@Database(
    entities = [
        Company::class
    ], version = 2
    , exportSchema = false
)
abstract class Database: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "company_directory_db"
    }

    abstract fun companyDAO(): CompanyDAO

}