package com.dignicate.zero1.ui.subject01.case103

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.databinding.BasicFetchApiFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy

class FetchAndSaveDataFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = FetchAndSaveDataFragment()
    }

    private lateinit var binding: BasicFetchApiFragmentBinding

    private lateinit var viewModel: FetchAndSaveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BasicFetchApiFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FetchAndSaveDataViewModel::class.java)
        setupBinding()
        setupUi()
    }

    private fun setupUi() {
        binding.basicFetchApiButton.setOnClickListener {
            viewModel.didTapFetchButton(1234)
        }
    }

    private fun setupBinding() {
        viewModel.companyNameJP
            .bindTo(binding.basicFetchCompanyNameJpLabel)
            .disposedBy(disposeBag)

        viewModel.companyNameEN
            .bindTo(binding.basicFetchCompanyNameEnLabel)
            .disposedBy(disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}