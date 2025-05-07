package com.codepath.project2wishlist
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var items: MutableList<WishlistItem>
    private lateinit var nameEntry: EditText
    private lateinit var urlEntry: EditText
    private lateinit var priceEntry: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = mutableListOf()

        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        val wishlistAdapter = WishlistAdapter(items)
        wishlistRv.adapter = wishlistAdapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        nameEntry = findViewById(R.id.nameEntry)
        urlEntry = findViewById(R.id.urlEntry)
        priceEntry = findViewById(R.id.priceEntry)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val name = nameEntry.text.toString()
            val url = urlEntry.text.toString()
            val priceText = priceEntry.text.toString()

            if (name.isNotBlank() && url.isNotBlank() && priceText.isNotBlank()) {
                try {
                    val price = priceText.toDouble()
                    val newItem = WishlistItem(name, url, price)
                    items.add(newItem)
                    wishlistAdapter.notifyDataSetChanged()
                    nameEntry.text.clear()
                    urlEntry.text.clear()
                    priceEntry.text.clear()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
