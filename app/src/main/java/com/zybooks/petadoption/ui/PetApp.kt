package com.zybooks.petadoption.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zybooks.petadoption.data.Pet
import com.zybooks.petadoption.data.PetDataSource
import com.zybooks.petadoption.data.PetGender
import com.zybooks.petadoption.ui.theme.PetAdoptionTheme
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog

import kotlinx.serialization.Serializable

sealed class Routes {
   @Serializable
   data object List

   @Serializable
   data class Settings(
      val selectedLanguage: String,
      val selectedDifficulty: String
   )

   @Serializable
   data class Word(
      val selectedLanguage: String,
      val selectedDifficulty: String
   )
   @Serializable
   data object Archive


}

@Composable
fun PetApp() {
   val navController = rememberNavController()
   val wordViewModel: WordScreenViewModel = viewModel()


   NavHost(
      navController = navController,
      startDestination = Routes.List
   ) {
      composable<Routes.List> {
         ListScreen(
            onButtonClick = {
               navController.navigate(Routes.Settings(selectedLanguage = "Spanish", selectedDifficulty = "Intermediate"))
            }
         )
      }
      composable<Routes.Settings> { backstackEntry ->
         val settings: Routes.Settings = backstackEntry.toRoute()

         SettingsScreen(viewModel = wordViewModel,
            onButtonClick = {
               navController.navigate(Routes.Word(selectedLanguage = "Spanish", selectedDifficulty = "Intermediate"))
            }
         )
      }
      composable<Routes.Word> { backstackEntry ->
         val word: Routes.Word = backstackEntry.toRoute()
         val selectedLanguage = backstackEntry.arguments?.getString("selectedLanguage")


         WordScreen(
           viewModel = wordViewModel, onNextClick = {navController.navigate(Routes.Archive)}
         )
      }
      composable<Routes.Archive> { backstackEntry ->
         val archive: Routes.Archive = backstackEntry.toRoute()

         ArchiveScreen(
            viewModel = wordViewModel
         )
      }
   }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetAppBar(
   title: String,
   modifier: Modifier = Modifier,
   canNavigateBack: Boolean = false,
   onUpClick: () -> Unit = { },

   ) {
   TopAppBar(
      title = { Text(title) },
      colors = TopAppBarDefaults.topAppBarColors(
         containerColor = MaterialTheme.colorScheme.primaryContainer
      ),
      modifier = modifier,
      navigationIcon = {
         if (canNavigateBack) {
            IconButton(onClick = onUpClick) {
               Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
         }
      }

   )
}

@Composable
fun ListScreen(
   onButtonClick: () -> Unit,
   modifier: Modifier = Modifier,

) {
   Box(modifier = Modifier.fillMaxSize()){
      Text("pidgin", Modifier.align(Alignment.Center), fontSize = 25.sp)
      Button(onClick=onButtonClick, modifier = Modifier.align(Alignment.BottomCenter).padding( 32.dp) ) {
         Text("get started")
      }
   }
}




@Composable
fun SettingsScreen(
   onButtonClick: () -> Unit,
   modifier: Modifier = Modifier,
   viewModel: WordScreenViewModel = viewModel(),
) {
   val language = viewModel.selectedLanguage
   val difficulty = viewModel.selectedDifficulty

   Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.TopStart
   ) {
      Column(
         modifier = Modifier.wrapContentSize().padding(16.dp),
         verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
         DropdownRow(
            text = language,
            expanded = viewModel.languageDropdownExpanded,
            onExpand = { viewModel.languageDropdownExpanded = true },
            onDismiss = { viewModel.languageDropdownExpanded = false },
            options = viewModel.languages,
            onSelect = { viewModel.selectedLanguage = it },
            header = "Select your language"
         )

         DropdownRow(
            text = difficulty,
            expanded = viewModel.difficultyDropdownExpanded,
            onExpand = { viewModel.difficultyDropdownExpanded = true },
            onDismiss = { viewModel.difficultyDropdownExpanded = false },
            options = viewModel.difficulties,
            onSelect = { viewModel.selectedDifficulty = it },
            header = "Select your level"
         )
      }
      Button(onClick=onButtonClick, modifier = Modifier.align(Alignment.BottomCenter).padding( 32.dp) ) {
         Text("begin!")
      }
   }

}

@Composable
fun DropdownRow(
   header: String,
   text: String,
   expanded: Boolean,
   onExpand: () -> Unit,
   onDismiss: () -> Unit,
   options: Array<String>,
   onSelect: (String) -> Unit
) {

   Box(modifier = Modifier.fillMaxWidth().padding(bottom = 200.dp)) {
      Text(modifier = Modifier.padding(10.dp).fillMaxWidth(), text = header)
      Row(
         modifier = Modifier.fillMaxWidth().padding(8.dp),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         TextButton(
            onClick = onExpand,
            modifier = Modifier.weight(1f).padding(top = 30.dp) // Ensures button takes most of the space
         ) {
            Text(text, textAlign = TextAlign.Left)
         }

         IconButton(onClick = onExpand) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
         }
      }

      DropdownMenu(
         expanded = expanded,
         onDismissRequest = onDismiss,
         modifier = Modifier.fillMaxWidth()
      ) {
         options.forEach { option ->
            DropdownMenuItem(
               text = { Text(option) },
               onClick = {
                  onSelect(option)
                  onDismiss()
               }
            )
         }
      }
   }
}

@Composable
fun WordCard(word: String, onButtonClick: () -> Unit, isSelected: Boolean){
   TextButton(onClick = onButtonClick){
      Row(modifier = Modifier.width(280.dp).background(color = Color.Cyan).padding(15.dp).height(40.dp), horizontalArrangement = Arrangement.SpaceBetween){
         Text(word)
         Icon(imageVector = Icons.Filled.Star, contentDescription = "Dropdown", tint = if (isSelected) Color.Yellow else Color.Gray)
      }
   }

}

@Composable
fun WordScreen(viewModel: WordScreenViewModel = viewModel(), onNextClick: () -> Unit ={}) {
   LaunchedEffect(viewModel.selectedLanguage, viewModel.selectedDifficulty) {
      viewModel.getWords()
   }
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      Row(
         modifier = Modifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         Text("round one", fontSize = 30.sp)
         Button(onClick = onNextClick) {
            Text("next")
         }
      }

      Spacer(modifier = Modifier.height(36.dp))

      // Column for word cards
      Column(
         verticalArrangement = Arrangement.spacedBy(8.dp),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         viewModel.words.map { word ->
            WordCard(
               word = word.word,
               onButtonClick = { viewModel.selectWord(word.word)
                                 viewModel.selectedWords.add(word)
                               },
               isSelected = viewModel.checkIsSelected(word.word)
            )
         }
      }
   }
}
@Composable
fun ArchiveScreen(viewModel: WordScreenViewModel = viewModel()) {
   var selectedWord by remember { mutableStateOf<Word?>(null) }
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      Row(
         modifier = Modifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         Text("archive", fontSize = 30.sp)

      }

      Spacer(modifier = Modifier.height(36.dp))

      // Column for word cards
      Column(
         verticalArrangement = Arrangement.spacedBy(8.dp),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         println("Selected words: ")
         println(viewModel.selectedWords)
         viewModel.selectedWords.map { word ->
            WordCard(
               word = word.word,
               onButtonClick = { selectedWord = word},
               isSelected = true
            )
         }
      }
      if (selectedWord != null) {
         Dialog(onDismissRequest = { selectedWord = null }) {
            Box(
               modifier = Modifier
                  .fillMaxWidth(0.8f)  // Set width to 80% of screen
                  .fillMaxHeight(0.5f) // Set height to 50% of screen
                  .background(Color.White, shape = RoundedCornerShape(16.dp))
                  .padding(16.dp)
            ) {
               Column(
                  horizontalAlignment = Alignment.CenterHorizontally,
                  modifier = Modifier.fillMaxSize()
               ) {
                  selectedWord?.word?.let { Text(text = it, fontSize = 24.sp, fontWeight = FontWeight.Bold) }
                  Spacer(modifier = Modifier.height(8.dp))
                  Text(text = selectedWord?.definition ?: "", fontSize = 18.sp)

                  // Pushes everything above it up, moving the button to the bottom
                  Spacer(modifier = Modifier.weight(1f))

                  Button(
                     onClick = { selectedWord = null },
                     modifier = Modifier.fillMaxWidth() // Ensures button is centered and takes full width
                  ) {
                     Text("Close")
                  }
               }
            }
         }

      }
   }
}





