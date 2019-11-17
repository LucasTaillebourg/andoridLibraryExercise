package fr.android.taillebourg.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.android.taillebourg.Book
import fr.android.taillebourg.R
import fr.android.taillebourg.BookAdapter
import fr.android.taillebourg.GlobalActivity
import fr.android.taillebourg.interfaces.HenriPotierService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksListFragment : Fragment() {

    private val booksList: ArrayList<Book> = ArrayList()

    private var bookAdapter: RecyclerView.Adapter<*>? = null
    private var booksListRecyclerView: RecyclerView? = null

    override fun onAttach(context: Context) {
        Log.d("STATE","STATE : onAttach")
        super.onAttach(context)
        this.fetchBooks()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("STATE","STATE : onCreateView")
        val bookListView = inflater.inflate(R.layout.list_books_frag, container, false)

        booksListRecyclerView = bookListView.findViewById(R.id.book_list_recycler_view)

        return bookListView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("STATE","STATE : onViewCreated")

        bookAdapter = BookAdapter(this.context!!, booksList)
        booksListRecyclerView!!.adapter = bookAdapter
        booksListRecyclerView!!.layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)

        bookAdapter!!.notifyDataSetChanged()
    }

    private fun fetchBooks(){

        val retrofit = Retrofit.Builder()
                .baseUrl(GlobalActivity.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service: HenriPotierService = retrofit.create(HenriPotierService::class.java)

        val res = service.listBooks()
        val books: ArrayList<Book> = ArrayList()

        res.enqueue(object: Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val allBooks = response.body()
                allBooks?.let {
                    for( book in it) {
                        Log.d("BOOK","Book : ${book.title}")
                        books.add(book)
                    }
                    Log.d("STATE","STATE : book fetched")
                    bookAdapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Log.e("Book", "Error : $t")
            }
        })


    }
}