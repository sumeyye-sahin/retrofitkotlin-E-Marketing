package com.sumeyyesahin.retrofitkotlintekrartekrar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sumeyyesahin.retrofitkotlintekrartekrar.adapter.RvAdapter
import com.sumeyyesahin.retrofitkotlintekrartekrar.databinding.ActivityMainBinding
import com.sumeyyesahin.retrofitkotlintekrartekrar.models.Product
import com.sumeyyesahin.retrofitkotlintekrartekrar.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch (Dispatchers.IO) {

            val response = try {
                RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                Toast.makeText(applicationContext,"app error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }
            if(response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                     val productList: List<Product> = response.body()!!.products
                    binding.apply {
                        progressBar.visibility = View.GONE
                        rvAdapter = RvAdapter(productList)
                        recyclerView.adapter = rvAdapter
                        recyclerView.layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)

                    }
                }
        }}
    }
}