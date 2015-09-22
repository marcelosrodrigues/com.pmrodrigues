package com.pmrodrigues.utilities.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Marceloo on 05/05/2015.
 */
public final class Deserializer {

    public static Object unmarshall(Class type, InputStream source) {

        try {
            final Reader reader = new InputStreamReader(source, "UTF-8");
            final JAXBContext context = JAXBContext.newInstance(type);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(reader);
        } catch (JAXBException | UnsupportedEncodingException e) {
            return null;
        }

    }
}
