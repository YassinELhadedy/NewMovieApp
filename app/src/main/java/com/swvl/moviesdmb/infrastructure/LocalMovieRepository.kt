package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.LocalMovie
import com.swvl.moviesdmb.models.Pagination
import java.io.IOException
import java.io.InputStream


class LocalMovieRepository(
    val inputStream: InputStream
) : GetAllRepository<LocalMovie> {
    override suspend fun getAll(pagination: Pagination): List<LocalMovie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<LocalMovie> {
        TODO("Not yet implemented")
    }

    fun parseJSONData(): String? {
        val JsonString: String?
        JsonString = try {
            val sizeOfJSONFile: Int = inputStream.available()
            val bytes = ByteArray(sizeOfJSONFile)
            inputStream.read(bytes)
            inputStream.close()
            String(bytes, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return JsonString
    }
}