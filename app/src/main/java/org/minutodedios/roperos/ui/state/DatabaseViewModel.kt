package org.minutodedios.roperos.ui.state

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.minutodedios.roperos.services.database.IDatabaseService
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    val databaseService: IDatabaseService
) : ViewModel()