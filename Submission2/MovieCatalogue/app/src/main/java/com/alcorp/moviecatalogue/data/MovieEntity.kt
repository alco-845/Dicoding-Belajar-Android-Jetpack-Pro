package com.alcorp.moviecatalogue.data

data class MovieEntity(
    var movieId: String,
    var title: String,
    var synopsis: String,
    var yearRelease: String,
    var imagePath: String
)