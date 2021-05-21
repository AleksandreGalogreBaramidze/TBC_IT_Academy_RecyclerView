package com.example.recyclerapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.databinding.ItemCarDefaultProfileBinding
import com.example.recyclerapplication.databinding.ItemCarPictureProfileBinding

class CarsAdapter(private  val cars: MutableList<ItemModel>, private val carsOnClickListener: CarsOnClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val CAR_DEFAULT_PROFILE = 1
        private const val CAR_PICTURE_PROFILE = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == CAR_DEFAULT_PROFILE){
            CarWithoutPicture(ItemCarDefaultProfileBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }else{
            CarWithPicture(ItemCarPictureProfileBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CarWithPicture -> holder.bind()
            is CarWithoutPicture -> holder.bind()
        }
    }

    override fun getItemCount() = cars.size

    inner class  CarWithPicture(val binding: ItemCarPictureProfileBinding):
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener, View.OnClickListener {
        private lateinit var model: ItemModel
        fun bind() {
            model = cars[adapterPosition]
            binding.imgCarProfile.setImageResource(model.CarPhoto?:R.drawable.ic_error)
            binding.txtCarType.text = model.carType
            binding.txtStatus.text = model.status
            binding.root.setOnLongClickListener(this)
            binding.root.setOnClickListener(this)
        }
        override fun onLongClick(v: View?): Boolean {
            carsOnClickListener.carsOnLongClick(adapterPosition)
            return false
        }

        override fun onClick(v: View?) {
            carsOnClickListener.carsOnClick(adapterPosition)
        }

    }

    inner class CarWithoutPicture(val binding: ItemCarDefaultProfileBinding):
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener, View.OnClickListener{
            private lateinit var model: ItemModel
            fun bind() {
                model = cars[adapterPosition]
                binding.imgCarProfile.setImageResource(R.drawable.ic_error)
                binding.txtCarType.text = model.carType
                binding.txtStatus.text = model.status
                binding.root.setOnLongClickListener(this)
                binding.root.setOnClickListener(this)
            }
        override fun onLongClick(v: View?): Boolean {
            carsOnClickListener.carsOnLongClick(adapterPosition)
            return false
        }
        override fun onClick(v: View?) {
            carsOnClickListener.carsOnClick(adapterPosition)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = cars[position]
        return if(model.CarPhoto == null){
            CAR_DEFAULT_PROFILE
        }else{
            CAR_PICTURE_PROFILE
        }

    }

}