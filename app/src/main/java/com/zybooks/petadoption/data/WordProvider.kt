package com.zybooks.petadoption.data

import com.zybooks.petadoption.ui.Word

val spanishBeginnerVerbs = arrayOf(
    "andar", "ayudar", "bailar", "bajar", "beber",
    "buscar", "caminar", "cantar", "cambiar", "cenar",
    "cerrar", "cocinar", "comenzar", "comer", "comprar",
    "comprender", "contar", "correr", "creer", "dar",
    "decidir", "decir", "dejar", "descansar", "desear",
    "dibujar", "dormir", "enseñar", "entender", "enviar",
    "escribir", "escuchar", "esperar", "estar", "estudiar",
    "explicar", "ganar", "gastar", "hablar", "hacer",
    "invitar", "ir", "jugar", "lavar", "leer",
    "levantar", "limpiar", "llamar", "llegar", "llevar"
)

val spanishBeginnerDefinitions = arrayOf(
    "to walk", "to help", "to dance", "to go down, to lower", "to drink",
    "to look for", "to walk", "to sing", "to change", "to have dinner",
    "to close", "to cook", "to begin, to start", "to eat", "to buy",
    "to understand", "to count, to tell (a story)", "to run", "to believe", "to give",
    "to decide", "to say, to tell", "to leave (something)", "to rest", "to wish, to want",
    "to draw", "to sleep", "to teach", "to understand", "to send",
    "to write", "to listen", "to wait, to hope", "to be (temporary)", "to study",
    "to explain", "to win, to earn", "to spend (money)", "to speak, to talk", "to do, to make",
    "to invite", "to go", "to play (a game)", "to wash", "to read",
    "to lift, to raise", "to clean", "to call", "to arrive", "to carry, to wear"
)



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

val spanishAdvancedVerbs = arrayOf(
    "abordar", "abrumar", "acatar", "acoplar", "adjudicar",
    "aferrar", "agotar", "alegar", "alentar", "alterar",
    "amenazar", "amplificar", "anhelar", "anular", "apaciguar",
    "argüir", "arrasar", "arrojar", "asimilar", "atormentar",
    "avalar", "avistar", "azotar", "bloquear", "cegar",
    "coagular", "coartar", "conciliar", "concurrir", "conmover",
    "consolidar", "contravenir", "contrastar", "debilitar", "defraudar",
    "desmoronar", "desplomar", "disuadir", "ejecutar", "eludir",
    "enaltecer", "enriquecer", "entablar", "exhortar", "eximir",
    "extirpar", "favorecer", "fomentar", "menospreciar", "reprochar"
)

val spanishAdvancedDefinitions = arrayOf(
    "to tackle, to approach", "to overwhelm", "to comply with, to obey", "to couple, to fit together", "to award, to assign",
    "to cling to", "to exhaust, to deplete", "to claim, to argue", "to encourage", "to alter, to disrupt",
    "to threaten", "to amplify", "to long for", "to annul, to cancel", "to appease, to pacify",
    "to argue, to reason", "to devastate", "to hurl, to throw", "to assimilate", "to torment",
    "to endorse, to guarantee", "to sight, to glimpse", "to whip, to lash", "to block", "to blind",
    "to clot", "to restrict, to hinder", "to reconcile", "to gather, to meet", "to move emotionally",
    "to consolidate, to strengthen", "to contravene, to violate", "to contrast, to compare", "to weaken", "to defraud, to disappoint",
    "to crumble, to collapse", "to collapse, to fall down", "to dissuade", "to execute, to carry out", "to evade, to avoid",
    "to praise, to exalt", "to enrich", "to establish, to initiate", "to urge, to exhort", "to exempt, to free",
    "to remove, to eradicate", "to favor", "to promote, to foster", "to scorn, to undervalue", "to reproach, to criticize"
)


class WordProvider {
    val spanishBeginnerWords: List<Word> by lazy {
        spanishBeginnerVerbs.zip(spanishBeginnerDefinitions) { verb, definition ->
            Word(verb, definition, false)
        }
    }

    val spanishIntermediateWords: List<Word> by lazy {
        spanishIntermediateVerbs.zip(spanishIntermediateDefinitions) { verb, definition ->
            Word(verb, definition, false)
        }
    }

    val spanishAdvancedWords: List<Word> by lazy {
        spanishAdvancedVerbs.zip(spanishAdvancedDefinitions) { verb, definition ->
            Word(verb, definition, false)
        }
    }
}