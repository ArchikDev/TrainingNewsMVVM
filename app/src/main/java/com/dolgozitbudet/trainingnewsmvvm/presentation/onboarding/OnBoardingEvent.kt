package com.dolgozitbudet.trainingnewsmvvm.presentation.onboarding

sealed class OnBoardingEvent {

    data object SaveAppEntry: OnBoardingEvent()

}