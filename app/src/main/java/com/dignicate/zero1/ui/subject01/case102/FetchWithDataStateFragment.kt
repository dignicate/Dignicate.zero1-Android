package com.dignicate.zero1.ui.subject01.case102

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.R
import com.dignicate.zero1.databinding.FetchWithDataStateFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTextTo
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.bindVisibilityTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy

class FetchWithDataStateFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = FetchWithDataStateFragment()
    }

    private lateinit var binding: FetchWithDataStateFragmentBinding

    private lateinit var viewModel: FetchWithDataStateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FetchWithDataStateFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FetchWithDataStateViewModel::class.java)
        setupBinding()
        setupUi()
    }

    private fun setupUi() {
        binding.apply {
            fetchWithDataStateButton.setOnClickListener {
                viewModel.didTapFetchButton(1234)
            }
        }
    }

    private fun setupBinding() {
        viewModel.companyNameJP
            .bindTextTo(binding.fetchWithDataStateCompanyNameJpLabel)
            .disposedBy(disposeBag)

        viewModel.companyNameEN
            .bindTextTo(binding.fetchWithDataStateCompanyNameEnLabel)
            .disposedBy(disposeBag)

        viewModel.address
            .bindTextTo(binding.fetchWithDataStateAddressLabel)
            .disposedBy(disposeBag)

        viewModel.foundationDate
            .bindTextTo(binding.fetchWithDataStateFoundationDateLabel)
            .disposedBy(disposeBag)

        viewModel.capital
            .bindTextTo(binding.fetchWithDataStateCapitalLabel)
            .disposedBy(disposeBag)

        viewModel.numberOfEmployees
            .bindTextTo(binding.fetchWithDataStateNumberOfEmployeeLabel)
            .disposedBy(disposeBag)

        viewModel.visibilityOfProgress
            .bindVisibilityTo(binding.fetchWithDataStateProgress)
            .disposedBy(disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}