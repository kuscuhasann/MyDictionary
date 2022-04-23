package com.pakt_games.mydictionary.ui.view

import com.pakt_games.mydictionary.R
import com.pakt_games.mydictionary.base.BaseFragment
import com.pakt_games.mydictionary.databinding.FragmentMyDictionaryBinding
import com.pakt_games.mydictionary.di.myDictionaryViewModel
import com.pakt_games.mydictionary.ui.viewmodel.MyDictionaryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyDictionaryFragment : BaseFragment<MyDictionaryViewModel, FragmentMyDictionaryBinding>() {

    override fun getLayoutID() = R.layout.fragment_my_dictionary

    override val viewModel: MyDictionaryViewModel by viewModel()


    override fun layoutActions() {
        dataBinding.editTextKey.requestFocus()
    }

    override fun dependecyInversion() {
        startKoin {
            androidContext(requireActivity())
            modules(myDictionaryViewModel)
        }
    }


}