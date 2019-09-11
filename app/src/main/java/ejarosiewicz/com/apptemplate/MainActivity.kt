package ejarosiewicz.com.apptemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, RequesterActivity::class.java)
            startActivity(intent)
        }
    }

}
