package fr.android.taillebourg

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.android.taillebourg.interfaces.OnBookSelected

class BookAdapter(private val context: Context, private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var bookSelectedListener: OnBookSelected? = null

    inner class BookViewHolder(val bookItemView: View) : RecyclerView.ViewHolder(bookItemView) {
        internal var titleItemView: TextView = bookItemView.findViewById<View>(R.id.book_item_title) as TextView
        internal var priceItemView: TextView = bookItemView.findViewById<View>(R.id.book_item_price) as TextView
        internal var coverItemView: ImageView = bookItemView.findViewById(R.id.book_item_cover) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item_lay, parent, false)

        bookSelectedListener = context as OnBookSelected

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        Picasso.get().load(books[position].cover).into(holder.coverItemView)
        holder.titleItemView.text = books[position].title
        holder.priceItemView.text = books[position].price + " €"
        holder.bookItemView.setOnClickListener{
            bookSelectedListener!!.onBookSelect(books[position])
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}

