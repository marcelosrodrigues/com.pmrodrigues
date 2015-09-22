
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
 *         &lt;element name="v_st_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "vStStatus"
})
@XmlRootElement(name = "MPG_Credits")
public class MPGCredits {

    @XmlElement(name = "Credencial")
    protected String credencial;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "v_st_Status")
    protected String vStStatus;

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
     * Gets the value of the vStStatus property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVStStatus() {
        return vStStatus;
    }

    /**
     * Sets the value of the vStStatus property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVStStatus(String value) {
        this.vStStatus = value;
    }

}
