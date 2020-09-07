
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import com.alaaalrayes.gads2020leaderboard.Model.SkillIQModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

public interface ApiInterface {


    @GET("hours")
    fun GetLearningLeaders(): Call<List<LearningLeaderModel>>

    @GET("skilliq")
    fun GetSkillIQ(): Call<List<SkillIQModel>>



    @POST("forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun Submission(@Field("entry.1877115667") firstName:String,
                   @Field("entry.2006916086") lastName:String,
                   @Field("entry.1824927963") email:String ,
                   @Field("entry.284483984") linkProject:String ): Call<Void>




}