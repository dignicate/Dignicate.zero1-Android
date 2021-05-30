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
import com.dignicate.zero1.rx.bindTo
import com.dignicate.zero1.rx.bindVisibilityTo
import com.dignicate.zero1.rx.disposedBy

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
            .bindTo(binding.fetchWithDataStateCompanyNameJpLabel)
            .disposedBy(disposeBag)

        viewModel.companyNameEN
            .bindTo(binding.fetchWithDataStateCompanyNameEnLabel)
            .disposedBy(disposeBag)

        viewModel.address
            .bindTo(binding.fetchWithDataStateAddressLabel)
            .disposedBy(disposeBag)

        viewModel.foundationDate
            .bindTo(binding.fetchWithDataStateFoundationDateLabel)
            .disposedBy(disposeBag)

        viewModel.capital
            .bindTo(binding.fetchWithDataStateCapitalLabel)
            .disposedBy(disposeBag)

        viewModel.numberOfEmployees
            .bindTo(binding.fetchWithDataStateNumberOfEmployeeLabel)
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