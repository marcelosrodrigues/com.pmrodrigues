package com.pmrodrigues.utilities.email;

import com.pmrodrigues.utilities.email.dto.Attachment;

import java.util.Map;

/**
 * Created by Marceloo on 24/04/2015.
 */
public interface EmailService {

    EmailService from(String email);

    EmailService to(String email);

    EmailService cc(String email);

    EmailService subject(String subject);

    EmailService message(String message);

    EmailService attachment(Attachment attachment);

    EmailService template(String template,
                          Map<String, Object> parameters);

    void send();
}
