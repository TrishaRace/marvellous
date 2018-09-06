package com.example.carrerap.marvellous.model

data class Characters(val data: Data)

data class Data( val count: Int, val results: List<Result>)

data class Thumbnail(val path: String, val extension: String)

data class Urls(val type: String, val url: String)

data class Result(val id: Int, val name: String, val description: String, val modified: String, val thumbnail: Thumbnail, val resourceURI: String, val comics: Comics, val series: Series, val stories: Stories, val events: Events, val urls: List<Urls>)

