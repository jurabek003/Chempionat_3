package com.turgunboyevjurabek.chempionat3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.turgunboyevjurabek.chempionat3.adabter.AdabterRv
import com.turgunboyevjurabek.chempionat3.databinding.ActivityMainBinding
import com.turgunboyevjurabek.chempionat3.db.DataBaseRomm
import com.turgunboyevjurabek.chempionat3.models.NetworkHelper
import com.turgunboyevjurabek.chempionat3.models.Valyuta
import com.turgunboyevjurabek.chempionat3.models.netvork.ApiClinet
import com.turgunboyevjurabek.chempionat3.models.netvork.ApiServis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var apiSevis:ApiServis
    private lateinit var adapterrv:AdabterRv
    private lateinit var dataBaseRomm: DataBaseRomm
    lateinit var list: ArrayList<Valyuta>
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list=ArrayList()


        dataBaseRomm=DataBaseRomm.newInstens(this)


        val chekked= NetworkHelper(this)

        if (chekked.isNetworkConnected()) {
            network()
            Toast.makeText(this, "Connection ", Toast.LENGTH_SHORT)
                .show()

        } else {
            if(!dataBaseRomm.abstrakDao().getAll().equals(null)){
                adapterrv= AdabterRv(dataBaseRomm.abstrakDao().getAll() as ArrayList<Valyuta>)
                binding.rvadaptr.adapter=adapterrv
                adapterrv.notifyDataSetChanged()

                binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val queryy=query?:""
                        adapterrv.list.clear()
                        val list = dataBaseRomm.abstrakDao().searchQuery(queryy)
                        adapterrv.list.addAll(list)
                        adapterrv.notifyDataSetChanged()

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val queryy=newText?:""
                        adapterrv.list.clear()
                        val list = dataBaseRomm.abstrakDao().searchQuery(queryy)
                        adapterrv.list.addAll(list)
                        adapterrv.notifyDataSetChanged()

                        return true
                    }

                })

            }
            Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun network() {
        apiSevis=ApiClinet.getRetrofitServis(this)
        apiSevis.getData().enqueue(object : Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful && response.body()!=null){
                    val list2=response.body()
                    list.addAll(list2!!)
                    adapterrv=AdabterRv(list)
                    binding.rvadaptr.adapter=adapterrv
                    adapterrv.notifyDataSetChanged()

                    binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            val array=ArrayList<Valyuta>()
                            for (i in 0 until list2.size ){
                                if (list2[i].title.contains(query!!)){
                                    adapterrv.list.clear()
                                    array.addAll(listOf(list2[i]))
                                    adapterrv=AdabterRv(array)
                                    binding.rvadaptr.adapter=adapterrv
                                    adapterrv.notifyDataSetChanged()
                                }
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            TODO("Not yet implemented")
                        }

                    })


                }

            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {
                Log.d("@getData", "onFailure: ${t.message}")
            }
        })
    }

    override fun onStop() {
        super.onStop()
        dataBaseRomm.abstrakDao().insert(list)

    }
}