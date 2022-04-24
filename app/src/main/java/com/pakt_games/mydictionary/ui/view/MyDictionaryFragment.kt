package com.pakt_games.mydictionary.ui.view

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
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyDictionaryFragment : BaseFragment<MyDictionaryViewModel, FragmentMyDictionaryBinding>() {

    override fun getLayoutID() = R.layout.fragment_my_dictionary

    override val viewModel: MyDictionaryViewModel by viewModel()


    override fun layoutActions() {
        dbEvents()
        closeProject()
        bindRecyclerViewAndDatabase()
    }

    private fun bindRecyclerViewAndDatabase() {
        viewModel.getWordsDataInSQL()
        viewModel.readAllDataDB.observe(this) {
            dataBinding.recyclerViewMyDictionaryWords.adapter = MyDictionaryAdapter(it)
            dataBinding.executePendingBindings()
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