package com.strategycorps.medlite.repository

import com.strategycorps.medlite.data.LocalDataSource
import com.strategycorps.medlite.data.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataStore: RemoteDataSource, localDateSource: LocalDataSource) {

    val remote = remoteDataStore
    val local = localDateSource

}