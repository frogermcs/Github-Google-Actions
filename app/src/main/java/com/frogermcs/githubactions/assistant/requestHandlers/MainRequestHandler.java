package com.frogermcs.githubactions.assistant.requestHandlers;

import com.frogermcs.gactions.ResponseBuilder;
import com.frogermcs.gactions.api.RequestHandler;
import com.frogermcs.gactions.api.request.RootRequest;
import com.frogermcs.gactions.api.response.RootResponse;

/**
 * Created by froger_mcs on 19/01/2017.
 */
public class MainRequestHandler extends RequestHandler {
    MainRequestHandler(RootRequest rootRequest) {
        super(rootRequest);
    }

    @Override
    public RootResponse getResponse() {
        return ResponseBuilder.tellResponse("Hey! Welcome to Github Actions. You can check what are the hottest java repositories!");
    }

    public static class Factory extends RequestHandler.Factory {

        @Override
        public RequestHandler create(RootRequest rootRequest) {
            return new MainRequestHandler(rootRequest);
        }
    }
}