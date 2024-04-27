package com.cwilliam.task.manager.services.oauth;

import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.services.oauth.dto.OauthDto;

public interface AuthenticationService {

    OauthDto signin(User request);
}
