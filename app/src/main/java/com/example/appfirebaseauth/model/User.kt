package com.example.appfirebaseauth.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Users")
 data class User (
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="idUser")
    var idUser:Long,

    @ColumnInfo(name = "firstName")
    @NonNull
    var firstName: String,

    @ColumnInfo(name = "lastName")
    @NonNull
    var lastName: String,

    @ColumnInfo(name = "email")
    @NonNull
    var email: String,

    @ColumnInfo(name = "phone")
    @NonNull
    var phone: String,

    @ColumnInfo(name = "dateOfBirth")
    @NonNull
    var dateOfBirth: String,

    @ColumnInfo(name = "gender")
    @NonNull
    var gender: String,

    @ColumnInfo(name = "city")
    @NonNull
    var city: String,

    @ColumnInfo(name = "address")
    @NonNull
    var address: String

)