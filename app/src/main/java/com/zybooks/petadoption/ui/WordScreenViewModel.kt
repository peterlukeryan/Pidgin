package com.zybooks.petadoption.ui
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.petadoption.data.WordProvider


data class Word(val word: String, val definition: String, var selected: Boolean)

class WordScreenViewModel : ViewModel() {
    val provider = WordProvider()
    var selectedLanguage by mutableStateOf("English")
    var selectedDifficulty by mutableStateOf("Beginner")
    var difficultyDropdownExpanded by mutableStateOf(false);
    var languageDropdownExpanded by mutableStateOf(true);
    var languages = arrayOf("Spanish", "Italian", "Dutch", "French");
    var difficulties = arrayOf("Beginner", "Intermediate", "Advanced");


    var words by mutableStateOf(
        listOf(
            Word("lorem", "A placeholder text often used in design", true),
            Word("ipsum", "The second word in 'lorem ipsum' placeholder text", false),
            Word("dolor", "Latin for 'pain' or 'suffering'", false),
            Word("sit", "Latin for 'let it be' or 'exists'", false),
            Word("amet", "Latin for 'loved' or 'desired'", false)
        )
    )

    init {
        getWords()
    }


    // Use mutableStateListOf for selectedWords to trigger recomposition
    var selectedWords by mutableStateOf(mutableListOf<Word>())

    fun getWords(){
        if (selectedLanguage == "Spanish"){
            if (selectedDifficulty == "Intermediate"){
                words = provider.spanishIntermediateWords.shuffled().take(5)
            }
        }
    }

    fun selectWord(word: String) {
        // Update the words list with the new selection state
        words = words.map { item ->
            if (item.word == word) {
                item.copy(selected = !item.selected)
            } else {
                item
            }
        }

        // Only add selected words to the list, if they are selected
        val selectedItem = words.find { it.word == word && it.selected }
        selectedItem?.let {
            // Remove from selectedWords if already added to avoid duplicates
            selectedWords.removeAll { it.word == word }
            if (!selectedWords.contains(it)) {
                selectedWords.add(it)
            }

            println("selected words now");
            println(selectedWords)
        }
    }

    fun checkIsSelected(word: String): Boolean {
        return words.any { it.word == word && it.selected }
    }
}
