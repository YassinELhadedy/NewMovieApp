package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.infrastructure.dto.LocalMovieResult
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.LocalMovie
import com.swvl.moviesdmb.models.Pagination
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream


class LocalMovieRepository(
    private val inputStream: InputStream
) : GetAllRepository<LocalMovie> {
    override suspend fun getAll(pagination: Pagination): List<LocalMovie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<LocalMovie> = fetchLocalMovie().movies
        .sortedWith(compareBy({ -it.year }, { -it.rating }))
        .groupBy { movie -> movie.year }
        .flatMap { map ->
            map.value.first().keyDate = map.key.toString()
            map.value
        }


    private fun fetchLocalMovie(): LocalMovieResult {
        val movies = mutableListOf<LocalMovie>()
        kotlin.runCatching {
            val jsonObject = JSONObject(parseJSONData())
            val jsonArray = jsonObject.getJSONArray("movies")
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val title = item.getString("title")
                val year = item.getInt("year")
                val cast = parseListOfString(item.getJSONArray("cast"))
                val genres = parseListOfString(item.getJSONArray("genres"))
                val rating = item.getInt("rating")
                movies.add(LocalMovie(title, year, cast, genres, rating))
            }

        }.getOrElse { ex ->
            ex.printStackTrace()
        }
        return LocalMovieResult(movies)
    }

    private fun parseListOfString(list: JSONArray): List<String> {
        val newList = mutableListOf<String>()
        for (i in 0 until list.length()) {
            val item = list.getString(i)
            newList.add(item)
        }
        return newList
    }

    private fun parseJSONData(): String {
        val JsonString: String?
        JsonString = kotlin.runCatching {
            val sizeOfJSONFile: Int = inputStream.available()
            val bytes = ByteArray(sizeOfJSONFile)
            inputStream.read(bytes)
            inputStream.close()
            String(bytes, Charsets.UTF_8)
        }.getOrDefault("")

        return JsonString
    }
}