package org.sherman.magic.quoteportfolio.Data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Admin on 12/11/2017.
 */
interface QuoteAPI {
  @GET("?method=getQuote&format=json&lang=en")
    fun getQuote(): Call<ReadQuote>
}
class ReadQuote(val quoteText:String, val quoteAuthor:String)

class QuoteRetriever {
    val service: QuoteAPI
    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.forismatic.com/api/1.0/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(QuoteAPI::class.java)
    }
    fun getQuote(callback: Callback<ReadQuote>){
        val call = service.getQuote()
        call.enqueue(callback)
    }
}