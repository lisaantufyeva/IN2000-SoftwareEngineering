package com.example.team31.ui.overview.week_overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.team31.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OverviewFragment : Fragment(), OverviewContract.View {


    private lateinit var presenter: OverviewPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {

        val root: View = inflater.inflate(R.layout.overview_fragment, container, false)
        presenter = OverviewPresenter(root.context, OverviewModel())

        CoroutineScope(Dispatchers.Main).launch {
            val response = presenter.getForecastList()
            displayWeatherList(root, response)

        }

        return root
    }

     fun displayWeatherList(root: View, list: List<RefinedForecast>){
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = OverviewAdapter(list, root.context)
    }




    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

