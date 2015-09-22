
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
 *         &lt;element name="Start_Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="End_Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aux_User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status_Code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "startDate",
        "endDate",
        "auxUser",
        "mobile",
        "statusCode",
        "status"
})
@XmlRootElement(name = "MPG_Query01")
public class MPGQuery01 {

    @XmlElement(name = "Credencial")
    protected String credencial;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Start_Date")
    protected String startDate;
    @XmlElement(name = "End_Date")
    protected String endDate;
    @XmlElement(name = "Aux_User")
    protected String auxUser;
    @XmlElement(name = "Mobile")
    protected String mobile;
    @XmlElement(name = "Status_Code")
    protected int statusCode;
    @XmlElement(name = "Status")
    protected String status;

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
     * Gets the value of the startDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEndDate(String value) {
        this.endDate = value;
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
     * Gets the value of the statusCode property.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     */
    public void setStatusCode(int value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
