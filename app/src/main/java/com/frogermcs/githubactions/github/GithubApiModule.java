package com.frogermcs.githubactions.github;

import com.frogermcs.githubactions.github.response.RepositoriesManager;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by froger_mcs on 07/05/2017.
 */
@Module
public class GithubApiModule {

    @Provides
    @Singleton
    public HttpRequestFactory provideHttpRequestFactory() {
        HttpTransport httpTransport = new NetHttpTransport();
        return httpTransport.createRequestFactory();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public RepositoriesManager provideRepositoriesManager(HttpRequestFactory httpRequestFactory, Gson gson) {
        return new RepositoriesManager(httpRequestFactory, gson);
    }
}
