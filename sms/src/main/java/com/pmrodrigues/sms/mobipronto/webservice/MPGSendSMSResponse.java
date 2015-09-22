
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
 *         &lt;element name="MPG_Send_SMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgSendSMSResult"
})
@XmlRootElement(name = "MPG_Send_SMSResponse")
public class MPGSendSMSResponse {

    @XmlElement(name = "MPG_Send_SMSResult")
    protected String mpgSendSMSResult;

    /**
     * Gets the value of the mpgSendSMSResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPGSendSMSResult() {
        return mpgSendSMSResult;
    }

    /**
     * Sets the value of the mpgSendSMSResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPGSendSMSResult(String value) {
        this.mpgSendSMSResult = value;
    }

}
