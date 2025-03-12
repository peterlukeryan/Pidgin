package com.zybooks.petadoption.data

import com.zybooks.petadoption.ui.Word


val spanishIntermediateVerbs = arrayOf(
    "abrir", "acercar", "acompañar", "aconsejar", "admitir",
    "ahorrar", "alcanzar", "alegrar", "alquilar", "añadir",
    "apagar", "aparecer", "apoyar", "aprovechar", "asistir",
    "asustar", "averiguar", "borrar", "cargar", "castigar",
    "coger", "colocar", "comprobar", "confesar", "conseguir",
    "convencer", "convertir", "corregir", "cumplir", "devolver",
    "discutir", "dudar", "elegir", "empezar", "enfadar",
    "entregar", "equivocar", "escoger", "esforzar", "evitar",
    "felicitar", "fingir", "fracasar", "guardar", "intentar",
    "lastimar", "lograr", "madrugar", "merecer", "notar"
)
val spanishIntermediateDefinitions = arrayOf(
    "to open", "to bring closer", "to accompany", "to advise", "to admit",
    "to save (money, resources)", "to reach", "to make happy", "to rent", "to add",
    "to turn off", "to appear", "to support", "to take advantage of", "to attend",
    "to frighten", "to find out", "to erase", "to load, to charge", "to punish",
    "to take, to grab", "to place, to put", "to check, to verify", "to confess",
    "to achieve, to obtain", "to convince", "to convert, to turn into", "to correct",
    "to fulfill, to accomplish", "to return (something)", "to argue, to discuss",
    "to doubt", "to choose", "to start", "to anger, to upset", "to deliver, to hand over",
    "to make a mistake", "to choose, to select", "to strive, to make an effort",
    "to avoid", "to congratulate", "to pretend", "to fail", "to keep, to store",
    "to try, to attempt", "to hurt", "to achieve", "to wake up early", "to deserve", "to notice"
)

class WordProvider {
    val spanishIntermediateWords: List<Word> by lazy {
        spanishIntermediateVerbs.zip(spanishIntermediateDefinitions) { verb, definition ->
            Word(verb, definition, false)
        }
    }
}