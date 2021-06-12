package com.dignicate.zero1.ui.subject01.case103

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.databinding.FetchAndSaveDataFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindEnabledTo
import com.dignicate.zero1.rx.RxExtensions.bindTextTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy

class FetchAndSaveDataFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = FetchAndSaveDataFragment()
    }

    private lateinit var binding: FetchAndSaveDataFragmentBinding

    private lateinit var viewModel: FetchAndSaveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FetchAndSaveDataFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FetchAndSaveDataViewModel::class.java)
        viewModel.onActivityCreated(requireContext().getSharedPreferences("Data", Context.MODE_PRIVATE))
        setupBinding()
        setupUi()
    }

    private fun setupUi() {
        binding.fetchAndSaveDataFetchButton.setOnClickListener {
            viewModel.didTapFetchButton(1234)
        }
    }

    private fun setupBinding() {
        viewModel.companyNameJP
            .bindTextTo(binding.fetchAndSaveDataNameJpLabel)
            .disposedBy(disposeBag)

        viewModel.companyNameEN
            .bindTextTo(binding.fetchAndSaveDataNameEnLabel)
            .disposedBy(disposeBag)

        viewModel.lastUpdated
            .bindTextTo(binding.fetchAndSaveDataLastUpdatedLabel)
            .disposedBy(disposeBag)

        viewModel.shouldEnableClearButton
            .bindEnabledTo(binding.fetchAndSaveDataClearButton)
            .disposedBy(disposeBag)

        viewModel.shouldEnableFetchButton
            .bindEnabledTo(binding.fetchAndSaveDataFetchButton)
            .disposedBy(disposeBag)

        viewModel.dataState
            .bindTextTo(binding.fetchAndSaveDataDataState)
            .disposedBy(disposeBag)

        viewModel.processState
            .bindTextTo(binding.fetchAndSaveDataProcessState)
            .disposedBy(disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}