package com.frogermcs.githubactions;

import com.frogermcs.githubactions.assistant.AssistantModule;
import com.frogermcs.githubactions.github.GithubApiModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by froger_mcs on 04/05/2017.
 */
@Singleton
@Component(
        modules = {
                GithubApiModule.class,
                AssistantModule.class
        }
)
public interface GithubActionsComponent {
    void inject(GithubActionsServlet githubActionsServlet);
}
