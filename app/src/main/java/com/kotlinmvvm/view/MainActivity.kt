package com.kotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinmvvm.R
import com.kotlinmvvm.databinding.ActivityMainBinding
import com.kotlinmvvm.viewmodel.CelebsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: CelebsViewModel
    val adapterCelebs = CelebsAdapter(arrayListOf())
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(CelebsViewModel::class.java)
        //required when context is needed
//        viewmodel.refresh(this)
        viewmodel.refresh()
        binding.recCelebs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCelebs
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewmodel.celebsdetails.observe(this, Observer { celebslist ->
            celebslist.let {
                adapterCelebs.updateCelebsList(celebslist)
                binding.linLoader.visibility = GONE
                binding.recCelebs.visibility = VISIBLE
            }
        })

        viewmodel.loading.observe(this, Observer { loading ->
            loading.let {
                if (it) {
                    binding.linLoader.visibility = VISIBLE
                    binding.recCelebs.visibility = GONE
                }
            }
        })

        viewmodel.celebsLoadError.observe(this, Observer { error ->
            error.let {
                if (it) {
                    binding.linLoader.visibility = GONE
                    binding.recCelebs.visibility = GONE
                }
            }
        })

    }
}