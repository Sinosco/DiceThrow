package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.getValue
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random
import androidx.lifecycle.LifecycleOwner

const val DIESIDE = "sidenumber" //create as a global reference so companion function can see it

class DieFragment : Fragment() {

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java] //now each frag has its own instance of a viewModel
    }
    val CURRENT_ROLL_KEY = "current_roll"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }

        view.setOnClickListener{
            if(requireActivity() is DieRollInterface)
                (requireActivity() as DieRollInterface).rollDie()
        }
    }

    fun throwDie() {
        dieViewModel.setRoll((Random.nextInt(dieSides)+1))
    }

    companion object{
        fun newInstance(sides: Int) = DieFragment().apply{
            arguments = Bundle().apply{
                putInt(DIESIDE, sides)
            }
        }
    }

    interface DieRollInterface{
        fun rollDie()
    }
}