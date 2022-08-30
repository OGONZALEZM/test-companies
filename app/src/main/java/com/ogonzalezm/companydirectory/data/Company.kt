package com.ogonzalezm.companydirectory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "address")
    val address: String?,

    @ColumnInfo(name = "nit")
    val nit: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,

)
