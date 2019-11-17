package fr.android.taillebourg

data class Book(val isbn: String?, val title: String?, val price: String?, val cover: String?,val synopsis: List<String>) {
    fun getSysnopsisAsString(): String{
        return synopsis.joinToString("\n\n")
    }
}