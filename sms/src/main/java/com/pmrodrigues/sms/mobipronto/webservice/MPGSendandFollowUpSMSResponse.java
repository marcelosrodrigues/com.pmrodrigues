
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
 *         &lt;element name="MPG_SendandFollowUp_SMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgSendandFollowUpSMSResult"
})
@XmlRootElement(name = "MPG_SendandFollowUp_SMSResponse")
public class MPGSendandFollowUpSMSResponse {

    @XmlElement(name = "MPG_SendandFollowUp_SMSResult")
    protected String mpgSendandFollowUpSMSResult;

    /**
     * Gets the value of the mpgSendandFollowUpSMSResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPGSendandFollowUpSMSResult() {
        return mpgSendandFollowUpSMSResult;
    }

    /**
     * Sets the value of the mpgSendandFollowUpSMSResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPGSendandFollowUpSMSResult(String value) {
        this.mpgSendandFollowUpSMSResult = value;
    }

}
