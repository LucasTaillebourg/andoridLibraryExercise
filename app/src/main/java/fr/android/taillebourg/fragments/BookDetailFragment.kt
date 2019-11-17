package fr.android.taillebourg.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.android.taillebourg.Book
import fr.android.taillebourg.R


class BookDetailsFragment : Fragment(){

    private var book: Book? = null

    private var titleTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var coverImageView: ImageView? = null
    private var sysnopsisTextView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bookDetailView = inflater.inflate(R.layout.book_detail_frag, container, false)

        titleTextView = bookDetailView.findViewById(R.id.detailed_book_title)
        priceTextView = bookDetailView.findViewById(R.id.detailed_book_price)
        coverImageView = bookDetailView.findViewById(R.id.detailed_book_cover)
        sysnopsisTextView = bookDetailView.findViewById(R.id.detailed_book_synopsis)

        return bookDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.updateBook()
    }

    fun updateBook(){
        if(book !== null){
            titleTextView?.text = book?.title
            priceTextView?.text = "Pour la modique somme de : " + book?.price + " €"
            Picasso.get().load(book!!.cover).into(coverImageView)
            sysnopsisTextView?.text = book?.getSysnopsisAsString()
        }
    }

    fun setBook(book: Book){
        this.book = book
    }
}