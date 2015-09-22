package com.pmrodrigues.utilities.email.impl;

import com.pmrodrigues.utilities.email.EmailService;
import com.pmrodrigues.utilities.email.dto.Attachment;
import com.pmrodrigues.utilities.exceptions.EnderecoEmailInvalidoException;
import com.pmrodrigues.utilities.exceptions.ErroAdicionarAttachmentException;
import com.pmrodrigues.utilities.exceptions.ErrorProcessamentoDeTemplateException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;

import static java.lang.String.format;

/**
 * Created by Marceloo on 16/04/2015.
 */
@Service("EmailService")
public class EmailServiceImpl implements com.pmrodrigues.utilities.email.EmailService {

    @Resource(name = "mailSender")
    private JavaMailSender sender;

    private MimeMessage message;

    @Resource(name = "velocityEngine")
    private VelocityEngine velocityEngine;

    @Override
    public EmailService from(final String email) {

        try {
            createMimeMessage();
            message.setFrom(new InternetAddress(email));
            return this;
        } catch (MessagingException e) {
            throw new EnderecoEmailInvalidoException(format(
                    "O endereço %s é inválido", email), e);
        }
    }

    @Override
    public EmailService to(final String email) {
        try {
            createMimeMessage();
            message.setRecipient(RecipientType.TO, new InternetAddress(email));
            return this;
        } catch (MessagingException e) {
            throw new EnderecoEmailInvalidoException(format(
                    "O endereço %s é inválido", email), e);
        }
    }

    @Override
    public EmailService cc(final String email) {
        try {
            createMimeMessage();
            message.setRecipient(RecipientType.CC, new InternetAddress(email));
            return this;
        } catch (MessagingException e) {
            throw new EnderecoEmailInvalidoException(format(
                    "O endereço %s é inválido", email), e);
        }
    }

    @Override
    public EmailService subject(final String subject) {
        try {
            createMimeMessage();
            message.setSubject(subject);
            return this;
        } catch (MessagingException e) {
            throw new EnderecoEmailInvalidoException(format(
                    "Erro ao adicionar o subject %s ao email", subject), e);
        }
    }

    @Override
    public EmailService message(final String message) {
        try {
            this.message.setContent(message, "text/plain; charset=utf-8");
            return this;
        } catch (MessagingException e) {
            throw new ErrorProcessamentoDeTemplateException(e.getMessage(), e);
        }
    }

    @Override
    public EmailService attachment(final Attachment attachment) {
        try {
            final MimeBodyPart bodypart = new MimeBodyPart();

            bodypart.setDataHandler(new DataHandler(attachment));
            bodypart.setFileName(attachment.getNomeArquivo());

            final Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodypart);
            message.setContent(multipart);
            return this;
        } catch (MessagingException e) {
            throw new ErroAdicionarAttachmentException(e);
        }
    }


    @Override
    public EmailService template(final String template,
                                 final Map<String, Object> parameters) {

        try {
            createMimeMessage();
            final String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, template, "UTF-8", parameters);

            message.setContent(text, "text/html; charset=utf-8");

            return this;
        } catch (MessagingException e) {
            throw new ErrorProcessamentoDeTemplateException(e.getMessage(), e);
        }
    }

    @Override
    public void send() {
        this.sender.send(this.message);
    }

    private void createMimeMessage() {
        if (message == null) {
            message = sender.createMimeMessage();
        }
    }

}
