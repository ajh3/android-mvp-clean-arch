package com.aaronhalbert.androidmvpdemo.ui.browse

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaronhalbert.androidmvpdemo.R
import com.aaronhalbert.androidmvpdemo.presentation.browse.BrowseCoworkersContract
import com.aaronhalbert.androidmvpdemo.presentation.model.CoworkerView
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity.SortOrder.DEFAULT
import com.aaronhalbert.androidmvpdemo.ui.mapper.CoworkerMapper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

private const val SORT_ORDER = "sortOrder"
private const val FILENAME1 = "bsg.json"
private const val FILENAME2 = "itcrowd.json"

class BrowseActivity : DaggerAppCompatActivity(), BrowseCoworkersContract.View {
    @Inject lateinit var browsePresenter: BrowseCoworkersContract.Presenter
    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: CoworkerMapper
    override var sortOrder = DEFAULT
    override var fileName = FILENAME1

    // region lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setupBrowseRecycler()

        /* persist sort order across config changes */
        if (savedInstanceState != null)
            sortOrder = savedInstanceState.getSerializable(SORT_ORDER) as SortOrder
    }

    override fun onStart() {
        super.onStart()
        browsePresenter.start()
    }

    override fun onStop() {
        browsePresenter.stop()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SORT_ORDER, sortOrder)
    }

    // endregion lifecycle -------------------------------------------------------------------------

    // region options menu -------------------------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sort -> {
                showSortDialog()
                return true
            }

            R.id.cmb -> {
                fileName = FILENAME1
                browsePresenter.refresh()
            }

            R.id.aaron -> {
                fileName = FILENAME2
                browsePresenter.refresh()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // endregion options menu ----------------------------------------------------------------------

    // region presenter contract -------------------------------------------------------------------
    // TODO: implement all of these

    override fun showProgress() {}
    override fun hideProgress() {}

    override fun showCoworkers(coworkers: List<CoworkerView>) {
        browseAdapter.coworkers = coworkers.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
    }

    override fun hideCoworkers() {}

    override fun showErrorState() {}
    override fun hideErrorState() {}

    override fun showEmptyState() {}
    override fun hideEmptyState() {}

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: BrowseCoworkersContract.Presenter) {
        browsePresenter = presenter
    }

    override fun clearSortOrder() {
        sortOrder = DEFAULT
    }

    // endregion presenter contract ----------------------------------------------------------------

    // region helpers ------------------------------------------------------------------------------

    private fun setupBrowseRecycler() {
        browse_activity_recyclerview.layoutManager = LinearLayoutManager(this)
        browse_activity_recyclerview.adapter = browseAdapter
    }

    private fun showSortDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.options_menu_sort))
            .setItems(
                arrayOf(
                    getString(R.string.first_name_asc),
                    getString(R.string.first_name_desc),
                    getString(R.string.last_name_asc),
                    getString(R.string.last_name_desc),
                    getString(R.string.id_asc),
                    getString(R.string.id_desc)
                )
            ) { _, which ->
                when (which) {
                    0 -> sortOrder = SortOrder.FIRST_NAME_ASC
                    1 -> sortOrder = SortOrder.FIRST_NAME_DESC
                    2 -> sortOrder = SortOrder.LAST_NAME_ASC
                    3 -> sortOrder = SortOrder.LAST_NAME_DESC
                    4 -> sortOrder = SortOrder.ID_ASC
                    5 -> sortOrder = SortOrder.ID_DESC
                }

                browsePresenter.retrieveCoworkers()
            }
            .create()

        dialog.show()
    }

    // endregion helpers ---------------------------------------------------------------------------

    enum class SortOrder {
        DEFAULT,
        FIRST_NAME_ASC,
        FIRST_NAME_DESC,
        LAST_NAME_ASC,
        LAST_NAME_DESC,
        ID_ASC,
        ID_DESC
    }
}
