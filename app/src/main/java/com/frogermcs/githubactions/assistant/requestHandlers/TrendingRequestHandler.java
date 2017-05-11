package com.frogermcs.githubactions.assistant.requestHandlers;

import com.frogermcs.gactions.ResponseBuilder;
import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.api.request.RootRequest;
import com.frogermcs.gactions.api.response.RootResponse;
import com.frogermcs.githubactions.github.response.RepositoriesManager;
import com.frogermcs.githubactions.github.response.RepositoryResponse;
import com.frogermcs.githubactions.github.response.SearchResponse;

import java.util.Random;

/**
 * Created by froger_mcs on 04/05/2017.
 */
public class TrendingRequestHandler extends RequestHandler {

    private final RepositoriesManager repositoriesManager;

    protected TrendingRequestHandler(RootRequest rootRequest, RepositoriesManager repositoriesManager) {
        super(rootRequest);
        this.repositoriesManager = repositoriesManager;
    }

    @Override
    public RootResponse getResponse() {
        try {
            SearchResponse trendingRepositories = repositoriesManager.getTrendingRepositories();
            StringBuilder sb = new StringBuilder("Here you have one of the hottest java repositories from last days: ");
            int randomNo = new Random().nextInt(5);
            if (randomNo > trendingRepositories.items.size()) {
                randomNo = trendingRepositories.items.size();
            }

            RepositoryResponse repositoryResponse = trendingRepositories.items.get(randomNo);
            sb.append(repositoryResponse.name);
            sb.append(" - ");
            sb.append(repositoryResponse.description);
            sb.append(". ");
            sb.append(repositoryResponse.stargazers_count);
            sb.append(" stars");
            sb.append(".\n");
            return ResponseBuilder.tellResponse(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.tellResponse("Something went wrong. Please come back later.");
        }
    }

    public static class Factory extends RequestHandler.Factory {

        private final RepositoriesManager repositoriesManager;

        public Factory(RepositoriesManager repositoriesManager) {
            this.repositoriesManager = repositoriesManager;
        }

        @Override
        public RequestHandler create(RootRequest rootRequest) {
            return new TrendingRequestHandler(rootRequest, repositoriesManager);
        }
    }
}
