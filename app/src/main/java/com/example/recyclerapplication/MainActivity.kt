package com.example.recyclerapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  adapter: CarsAdapter
    private lateinit var model: ItemModel
    private var cars = mutableListOf<ItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        addData()
    }

    fun init(){

        adapter = CarsAdapter(cars,object: CarsOnClickListener {
            override fun carsOnLongClick(position: Int) {
                cars.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            override fun carsOnClick(position: Int) {
                model = cars[position]
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                intent.putExtra("carType", model.carType)
                intent.putExtra("status", model.status)
                intent.putExtra("profile", (model.CarPhoto?:R.drawable.ic_error))
                startActivity(intent)
            }
        })
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

    }

    private fun addData(){
        val status = "Status:"
        val type = "Type:"
        cars.add(ItemModel("$type Coupe", "$status sale", R.drawable.ic_car_1))
        cars.add(ItemModel("$type Sedan", "$status sold", R.drawable.ic_car_2))
        cars.add(ItemModel("$type HatchBack", "$status sale", R.drawable.ic_car_3))
        cars.add(ItemModel("$type coupe", "$status sale"))
        cars.add(ItemModel("$type coupe", "$status sale", R.drawable.ic_car_4))
        cars.add(ItemModel("$type SUV", "$status sale", R.drawable.ic_car_5))
        cars.add(ItemModel("$type Sedan", "$status sold"))
        cars.add(ItemModel("$type SUV", "$status sold", R.drawable.ic_car_6))
        cars.add(ItemModel("$type HatchBack", "$status sale", R.drawable.ic_car_7))
        cars.add(ItemModel("$type coupe", "$status sale"))
        cars.add(ItemModel("$type HatchBack", "$status sale", R.drawable.ic_car_8))
        cars.add(ItemModel("$type coupe", "$status sale"))
        adapter.notifyDataSetChanged()
    }
}