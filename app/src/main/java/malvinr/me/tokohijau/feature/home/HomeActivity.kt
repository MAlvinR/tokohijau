package malvinr.me.tokohijau.feature.home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.feature.product.ProductActivity
import malvinr.me.tokohijau.utils.hideKeyboard

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        input_product.setOnEditorActionListener { result, actionId, _ ->
            hideKeyboard()
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent(this, ProductActivity::class.java)
                    .apply { putExtra("KEYWORD", result.text.toString()) }
                    .apply { startActivity(this) }
                true
            } else {
                false
            }
        }
    }
}
