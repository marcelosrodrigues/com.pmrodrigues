package test.com.pmrodrigues.sms;

import com.pmrodrigues.sms.SMS;
import com.pmrodrigues.sms.exception.*;
import com.pmrodrigues.sms.impl.MPSMSService;
import com.pmrodrigues.sms.mobipronto.webservice.MPGatewaySoap;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by Marceloo on 28/04/2015.
 */
public class TestMPSMSService {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void enviar() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("OK"));
        }});

        service.enviar(sms);

    }

    @Test(expected = InvalidCredentialException.class)
    public void erroNoEnvioPorFalhaDeAutenticacao() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("001"));
        }});

        service.enviar(sms);
    }

    @Test(expected = InvalidPhoneFormatException.class)
    public void erroNoEnvioPeloDevidoAoFormatoDoTelefone() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("005"));
        }});

        service.enviar(sms);
    }

    @Test(expected = MessageSizeException.class)
    public void erroDeEnvioPeloDevidoAoTamanhoDaMessagem() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("008"));
        }});

        service.enviar(sms);
    }

    @Test(expected = CreditInsufficientException.class)
    public void erroPorFaltaDeCredito() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("009"));
        }});

        service.enviar(sms);
    }

    @Test(expected = WarningException.class)
    public void erroPorContaBloqueada() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("012"));
        }});

        service.enviar(sms);
    }

    @Test(expected = MessageSizeException.class)
    public void erroPorMensagemVazia() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("013"));
        }});

        service.enviar(sms);
    }

    @Test(expected = CountryWithoutException.class)
    public void erroPorPaisSemCobertura() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("015"));
        }});

        service.enviar(sms);
    }

    @Test(expected = PhoeCompanyAuthorizationException.class)
    public void erroPorOperadoraSemAutorizacao() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("016"));
        }});

        service.enviar(sms);
    }

    @Test(expected = TokenInvalidException.class)
    public void erroPorTokenInvalido() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("019"));
        }});

        service.enviar(sms);
    }

    @Test(expected = AuthenticationExceedException.class)
    public void erroPorAutenticacaoExcederOLimeteDeSeguranca() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("900"));
        }});

        service.enviar(sms);
    }

    @Test(expected = SMSException.class)
    public void erroGenerico() throws Exception {

        final MPGatewaySoap gateway = context.mock(MPGatewaySoap.class);
        final SMS sms = context.mock(SMS.class);
        final MPSMSService service = new MPSMSService(gateway);

        setField("token", "teste", service);
        setField("credential", "teste", service);

        context.checking(new Expectations() {{

            oneOf(sms).getTelefone();
            will(returnValue("TESTE"));

            oneOf(sms).getMessagem();
            will(returnValue("TESTE"));

            oneOf(gateway).mpgSendSimpleSMS(with(aNonNull(String.class)), with(aNonNull(String.class)), with(any(String.class)), with(any(String.class)));
            will(returnValue("901"));
        }});

        service.enviar(sms);
    }

    private void setField(final String fieldname, final String value, final MPSMSService service) throws Exception {
        Field token = service.getClass().getDeclaredField(fieldname);
        token.setAccessible(true);
        token.set(service, value);
    }

}