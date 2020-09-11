import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


public class ApiClient {

    val BASE_URL: String = "https://gadsapi.herokuapp.com/api/"
    val GoogleDoc_URL: String = "https://docs.google.com/"

    val TIMEOUT_MINUTES = 1L


    lateinit var apiInterface: ApiInterface

    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
        .readTimeout(TIMEOUT_MINUTES, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_MINUTES, TimeUnit.SECONDS)
        .build()

    fun create(): ApiInterface {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface
    }


    fun createGoogleDoc(): ApiInterface {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(GoogleDoc_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface
    }
}