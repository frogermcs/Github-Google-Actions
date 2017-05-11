package com.frogermcs.githubactions.assistant;

import com.frogermcs.gactions.AssistantActions;
import com.frogermcs.gactions.ResponseHandler;
import com.frogermcs.gactions.api.StandardIntents;
import com.frogermcs.githubactions.assistant.requestHandlers.MainRequestHandler;
import com.frogermcs.githubactions.assistant.requestHandlers.TrendingRequestHandler;
import com.frogermcs.githubactions.github.response.RepositoriesManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by froger_mcs on 10/05/2017.
 */
@Module
public class AssistantModule {

    private final HttpServletResponse response;

    public AssistantModule(HttpServletResponse response) {
        this.response = response;
    }

    @Provides
    @Singleton
    ResponseHandler provideResponseHandler() {
        return new AppEngineResponseHandler(response);
    }

    @Provides
    @Singleton
    MainRequestHandler.Factory provideMainRequestHandlerFactory() {
        return new MainRequestHandler.Factory();
    }

    @Provides
    @Singleton
    TrendingRequestHandler.Factory provideTrendingRequestHandlerFactory(RepositoriesManager repositoriesManager) {
        return new TrendingRequestHandler.Factory(repositoriesManager);
    }

    @Provides
    @Singleton
    AssistantActions provideAssistantActions(ResponseHandler responseHandler,
                                             MainRequestHandler.Factory mainRequestHandlerFactory,
                                             TrendingRequestHandler.Factory trendingRequestHandlerFactory) {
        return new AssistantActions.Builder(responseHandler)
                .addRequestHandlerFactory(StandardIntents.MAIN, mainRequestHandlerFactory)
                .addRequestHandlerFactory(Intents.TRENDING, trendingRequestHandlerFactory)
                .build();
    }
}
