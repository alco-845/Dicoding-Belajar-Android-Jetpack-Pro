package com.alcorp.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tventities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean,

    @ColumnInfo(name = "first_air_date")
    var first_air_date: String,

    @ColumnInfo(name = "poster_path")
    var poster_path: String
)