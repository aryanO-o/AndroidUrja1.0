package com.aryandadhich.urja10.ui.forms

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.AvailableFormsListItemBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


class AvailableFormsAdapter(val applyClickListener: AvailableFormsApplyBtnListener, val editBtnListner: AvailableFormsEditBtnListner, val deleteBtnListner: AvailableFormsDeleteBtnListner, val filledFormListner: AvailableFormToFilledFormListner): androidx.recyclerview.widget.ListAdapter<Form, AvailableFormsAdapter.AvailableFormsViewHolder>(DiffCallBack){
    class AvailableFormsViewHolder(private var binding: AvailableFormsListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(form: Form, applyClickListener: AvailableFormsApplyBtnListener, editBtnListner: AvailableFormsEditBtnListner, deleteBtnListner: AvailableFormsDeleteBtnListner, filledFormListner: AvailableFormToFilledFormListner){

            binding.applyClickListner = applyClickListener;
            binding.editClickListner = editBtnListner;
            binding.deleteBtnListner = deleteBtnListner;
            binding.filledFormListner = filledFormListner
            binding.form = form;
            binding.executePendingBindings();


            if(form.toSelect == "house-captain" && role != "supervisor"){
                binding.availableFormsListItemEditFormBtn.visibility = View.INVISIBLE;
                binding.deleteFormBtn.visibility = View.INVISIBLE

            }
            else if(form.toSelect == "coordinator" && (role != "supervisor" && role != "house-captain"))
            {
                binding.availableFormsListItemEditFormBtn.visibility = View.INVISIBLE
                binding.deleteFormBtn.visibility = View.INVISIBLE

            }
            else if(form.toSelect == "event-cordinator" && (role != "supervisor" && role != "house-captain" && role != "coordinator"))
            {
                binding.availableFormsListItemEditFormBtn.visibility = View.INVISIBLE
                binding.deleteFormBtn.visibility = View.INVISIBLE

            }
            else if(role == ""){
                binding.availableFormsListItemEditFormBtn.visibility = View.INVISIBLE
                binding.deleteFormBtn.visibility = View.INVISIBLE
            }
            else{
                binding.availableFormsListItemEditFormBtn.visibility = View.VISIBLE

            }



            if(form.isActive == true){
                binding.availableFormsListItemApplyBtn.visibility = View.VISIBLE
                binding.availableFormsListItemEditFormBtn.setText("off")
                binding.availableFormsListItemEditFormBtn.setTextColor(Color.parseColor("#FC5F6A"))
            }
            else{
                binding.availableFormsListItemApplyBtn.visibility = View.GONE;
                binding.availableFormsListItemEditFormBtn.setText("on")
                binding.availableFormsListItemEditFormBtn.setTextColor(Color.parseColor("#FF03DAC5"))
            }
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Form>(){
        override fun areItemsTheSame(oldItem: Form, newItem: Form): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Form, newItem: Form): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvailableFormsAdapter.AvailableFormsViewHolder{
        return AvailableFormsAdapter.AvailableFormsViewHolder(
            AvailableFormsListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: AvailableFormsAdapter.AvailableFormsViewHolder, position: Int){
        val form = getItem(position)
        holder.bind(form, applyClickListener, editBtnListner, deleteBtnListner, filledFormListner);
    }


}

class AvailableFormsApplyBtnListener(val clickListener: (formId: Int) -> Unit){
    fun onApplyBtnClicked(form: Form) = clickListener(form.id)
}

class AvailableFormsEditBtnListner(val clickListener: (form: Form) -> Unit){
    fun onEditBtnClicked(form: Form) = clickListener(form)
}

class AvailableFormsDeleteBtnListner( val clickListener: (form: Form) -> Unit){
    fun onDeleteBtnClicked(form: Form)  = clickListener(form);
}

class AvailableFormToFilledFormListner(val clickListener: (formId: Int) -> Unit){
    fun getFilledForms(form: Form) = clickListener(form.id)
}