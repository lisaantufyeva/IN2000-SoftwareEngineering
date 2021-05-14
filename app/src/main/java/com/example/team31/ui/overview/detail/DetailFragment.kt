package com.example.team31.ui.overview.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.team31.AdminActivity
import com.example.team31.Bruker
import com.example.team31.R
import com.example.team31.Varsel
import com.example.team31.databinding.DetailFragmentBinding
import com.example.team31.ui.overview.week_overview.Forecast
import com.example.team31.ui.overview.week_overview.RefinedForecast
import com.example.team31.ui.profile.ProfileViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var forecastObject: RefinedForecast
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
                ViewModelProvider(this).get(DetailViewModel::class.java)
        forecastObject = args.RefinedForecast
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val admin = (activity as AdminActivity?)!!.getUser()
        val userId = (activity as AdminActivity?)!!.getUserId()

        super.onViewCreated(view, savedInstanceState)

        val binding = DetailFragmentBinding.bind(view)
        binding.apply {
            date.text = forecastObject.time
            temp.text = forecastObject.temp
            precipitation.text = forecastObject.precipitation
            extraStaffValue.text = args.extraStaff.toString()
            currentStaffValue.text = admin.normalBemanning
            val currentImageId = context?.resources?.getIdentifier("@drawable/"+forecastObject.symbol, "drawable",
                context?.packageName)
            val currentDrawable = currentImageId?.let { context?.resources?.getDrawable(it) }
            imageView.setImageDrawable(currentDrawable)
        }

        binding.sendMessage.setOnClickListener {
            val alertList = createAlertList(forecastObject.time, args.extraStaff, userId)
            sendMessage(alertList, userId)
            binding.sendMessage.isInvisible = true

        }
    }
    private fun sendMessage(list: MutableList<Varsel>, userId:String){
        Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
        for ( i in list){
            detailViewModel.addAlert(i)
        }
    }

    private fun createAlertList(date: String, extraStaff: Int, userId: String): MutableList<Varsel>{
        return MutableList(extraStaff){Varsel(null,date, false,null,userId)}
    }

}

