package com.aaronhalbert.androidmvpdemo.ui.detail

import android.os.Bundle
import com.aaronhalbert.androidmvpdemo.R
import com.aaronhalbert.androidmvpdemo.presentation.detail.DetailCoworkerContract
import com.aaronhalbert.androidmvpdemo.ui.browse.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

private const val DEFAULT_ID = 99

class DetailActivity : DaggerAppCompatActivity(), DetailCoworkerContract.View {
    @Inject lateinit var detailPresenter: DetailCoworkerContract.Presenter
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var title: String
    lateinit var avatar: String
    lateinit var bio: String
    var id: Int = DEFAULT_ID

    // region lifecycle ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getCoworkerFromIntent()
    }

    override fun onStart() {
        super.onStart()
        detailPresenter.start()
    }

    override fun onStop() {
        detailPresenter.stop()
        super.onStop()
    }

    // endregion lifecycle -------------------------------------------------------------------------

    // region presenter contract -------------------------------------------------------------------
    // TODO: implement

    override fun showProgress() {}
    override fun hideProgress() {}

    override fun showCoworker() {
        bindCoworkerToView()
    }

    override fun hideCoworker() {}

    override fun showErrorState() {}
    override fun hideErrorState() {}

    override fun showEmptyState() {}
    override fun hideEmptyState() {}

    override fun setPresenter(presenter: DetailCoworkerContract.Presenter) {
        detailPresenter = presenter
    }

    // endregion presenter contract ----------------------------------------------------------------

    // region helpers ------------------------------------------------------------------------------

    private fun getCoworkerFromIntent() {
        firstName = intent.getStringExtra(FIRST_NAME) ?: ""
        lastName = intent.getStringExtra(LAST_NAME) ?: ""
        title = intent.getStringExtra(TITLE) ?: ""
        avatar = intent.getStringExtra(AVATAR) ?: ""
        bio = intent.getStringExtra(BIO) ?: ""
        id = intent.getIntExtra(ID, DEFAULT_ID)
    }

    private fun bindCoworkerToView() {
        Glide.with(this)
            .load(avatar)
            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_person_black_24dp)
            )
            .into(detail_activity_avatar)

        detail_activity_name.text = getString(
            R.string.coworker_full_name,
            firstName,
            lastName
        )
        detail_activity_title.text = title
        detail_activity_bio.text = getString(
            R.string.coworker_bio,
            bio
        )
    }

    // endregion helpers ---------------------------------------------------------------------------
}
