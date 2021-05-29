package com.dignicate.zero1.ui.subject01.case101

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.R

class BasicFetchApiFragment : Fragment() {

    companion object {
        fun newInstance() = BasicFetchApiFragment()
    }

    private lateinit var viewModel: BasicFetchApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.basic_fetch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasicFetchApiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}