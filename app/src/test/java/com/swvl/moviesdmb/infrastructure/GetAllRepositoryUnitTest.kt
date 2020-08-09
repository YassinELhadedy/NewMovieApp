package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.utils.MainCoroutineRule
import com.swvl.moviesdmb.models.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.ParameterizedRobolectricTestRunner

/* TODO : Here will continue parameterized unit test for all repos that implement GetAllRepository<T> using coroutines
to see full sample but using RX2 not Coroutines please see my Github https://github.com/YassinELhadedy/Build-Android-App-Sample1-CompleteCycle/blob/master/app/src/test/java/com/transporter/streetglide/infrastructure/GetAllRepositoryUnitTest.kt

* */
private const val DATA_ERROR = "Unsupported field"

@RunWith(ParameterizedRobolectricTestRunner::class)
class GetAllRepositoryUnitTest {


    companion object {
        @ExperimentalCoroutinesApi
        @get:Rule
        var mainCoroutineRule = MainCoroutineRule()

        @ExperimentalCoroutinesApi
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{index}: {0}")
        fun data(): List<Array<*>> = listOf(
            arrayOf(object :
                SetupTestParameter<Movie> {
                override fun setup(): TestParameter<Movie> {

                    val dmbWebService = Mockito.mock(DmbWebService::class.java)

                    val movies = listOf(
                        Movie(
                            "1",
                            "overview1",
                            "date1",
                            "poster1",
                            "path1",
                            "orgTitle1",
                            "title1", 0.1, "en"
                        ),
                        Movie(
                            "2",
                            "overview2",
                            "date2",
                            "poster2",
                            "path2",
                            "orgTitle2",
                            "title2", 0.2, "ar"
                        )
                    )
                    val filterNormalMap = hashMapOf(1 to movies.subList(0, 1), 2 to emptyList())
                    val filterFaultMap =
                        hashMapOf<Int, Throwable>(3 to RuntimeException(DATA_ERROR))


                    return object :
                        TestParameter<Movie> {
                        override fun getNormalIDs(): Set<Int> {
                            TODO("Not yet implemented")
                        }

                        override fun getAllWithNormalIDs(id: Int): Triple<List<Movie>, Int, List<Movie>> {
                            TODO("Not yet implemented")
                        }

                        override fun getFaultyIDs(): Set<Int> {
                            TODO("Not yet implemented")
                        }

                        override fun getAllWithFaultyIDs(id: Int): Triple<List<Movie>, Int, Throwable> {
                            TODO("Not yet implemented")
                        }

                    }
                }

                override fun toString(): String =
                    MoviesRepository::class.java.simpleName
            })
        )
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    interface TestParameter<out T> {
        fun getNormalIDs(): Set<Int>
        fun getAllWithNormalIDs(id: Int): Triple<List<T>, Int, List<T>>
        fun getFaultyIDs(): Set<Int>
        fun getAllWithFaultyIDs(id: Int): Triple<List<T>, Int, Throwable>
    }

    interface SetupTestParameter<out T> {
        fun setup(): TestParameter<T>
    }
}