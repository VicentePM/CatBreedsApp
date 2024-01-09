package com.example.catbreedsapp.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catbreedsapp.data.models.breedList.Breed
import com.example.catbreedsapp.data.models.sendVotes.ResponseVote
import com.example.catbreedsapp.data.models.sendVotes.SendVotes
import com.example.catbreedsapp.data.models.votesList.Votes
import com.example.catbreedsapp.data.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(val context: Context): ViewModel() {

    private val repository = Repository(context)

    val breedsLiveData = MutableLiveData<List<Breed>?>()
    val selectedBreed = MutableLiveData<Breed>()
    val voteLiveData = MutableLiveData<ResponseVote?>()
    val voteListLiveData = MutableLiveData<List<Votes>?>()

    fun getBreeds() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getBreeds()
            if(response.isSuccessful) {
                val breedList = response.body()
                breedsLiveData.postValue(breedList)
            }
        }
    }

    fun voteBreed(vote: Int, imageId: String) {
        val sendVotes = SendVotes(imageId, vote, "Vicente")

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.voteBreed(sendVotes)

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val response = response.body()
                    voteLiveData.postValue(response)
                    Toast.makeText(context, response?.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getVoteList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getVoteList("Vicente")
            if(response.isSuccessful) {
                val response = response.body()
                voteListLiveData.postValue(response)
            } else {
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun delVote(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.delVote(id)

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val response = response.body()
                    if(response?.message == "SUCCESS"){
                        getVoteList()
                    } else {
                        Toast.makeText(context, "No se ha eliminado el voto correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}