
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
 *         &lt;element name="Principal_User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aux_User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "principalUser",
        "auxUser",
        "mobile",
        "message"
})
@XmlRootElement(name = "MPG_Send_Long_SMS")
public class MPGSendLongSMS {

    @XmlElement(name = "Credencial")
    protected String credencial;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Principal_User")
    protected String principalUser;
    @XmlElement(name = "Aux_User")
    protected String auxUser;
    @XmlElement(name = "Mobile")
    protected String mobile;
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
     * Gets the value of the principalUser property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPrincipalUser() {
        return principalUser;
    }

    /**
     * Sets the value of the principalUser property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPrincipalUser(String value) {
        this.principalUser = value;
    }

    /**
     * Gets the value of the auxUser property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAuxUser() {
        return auxUser;
    }

    /**
     * Sets the value of the auxUser property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAuxUser(String value) {
        this.auxUser = value;
    }

    /**
     * Gets the value of the mobile property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMobile(String value) {
        this.mobile = value;
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
