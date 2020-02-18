package com.rommac.cuefa.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.rommac.cuefa.R

class ProfileFragment : Fragment(),
    ProfileContract.ProfileView {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var presenter: ProfileContract.ProfilePresenterI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider().of(this).get(ProfileViewModel::class.java)
        if(viewModel.presenter == null)
            viewModel.presenter = ProfilePresenter()
        presenter = viewModel.presenter!!

        presenter.attachView(this, lifecycle)
        presenter.viewIsReady()
    }


}
