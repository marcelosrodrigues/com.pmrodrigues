package com.pmrodrigues.security.event.listener;

import com.pmrodrigues.security.event.UserUpdateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Marceloo on 28/01/2016.
 */
@Transactional
public interface UserUpdateEventListener extends ApplicationListener<UserUpdateEvent> {
}
