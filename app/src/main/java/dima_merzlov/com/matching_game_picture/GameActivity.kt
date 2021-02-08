package dima_merzlov.com.matching_game_picture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.android.synthetic.main.content_main.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        game()
    }

    private fun mathcingPicture(par: Int, par2: Int): Boolean {
        if (par == par2) return true
        return false
    }

    private fun game() {
        var match1 = 0
        var match2 = 0
        var currentButton: Int = 0
        var nextButton: Int = 0
        val images: MutableList<Int> =
            mutableListOf(
                R.drawable.camel,
                R.drawable.lion,
                R.drawable.coala,
                R.drawable.fox,
                R.drawable.monkey,
                R.drawable.wolf,
                R.drawable.camel,
                R.drawable.lion,
                R.drawable.coala,
                R.drawable.fox,
                R.drawable.monkey,
                R.drawable.wolf
            )
        var buttons = arrayOf(
            button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12
        )
        images.shuffle();
        val cardBack = R.drawable.code
        var countPair = 0;
        var currentPos = 0
        for (i in 0..11) {

            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                buttons[i].setBackgroundResource(images[i])


                if (match1 == 0) {
                    match1 = images[i]
                    currentButton = i
                } else {
                    match2 = images[i]
                    nextButton = i
                }

                var handler = Handler(Looper.getMainLooper())
                handler.postDelayed(object : Runnable {
                    override fun run() {
                        if (match1 != 0 && match2 != 0 && mathcingPicture(match1, match2)) {
                            Log.i("KEY", mathcingPicture(match1, match2).toString())
                            buttons[currentButton].isClickable = false
                            buttons[nextButton].isClickable = false
                            match1 = 0
                            match2 = 0
                        } else if (match1 != 0 && match2 != 0) {
                            buttons[currentButton].setBackgroundResource(cardBack)
                            buttons[nextButton].setBackgroundResource(cardBack)
                            match1 = 0
                            match2 = 0
                        }
                    }

                }, 200)
            }
        }
    }
}
