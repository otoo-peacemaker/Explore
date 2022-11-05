package com.peacecodetech.countries.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.peacecodetech.countries.data.repository.CountryRepository
import com.peacecodetech.countries.databinding.FragmentHomeBinding
import com.peacecodetech.countries.network.createApiService
import com.peacecodetech.countries.viewmodel.CountryAdapter
import com.peacecodetech.countries.viewmodel.CountryLoadingAdapter
import com.peacecodetech.countries.viewmodel.CountryViewModel
import com.peacecodetech.countries.viewmodel.CountryViewModelFactory
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val countryAdapter by lazy { CountryAdapter() }

    private val apiService = createApiService()
    private val countryViewModel: CountryViewModel by viewModels {
        CountryViewModelFactory(
            repository = CountryRepository(apiService)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel.getCountries()
        setUpRecView()
        viewModelObserver()

        /* binding.buttonFirst.setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/
    }


    private fun viewModelObserver(){
        lifecycleScope.launch {
            countryViewModel.countryData
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { data ->
                    if (data != null) {

                        countryAdapter.submitData(data)
                        Log.d("HomeFragment","${countryAdapter.submitData(data)}")
                    }
                }
        }
    }

    private fun setUpRecView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countryAdapter
            adapter = countryAdapter.withLoadStateHeaderAndFooter(
                header = CountryLoadingAdapter { countryAdapter.retry() },
                footer = CountryLoadingAdapter { countryAdapter.retry() }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}