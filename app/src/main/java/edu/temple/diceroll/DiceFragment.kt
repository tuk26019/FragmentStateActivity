package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"

class DiceFragment : Fragment() {
    private var sides: Int? = null
    private val CURRENT_NUMBER_KEY = "currentNumber"
    private var currentIndexID = 0

    private lateinit var numberDisplayTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {

            numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)
            findViewById<Button>(R.id.rollButton).setOnClickListener {
                changeIndex()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_NUMBER_KEY, currentIndexID )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.run{
            currentIndexID= getInt(CURRENT_NUMBER_KEY,1)
        }
        if (currentIndexID ==1)
            changeIndex()
        else
            changeIndex(currentIndexID)
    }
    fun changeIndex(){
        currentIndexID =Random.nextInt(sides!!)+1
        changeIndex(currentIndexID)
    }
    fun changeIndex(side:Int){
        numberDisplayTextView.text= side.toString()
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}