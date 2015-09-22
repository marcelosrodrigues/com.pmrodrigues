
package com.pmrodrigues.sms.mobipronto.webservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Credencial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "credencial",
        "token",
        "message"
})
@XmlRootElement(name = "MPG_Calculate_Message_Length_UTF8_or_UTF16")
public class MPGCalculateMessageLengthUTF8OrUTF16 {

    @XmlElement(name = "Credencial")
    protected String credencial;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Message")
    protected String message;

    /**
     * Gets the value of the credencial property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCredencial() {
        return credencial;
    }

    /**
     * Sets the value of the credencial property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCredencial(String value) {
        this.credencial = value;
    }

    /**
     * Gets the value of the token property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
