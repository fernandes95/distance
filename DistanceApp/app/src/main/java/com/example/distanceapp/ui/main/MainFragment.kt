package com.example.distanceapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.distanceapp.LocationTrack
import com.example.distanceapp.R
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {

    private var latitudeTv : TextView? = null
    private var longitudeTv : TextView? = null
    private var refreshBtn : MaterialButton? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latitudeTv = view.findViewById(R.id.latti);
        longitudeTv = view.findViewById(R.id.longi);
        refreshBtn = view.findViewById(R.id.refresh);

        refreshBtn?.setOnClickListener {
            updateLocation()
        }

        //Get Location for first create
        updateLocation()
    }

    private fun updateLocation() {
        if (LocationTrack(requireContext()).canGetLocation) {

            latitudeTv?.text = "Latitude:" + LocationTrack(requireContext()).getLatitude().toString()
            longitudeTv?.text = "Longitude:" + LocationTrack(requireContext()).getLongitude().toString()
        }
    }
}