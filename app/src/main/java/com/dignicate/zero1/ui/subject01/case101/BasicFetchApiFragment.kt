package com.dignicate.zero1.ui.subject01.case101

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dignicate.zero1.R
import com.dignicate.zero1.databinding.BasicFetchApiFragmentBinding
import com.dignicate.zero1.domain.subject01.case101.BasicFetchApiUseCase
import com.dignicate.zero1.infra.mock.subject01.SimpleCompanyInfoRepositoryMock
import com.dignicate.zero1.rx.DisposeBag

class BasicFetchApiFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = BasicFetchApiFragment()
    }

    private lateinit var binding: BasicFetchApiFragmentBinding

    private lateinit var viewModel: BasicFetchApiViewModel

    private val useCase = BasicFetchApiUseCase(
        disposeBag,
        repository = SimpleCompanyInfoRepositoryMock()
    )

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
    }

    private fun setupBinding() {
        binding.button.setOnClickListener {
            useCase.fetch(1234)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}