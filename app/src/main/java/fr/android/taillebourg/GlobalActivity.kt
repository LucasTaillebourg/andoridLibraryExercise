package fr.android.taillebourg

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.android.taillebourg.fragments.BookDetailsFragment
import fr.android.taillebourg.fragments.BooksListFragment
import fr.android.taillebourg.interfaces.OnBookSelected


class GlobalActivity : AppCompatActivity(), OnBookSelected {

    private var modePortrait: Boolean? = null

    private val bookDetailsFragment = BookDetailsFragment()
    private val bookListFragment = BooksListFragment()

    companion object {
        const val url = "http://henri-potier.xebia.fr/"
    }

    override fun onBookSelect(book: Book) {
        bookDetailsFragment.setBook(book)

        if(modePortrait!!){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.portraitActivity, bookDetailsFragment, BookDetailsFragment::class.java.name)
                    .addToBackStack(BookDetailsFragment::class.java.name)
                    .commit()
        }
        else{
            bookDetailsFragment.updateBook()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modePortrait = resources.configuration.orientation == ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_library)

        if (modePortrait as Boolean){
            supportFragmentManager.beginTransaction().replace(R.id.portraitActivity, bookListFragment, BooksListFragment::class.java.name).commit()
        }else {
            supportFragmentManager.beginTransaction().replace(R.id.listBook, bookListFragment, BooksListFragment::class.java.name).commit()
            supportFragmentManager.beginTransaction().replace(R.id.bookDetail, bookDetailsFragment, BookDetailsFragment::class.java.name).commit()
        }
    }
}