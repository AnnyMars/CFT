package com.example.cfttest


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cfttest.data.api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://lookup.binlist.net"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        enter_btn.setOnClickListener {
            val numb = edit_text.text.toString()
            getInfo(numb)
        }
//        getInfo(numb)



//        binding.enterBtn.setOnClickListener {
//            val numb = binding.editText.text.toString()
//            parseJson(numb)
//
//        }



    }

    private fun getInfo(numbers:String){
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BASIC }
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)

        lifecycleScope.launch{
            val response = api.getCardInfo(numbers)
            if (response.isSuccessful){
                val data = response.body()!!
                withContext(Dispatchers.Main){
                    scheme_value.text = data.scheme

                }
            }
        }
    }

//    fun parseJson(numb:String) {
//        val url = "https://lookup.binlist.net/$numb"
//        val request = Request.Builder()
//            .url(url)
//            .build()
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object: Callback{
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body?.string()
//                println(body)
//
//                val gson = GsonBuilder().create()
//
//                val cardInfo = gson.fromJson(body, CardInfo::class.java)
//            }
//            override fun onFailure(call: Call, e: IOException) {
//                println("Error")
//            }
//        })
//    }


}