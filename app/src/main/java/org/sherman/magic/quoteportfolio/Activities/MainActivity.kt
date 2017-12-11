package org.sherman.magic.quoteportfolio.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.sherman.magic.quoteportfolio.Adapters.QuoteAdapter
import org.sherman.magic.quoteportfolio.Data.DEBUG
import org.sherman.magic.quoteportfolio.Data.QuoteRetriever
import org.sherman.magic.quoteportfolio.Data.ReadQuote
import org.sherman.magic.quoteportfolio.Models.Quote
import org.sherman.magic.quoteportfolio.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var quoteAdapter:QuoteAdapter? = null
    //lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteAdapter = QuoteAdapter()

        //viewPager = findViewById(R.id.viewPager)
        //viewPager.adapter = quoteAdapter

        getQuote(quoteText)
    }

    fun getQuote(view:View) {
        var quote:Quote
        var retriever = QuoteRetriever()

        val callback = object:Callback<ReadQuote> {
            override fun onResponse(call: Call<ReadQuote>?, response: Response<ReadQuote>?) {
                val quote = response?.body()?.quoteText
                val author = response?.body()?.quoteAuthor
                if ((quote!=null)&& (author !=null)){
                    quoteText.text = quote
                    byAuthor.text = author
                }
            }

            override fun onFailure(call: Call<ReadQuote>?, t: Throwable?) {
                Log.d(DEBUG,"Quote API Failed")
            }

        }
        retriever.getQuote(callback)
    }
}
