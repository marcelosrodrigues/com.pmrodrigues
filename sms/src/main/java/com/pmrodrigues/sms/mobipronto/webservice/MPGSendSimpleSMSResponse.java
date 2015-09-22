
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
 *         &lt;element name="MPG_SendSimple_SMSResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgSendSimpleSMSResult"
})
@XmlRootElement(name = "MPG_SendSimple_SMSResponse")
public class MPGSendSimpleSMSResponse {

    @XmlElement(name = "MPG_SendSimple_SMSResult")
    protected String mpgSendSimpleSMSResult;

    /**
     * Gets the value of the mpgSendSimpleSMSResult property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMPGSendSimpleSMSResult() {
        return mpgSendSimpleSMSResult;
    }

    /**
     * Sets the value of the mpgSendSimpleSMSResult property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMPGSendSimpleSMSResult(String value) {
        this.mpgSendSimpleSMSResult = value;
    }

}
