package com.alcorp.projectacademyapp.data.source.local.entity

import androidx.room.ColumnInfo

data class ContentEntity (
    @ColumnInfo(name = "content")
    var content: String?
)