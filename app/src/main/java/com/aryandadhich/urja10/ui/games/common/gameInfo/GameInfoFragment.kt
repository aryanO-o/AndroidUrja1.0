package com.aryandadhich.urja10.ui.games.common.gameInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddBasketballGameBinding
import com.aryandadhich.urja10.databinding.FragmentGameInfoBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class GameInfoFragment : Fragment() {
    private lateinit var _binding: FragmentGameInfoBinding
    val binding get() = _binding!!

    private lateinit var viewModel: GameInfoViewModel;
    private lateinit var viewModelFactory: GameInfoViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameInfoBinding.inflate(inflater, container, false)


        val eventId = GameInfoFragmentArgs.fromBundle(arguments!!).eventId;
        viewModelFactory = GameInfoViewModelFactory(eventId);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameInfoViewModel::class.java);
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this);

        if(role == "") {
            binding.fragGameInfoEditBtn.visibility = View.GONE;
            binding.fragGameInfoUpdateBtn.visibility = View.GONE;
        }

        binding.fragGameInfoEditBtn.setOnClickListener {
            makeTextEditable()
        }
        binding.fragGameInfoUpdateBtn.setOnClickListener {
            onUpdateBtnClick()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it){
                updateEditTextViews()
                viewModel.settingDataComplete();
            }
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it)
        })

        return binding.root;
    }

    private fun toastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun onUpdateBtnClick() {
        viewModel.gameName = binding.fragGameInfoGameEditText.text.toString()
        viewModel.gameTitle = binding.fragGameInfoGameTitleEditText.text.toString();
        viewModel.refree = binding.fragGameInfoRefereeEditText.text.toString();
        viewModel.scorer = binding.fragGameInfoScorerCollegeIdEditText.text.toString()
        viewModel.venue  = binding.fragGameInfoVenueEditText.text.toString()

        var correct = true;

        var dateAndTimeString = "";
        dateAndTimeString +=  binding.fragGameInfoYearEditText.text.toString()
        if(binding.fragGameInfoYearEditText.text.toString().length !=4){
            Toast.makeText(context, "year will be in form YYYY", Toast.LENGTH_SHORT).show()
            correct = false
        }
        dateAndTimeString += "-"
        dateAndTimeString += binding.fragGameInfoMonthEditText.text.toString()
        if(binding.fragGameInfoMonthEditText.text.toString().length !=2){
            Toast.makeText(context, "month will be in form 00 - 12", Toast.LENGTH_SHORT).show()
            correct = false
        }
        dateAndTimeString += "-"
        dateAndTimeString += binding.fragGameInfoDayEditText.text.toString()
        if(binding.fragGameInfoDayEditText.text.toString().length !=2){
            Toast.makeText(context, "day will be in form 00 - 31", Toast.LENGTH_SHORT).show()
            correct = false
        }
        dateAndTimeString += "T"
        dateAndTimeString += binding.fragGameInfoHourEditText.text.toString()
        if(binding.fragGameInfoHourEditText.text.toString().length !=2){
            Toast.makeText(context, "hour will be in form 00 - 23", Toast.LENGTH_SHORT).show()
            correct = false
        }
        dateAndTimeString += ":"
        dateAndTimeString += binding.fragGameInfoMinutesEditText.text.toString()
        if(binding.fragGameInfoMinutesEditText.text.toString().length !=2){
            Toast.makeText(context, "minutes will be in form 00 - 59", Toast.LENGTH_SHORT).show()
            correct = false
        }
        dateAndTimeString += ":00.000Z"

        viewModel.dateAndTime = dateAndTimeString;

        if(correct) {
            viewModel.updateGameInfoDetails();
            makeTextNonEditable()
        }
    }

    private fun updateEditTextViews() {
        binding.fragGameInfoGameEditText.setText(viewModel.gameName)
        binding.fragGameInfoGameTitleEditText.setText(viewModel.gameTitle)
        val date = viewModel.dateAndTime.split('-')
        val day = date[2][0].toString() + date[2][1];
        val month = date[1];
        val year = date[0];

        val timeCombined = viewModel.dateAndTime.split('T')
        val time = timeCombined[1].split(':');
        val hour = time[0];
        val minutes = time[1];

        binding.fragGameInfoDayEditText.setText(day)
        binding.fragGameInfoMonthEditText.setText(month)
        binding.fragGameInfoYearEditText.setText(year)
        binding.fragGameInfoHourEditText.setText(hour)
        binding.fragGameInfoMinutesEditText.setText(minutes)
        binding.fragGameInfoScorerCollegeIdEditText.setText(viewModel.scorer)
        binding.fragGameInfoRefereeEditText.setText(viewModel.refree)
        binding.fragGameInfoVenueEditText.setText(viewModel.venue)
    }

    private fun makeTextEditable() {
        binding.fragGameInfoGameTitleEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoDayEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoMonthEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoYearEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoHourEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoMinutesEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoScorerCollegeIdEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoRefereeEditText.isFocusableInTouchMode = true;
        binding.fragGameInfoVenueEditText.isFocusableInTouchMode = true;

    }

    private fun makeTextNonEditable() {
        binding.fragGameInfoGameTitleEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoDayEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoMonthEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoYearEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoHourEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoMinutesEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoScorerCollegeIdEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoRefereeEditText.isFocusableInTouchMode = false;
        binding.fragGameInfoVenueEditText.isFocusableInTouchMode = false;

    }

}