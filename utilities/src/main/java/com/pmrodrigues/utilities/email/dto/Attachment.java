package com.pmrodrigues.utilities.email.dto;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marceloo on 04/05/2015.
 */
public abstract class Attachment extends ByteArrayDataSource {

    private final String filename;

    public Attachment(final InputStream is, final String type, final String filename) throws IOException {
        super(is, type);
        this.filename = filename;
    }

    public String getNomeArquivo() {
        return this.filename;
    }


}
