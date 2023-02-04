package com.example.cfttest


import android.os.Bundle
import android.widget.Toast
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
                    if(data.prepaid == true){
                        prepaid_value.text = "True"
                    }
                    else {
                        prepaid_value.text = "False"
                    }
                    type_value.text = data.type
                    brand_value.text = data.brand
                    number_value_1.text = "Length: " + data.number?.length.toString()
                    number_value_2.text = "Luhn: " + data.number?.luhn.toString()
                    bank_value_1.text = "Name: " + data.bank.name.toString()
                    bank_value_2.text = "URL: " + data.bank.url.toString()
                    bank_value_3.text = "City: " + data.bank.city.toString()
                    country_value_1.text = "Emoji: " + data.country?.emoji.toString()
                    country_value_2.text = "Latitude: " + data.country?.latitude.toString()
                    country_value_3.text = "Longitude: " + data.country?.longitude.toString()
                    country_value_4.text = "Name: " + data.country?.name.toString()

                }
            }
            else{
               Toast.makeText(applicationContext, "REQUEST ERROR", Toast.LENGTH_SHORT).show()

            }
        }
    }
}