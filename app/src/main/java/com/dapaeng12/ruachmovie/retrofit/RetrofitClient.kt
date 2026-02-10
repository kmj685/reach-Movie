package com.dapaeng12.ruachmovie.retrofit


import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.dapaeng12.ruachmovie.MyApp
import com.dapaeng12.ruachmovie.utils.Constants.TAG
import com.dapaeng12.ruachmovie.utils.SplashAPI
import com.dapaeng12.ruachmovie.utils.isJsonArray
import com.dapaeng12.ruachmovie.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Handler

object RetrofitClient {
    // 레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null
    //    private lateinit var retrofitClient: Retrofit

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit?{
        Log.d(TAG, "RetrofitClient - getClient called")

        // 로깅 인터셉터 추가
        // okhttp 인스턴스 생성(메모리에 올림)
        val client = OkHttpClient.Builder()

        // 로그를 찍기 위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(TAG, "RetrofitClient - log called / message: $message")

            when {
                message.isJsonObject() -> Log.d(TAG, JSONObject(message).toString(4))
                message.isJsonArray() -> Log.d(TAG, JSONObject(message).toString(4))
                else -> {
                    try {
                        Log.d(TAG, JSONObject(message).toString(4))
                    } catch (e: Exception) {
                        Log.d(TAG, message)
                    }
                }
            }
        }

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)

        // 기본 파라메터 인터셉터 설정
        val baseParameterInterceptor : Interceptor = (Interceptor { chain ->
            Log.d(TAG, "RetrofitClient - intercept called")
            // 오리지날 리퀘스트
            val originalRequest = chain.request()

            //쿼리 파라메터 추가하기
            val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id",
                SplashAPI.CLIENT_ID).build()
            val finalRequest = originalRequest.newBuilder().url(addedUrl).method(originalRequest.method, originalRequest.body).build()

//            chain.proceed(finalRequest)
            val response = chain.proceed(finalRequest)

            if (response.code != 200) {

                android.os.Handler(Looper.getMainLooper()).post{
                    Toast.makeText(MyApp.appContext, "${response.code} 에러 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            return@Interceptor response
        })

        // 위에서 설정한 기본 파라메터 인터셉터를 okhttp 클라이언트를 추가한다.
        client.addInterceptor(baseParameterInterceptor)

        // 커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if (retrofitClient == null){

            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                //위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .client(client.build())
                .build()

        }
        return retrofitClient
    }
}