package kz.rakym.domain.interaction

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kz.rakym.data.BuildConfig
import kz.rakym.data.api.WeatherApi
import kz.rakym.data.datasource.RemoteWeatherDataSource
import kz.rakym.data.datasource.RemoteWeatherDataSourceImpl
import kz.rakym.data.repository.WeatherRepository
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.service.WeatherService
import kz.rakym.library.logger.MutedLogger
import kz.rakym.testing.data.factory.WeatherDataFactory
import okhttp3.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@ExperimentalCoroutinesApi
class GetDailyCaseTest {

    private val retrofit: Retrofit = provideRetrofit(provideOkHttpClient())
    private val dataSource: RemoteWeatherDataSource =
        RemoteWeatherDataSourceImpl(retrofit.create(WeatherApi::class.java))
    private val logger = MutedLogger()
    private val service: WeatherService = WeatherRepository(logger, dataSource)

    private lateinit var useCase: GetWeatherCase
    private val expectedWeather = WeatherDataFactory.singleWeather()

    @Before
    fun `before each test`() {
        useCase = GetWeatherCase(service, logger)
    }

    @Test
    fun `fahrenheit is not celsius`() {
        runBlocking {
            try {
                service.getWeatherData(TemperatureSystem.FAHRENHEIT, Coordinates(1.0, 1.0), "1")
            } catch (e: Exception) {
                assert(e is IOException)
            }
        }
    }

    @Test
    fun `expected data equals`() {
        runBlocking {
            val data = service.getWeatherData(TemperatureSystem.CELSIUS, Coordinates(1.0, 1.0), "1")
            assert(data == expectedWeather)
        }
    }

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(MockInterceptor())
        .build()

    private fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl("https://api.openweathermap.org/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    class MockInterceptor : Interceptor {


        private val getListOfResponseJson = """
{
    "coord": {
        "lon": 76.881,
        "lat": 43.201
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 26.85,
        "feels_like": 26.85,
        "temp_min": 26.85,
        "temp_max": 26.85,
        "pressure": 1027,
        "humidity": 81,
        "sea_level": 1027,
        "grnd_level": 912
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.8,
        "deg": 161,
        "gust": 2.44
    },
    "clouds": {
        "all": 100
    },
    "dt": 1647804069,
    "sys": {
        "type": 1,
        "id": 8818,
        "country": "KZ",
        "sunrise": 1647824082,
        "sunset": 1647867887
    },
    "timezone": 21600,
    "id": 1524193,
    "name": "Gornyy Gigant",
    "cod": 200
}
"""

        override fun intercept(chain: Interceptor.Chain): Response {
            if (BuildConfig.DEBUG) {
                val uri = chain.request().url().uri().toString()

                if (!uri.endsWith("metric")) {
                    throw IOException()
                }

                return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(getListOfResponseJson)
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            getListOfResponseJson.toByteArray()
                        )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            } else {
                throw IllegalAccessError(
                    "MockInterceptor is only meant for Testing Purposes and " +
                            "bound to be used only with DEBUG mode"
                )
            }
        }

    }

}