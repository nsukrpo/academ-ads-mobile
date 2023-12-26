package nsu.krpo.academads.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import nsu.krpo.academads.ui.base.error_dialog.ErrorDialog
import nsu.krpo.academads.ui.base.progress_dialog.AcademAdsProgressDialog

abstract class BaseFragment : Fragment(), ViewBindingHolder {
    override var _viewBindingDelegate: ViewBindingDelegate<*>? = null
    protected open val binding: ViewBinding? = null

    protected abstract val viewModel: BaseViewModel

    private val progressDialog by lazy {
        AcademAdsProgressDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return _viewBindingDelegate?.create(inflater, container)
            ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showErrorDialog.observe(viewLifecycleOwner, ::onErrorOccurred)
        viewModel.isLoading.observe(viewLifecycleOwner, ::setIsLoading)
    }

    private fun onErrorOccurred(throwable: Throwable) {
        ErrorDialog(throwable.toString()).show(childFragmentManager, "Error dialog")
    }

    private fun setIsLoading(isLoading: Boolean) {
        if (isLoading) {
            progressDialog.show(childFragmentManager, "Loading dialog")
            progressDialog.dialog?.setOnCancelListener { viewModel.onDismiss() }
            progressDialog.dialog?.setOnDismissListener { viewModel.onDismiss() }
        } else {
            if (progressDialog.isAdded) {
                progressDialog.dismiss()
            }
        }
    }
}