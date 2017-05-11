package com.frogermcs.githubactions;

import com.frogermcs.gactions.AssistantActions;
import com.frogermcs.gactions.api.request.RootRequest;
import com.frogermcs.githubactions.assistant.AssistantModule;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by froger_mcs on 17/01/2017.
 */
public class GithubActionsServlet extends HttpServlet {

    @Inject
    AssistantActions assistantActions;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello in Github action!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Configure and inject dependencies
        DaggerGithubActionsComponent.builder()
                .assistantModule(new AssistantModule(response))
                .build()
                .inject(this);

        //Handle call from Google Assistant
        assistantActions.handleRequest(parseActionRequest(request));
    }

    private RootRequest parseActionRequest(HttpServletRequest request) throws IOException {
        JsonReader jsonReader = new JsonReader(request.getReader());
        return new Gson().fromJson(jsonReader, RootRequest.class);
    }
}
