package br.com.alura.technews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.repository.Resource

class ListaNoticiasViewModel (private val repository : NoticiaRepository) : ViewModel() {


    init {
        Log.i("viewModel", "Criando viewmodel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("viewModel","Destruindo view model...")
    }

    fun buscaTodos() : LiveData<Resource<List<Noticia>?>> {

        Log.i("viewModel","Buscando todos na view model")

        return repository.buscaTodos()
    }
}