package com.frogermcs.githubactions.github.response;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by froger_mcs on 07/05/2017.
 */
public class RepositoriesManager {

    private static final long TWO_WEEKS = 1000 * 60 * 60 * 24 * 14;

    private final HttpRequestFactory requestFactory;
    private final Gson gson;

    public RepositoriesManager(HttpRequestFactory requestFactory,
                               Gson gson) {
        this.requestFactory = requestFactory;
        this.gson = gson;
    }

    public SearchResponse getTrendingRepositories() throws IOException {
        Date date = new Date(System.currentTimeMillis() - TWO_WEEKS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GenericUrl url = new GenericUrl(
                "https://api.github.com/search/repositories?" +
                        "sort=stars&" +
                        "order=desc&" +
                        "q=language:java created:>" + dateFormat.format(date)
        );
        String response = requestFactory.buildGetRequest(url).execute().parseAsString();
        return gson.fromJson(response, SearchResponse.class);
    }
}
