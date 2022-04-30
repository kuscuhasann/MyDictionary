package com.pakt_games.mydictionary.ui.view

import android.app.Activity
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.pakt_games.mydictionary.R
import com.pakt_games.mydictionary.base.BaseFragment
import com.pakt_games.mydictionary.databinding.FragmentMyDictionaryBinding
import com.pakt_games.mydictionary.db.DictionaryWords
import com.pakt_games.mydictionary.di.dbModule
import com.pakt_games.mydictionary.di.myDictionaryRepositoryModule
import com.pakt_games.mydictionary.di.myDictionaryViewModel
import com.pakt_games.mydictionary.ui.recyclerviewitems.MyDictionaryAdapter
import com.pakt_games.mydictionary.ui.viewmodel.MyDictionaryViewModel
import com.pakt_games.mydictionary.util.showToast
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


class MyDictionaryFragment : BaseFragment<MyDictionaryViewModel, FragmentMyDictionaryBinding>() {

    override fun getLayoutID() = R.layout.fragment_my_dictionary

    override val viewModel: MyDictionaryViewModel by viewModel()


    override fun layoutActions() {
        dbEvents()
        closeProject()
        bindRecyclerViewAndDatabase()

        when (AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.getDefaultNightMode() -> {
                requireActivity().setTheme(R.style.Theme_Dark)
            }
            else -> {
                requireActivity().setTheme(R.style.Theme_Light)
            }
        }

        dataBinding.switchDarkMode.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            when {
                isChecked -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    stopKoin()
                }
                else -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        })
    }

    private fun bindRecyclerViewAndDatabase() {
        viewModel.getWordsDataInSQL()
        lifecycleScope.launchWhenStarted {
            viewModel.readAllDataDB.collect {
                dataBinding.recyclerViewMyDictionaryWords.adapter = MyDictionaryAdapter(it)
                with(dataBinding) {
                    executePendingBindings()
                }
            }
        }

    }

    private fun closeProject() {
        dataBinding.buttonExit.setOnClickListener {
            System.exit(-1)
        }
    }

    private fun dbEvents() {
        lifecycleScope.launchWhenCreated {
            dataBinding.appCompatButtonAddWord.setOnClickListener {
                addWordsToDb()
                bindRecyclerViewAndDatabase()
            }
        }
    }

    private fun addWordsToDb() {
        val dictionaryWords = DictionaryWords(
            0,
            dataBinding.editTextKey.text.toString(),
            dataBinding.editTextValue.text.toString()
        )
        viewModel.insertWordsToDB(dictionaryWords)
        showToast("Kelimeleriniz Başarı ile kayıt edilmiştir.")
        dataBinding.editTextKey.text?.clear()
        dataBinding.editTextValue.text?.clear()
    }

    override fun dependecyInversion() {
        startKoin {
            androidContext(requireActivity())
            modules(myDictionaryViewModel, myDictionaryRepositoryModule, dbModule)
        }
    }


}