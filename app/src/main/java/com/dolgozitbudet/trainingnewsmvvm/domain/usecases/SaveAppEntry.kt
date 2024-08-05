package com.dolgozitbudet.trainingnewsmvvm.domain.usecases

import com.dolgozitbudet.trainingnewsmvvm.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}