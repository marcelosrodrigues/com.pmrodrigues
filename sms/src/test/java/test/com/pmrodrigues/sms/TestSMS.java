package test.com.pmrodrigues.sms;

import com.pmrodrigues.sms.SMS;
import com.pmrodrigues.sms.SMSService;
import com.pmrodrigues.sms.exception.SMSException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

/**
 * Created by Marceloo on 28/04/2015.
 */
public class TestSMS {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void enviarComSucesso() throws Exception {

        final SMSService service = context.mock(SMSService.class);

        context.checking(new Expectations() {{
            allowing(service).enviar(with(aNonNull(SMS.class)));
        }});

        final SMS sms = new SMS(service);

        sms.comMessagem("teste")
                .comTelefone("+55(21)981363699")
                .enviar();

    }

    @Test(expected = SMSException.class)
    public void falhaNoEnvio() throws Exception {
        final SMSService service = context.mock(SMSService.class);

        context.checking(new Expectations() {{
            allowing(service).enviar(with(aNonNull(SMS.class)));
            will(throwException(new SMSException("")));
        }});

        final SMS sms = new SMS(service);

        sms.comMessagem("teste")
                .comTelefone("+55(21)981363699")
                .enviar();
    }
}