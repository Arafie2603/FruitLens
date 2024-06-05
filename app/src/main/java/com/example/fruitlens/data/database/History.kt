package com.example.fruitlens.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class History (
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "username")
    val result: String = "",

    @field:ColumnInfo(name = "fruitUrl")
    val imageUrl: String? = null
) : Parcelable