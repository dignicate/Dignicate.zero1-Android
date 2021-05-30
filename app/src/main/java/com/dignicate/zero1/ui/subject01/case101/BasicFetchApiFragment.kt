package com.dignicate.zero1.ui.subject01.case101

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.databinding.BasicFetchApiFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.bindTo
import com.dignicate.zero1.rx.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers

class BasicFetchApiFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = BasicFetchApiFragment()
    }

    private lateinit var binding: BasicFetchApiFragmentBinding

    private lateinit var viewModel: BasicFetchApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BasicFetchApiFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasicFetchApiViewModel::class.java)
        setupBinding()
        setupUiEvent()
    }

    private fun setupUiEvent() {
        binding.button.setOnClickListener {
            viewModel.didTapFetchButton(1234)
        }
    }

    private fun setupBinding() {
        viewModel.companyNameJP
            .bindTo(binding.basicFetchCompanyNameJPLabel)
            .disposedBy(disposeBag)

        viewModel.companyNameEN
            .bindTo(binding.basicFetchCompanyNameENLabel)
            .disposedBy(disposeBag)

        viewModel.address
            .bindTo(binding.basicFetchAddressLabel)
            .disposedBy(disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}