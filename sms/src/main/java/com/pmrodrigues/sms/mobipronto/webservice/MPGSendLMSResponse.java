
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
 *         &lt;element name="MPG_Send_LMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgSendLMSResult"
})
@XmlRootElement(name = "MPG_Send_LMSResponse")
public class MPGSendLMSResponse {

    @XmlElement(name = "MPG_Send_LMSResult")
    protected String mpgSendLMSResult;

    /**
     * Gets the value of the mpgSendLMSResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPGSendLMSResult() {
        return mpgSendLMSResult;
    }

    /**
     * Sets the value of the mpgSendLMSResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPGSendLMSResult(String value) {
        this.mpgSendLMSResult = value;
    }

}
