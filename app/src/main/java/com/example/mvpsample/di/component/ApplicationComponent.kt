package com.example.mvpsample.di.component

import com.example.mvpsample.BaseApp
import com.example.mvpsample.di.module.ApplicationModule
import dagger.Component


// injects application and provides it when needed


@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)

}

/*
Defines the classes that the modules will be injected in
assigns references in our activities, services, or fragments to have access to
singletons we earlier defined
the activities, services, or fragments that are allowed to request the dependencies declared
by the modules (by means of the @Inject annotation) should be declared in this class with individual inject() methods

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface ApiComponent {
    void inject(MainActivity mainActivity);
    void inject(SynchronisationFragment synchronisationFragment);
    void inject(AuthentificationActivity authentificationActivity);
    void inject(AddCookiesInterceptor addCookiesInterceptor);
    void inject(ReceivedCookiesInterceptor receivedCookiesInterceptor);
}

         */