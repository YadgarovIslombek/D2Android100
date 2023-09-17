package com.example.d2android100.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.d2android100.R
import com.example.d2android100.databinding.FragmentShopItemBinding
import com.example.d2android100.domain.ShopItem

class ShopItemFragment(

) : Fragment() {
    lateinit var viewModel: ShopItemViewModel
    private var screenStatus: String = UNKOWN
    private  var shopItemID: Int = ShopItem.UNDEFINED_ID
    var binding: FragmentShopItemBinding? = null
    private lateinit var editListen:editListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is editListener){
            editListen = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam();
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        binding = FragmentShopItemBinding.inflate(inflater, container, false)
        showView()
        binding?.name?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputNameError()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding?.count?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetInputCountError()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
//            requireActivity().onBackPressed()
//            (activity as MainActivity).getEditListener();

//            val onBackPressedDispatcher = activity?.onBackPressedDispatcher
//            onBackPressedDispatcher?.onBackPressed()

            editListen.onEditListenerFinished()

        }
        return binding?.root
    }


    //
//
    private fun showView() {
        when (screenStatus) {
            ADD -> launchAddStatus()
            EDIT -> launchEditStatus()
            else -> throw RuntimeException("status aniqlanmadi $screenStatus")
        }
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val mess = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding?.name1?.error = mess
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val mess = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding?.count1?.error = mess
        }

    }

    private fun launchEditStatus() {

        viewModel.getShopById(shopItemID)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            binding?.name?.setText(it.item_name.toString())
            binding?.count?.setText(it.count.toString())
        }
        binding?.apply {
            save.setOnClickListener {
                viewModel.editShopItem(name.text.toString(), count.text.toString())
            }
        }
    }

    private fun launchAddStatus() {
        binding?.apply {
            save.setOnClickListener {
                viewModel.addShopItem(name.text.toString(), count.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun parseParam() {
        var args = requireArguments()
        if (!args.containsKey(STATUS)) {
            throw RuntimeException("parametrlar topilmadi")
        }
        val status = args.getString(STATUS)
        if (status != ADD && status != EDIT) {
            throw RuntimeException("Status xato $status")
        }
        screenStatus = status
        if (status == ShopItemActivy.EDIT) {
            if (!args.containsKey(SHOP_ID)) {
                throw RuntimeException("EDIT STATUSDA ID KELMADI")
            }
            shopItemID = args.getInt(ShopItemActivy.SHOP_ID, ShopItem.UNDEFINED_ID)

        }


    }

    interface editListener{
        fun onEditListenerFinished()
    }

    //
    companion object {
        const val STATUS = "status"
        const val ADD = "add"
        const val EDIT = "edit"
        const val SHOP_ID = "id"
        const val UNKOWN = ""

        fun newIntanseItemAdd(): ShopItemFragment {
           return ShopItemFragment().apply {
               arguments = Bundle().apply {
                   putString(STATUS, ADD)
               }
           }
        }
        fun newIntanseItemEdit(id:Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(STATUS, EDIT)
                    putInt(SHOP_ID, id)
                }
            }
        }
        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivy::class.java)
            intent.putExtra(STATUS, ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivy::class.java)
            intent.putExtra(STATUS, EDIT)
            intent.putExtra(SHOP_ID, id)
            return intent
        }
    }
}
