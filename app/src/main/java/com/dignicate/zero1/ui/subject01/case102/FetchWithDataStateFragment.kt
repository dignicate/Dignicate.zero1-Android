package com.dignicate.zero1.ui.subject01.case102

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.R

class FetchWithDataStateFragment : Fragment() {

    companion object {
        fun newInstance() = FetchWithDataStateFragment()
    }

    private lateinit var viewModel: FetchWithDataStateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fetch_with_data_state_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FetchWithDataStateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}