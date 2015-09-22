package com.pmrodrigues.sms.impl;

import com.pmrodrigues.sms.SMS;
import com.pmrodrigues.sms.SMSService;
import com.pmrodrigues.sms.exception.*;
import com.pmrodrigues.sms.mobipronto.webservice.MPGatewaySoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Marceloo on 27/04/2015.
 */
@Component
public class MPSMSService implements SMSService {

    @Value("${MP_CREDENCIAL}")
    private String credential;

    @Value("${MP_TOKEN}")
    private String token;

    private MPGatewaySoap gateway;

    @Autowired
    public MPSMSService(final MPGatewaySoap gateway) {
        this.gateway = gateway;
    }

    @Override
    public void enviar(final SMS sms) {
        final String retorno = gateway.mpgSendSimpleSMS(this.credential, this.token, sms.getTelefone(), sms.getMessagem());

        switch (retorno) {
            case "OK":
                return;
            case "001":
                throw new InvalidCredentialException("Credencial inválida");
            case "005":
                throw new InvalidPhoneFormatException("Telefone com formato inválido");
            case "008":
                throw new MessageSizeException("SMS com mais de 160 posições. SMS concatenado com mais de 15300 posições");
            case "009":
                throw new CreditInsufficientException("Créditos insuficientes em conta");
            case "012":
                throw new WarningException("Gateway SMS da conta bloqueado");
            case "013":
                throw new MessageSizeException("Conteúdo da mensagem inválido ou vazio");
            case "015":
                throw new CountryWithoutException("País sem cobertura ou não aceita mensagens concatenadas (SMS Longo)");
            case "016":
                throw new PhoeCompanyAuthorizationException("Operadora não autorizada para esta credencial");
            case "019":
                throw new TokenInvalidException("Token inválido");
            case "900":
                throw new AuthenticationExceedException("Erro de autenticação ou limite de segurança excedido");
            default:
                throw new SMSException("Falha no Gateway");
        }

    }
}
