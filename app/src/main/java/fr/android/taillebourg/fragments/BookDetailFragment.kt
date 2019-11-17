package fr.android.taillebourg.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.android.taillebourg.Book
import fr.android.taillebourg.R


class BookDetailsFragment : Fragment(){

    private var book: Book? = null

    private var titleTxtView: TextView? = null
    private var priceTxtView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bookDetailView = inflater.inflate(R.layout.book_detail_frag, container, false)

        titleTxtView = bookDetailView.findViewById(R.id.book_item_title)
        priceTxtView = bookDetailView.findViewById(R.id.book_item_price)

        return bookDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.updateBook()
    }

    fun updateBook(){
        if(book !== null){
            titleTxtView?.text = book?.title
            priceTxtView?.text = book?.price
        }
    }

    fun setBook(book: Book){
        this.book = book
    }
}