package com.pmrodrigues.utilities.email.dto;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marceloo on 04/05/2015.
 */
public class PDFAttachment extends Attachment {
    public PDFAttachment(final InputStream is, final String filename) throws IOException {
        super(is, "application/pdf", filename);
    }
}
