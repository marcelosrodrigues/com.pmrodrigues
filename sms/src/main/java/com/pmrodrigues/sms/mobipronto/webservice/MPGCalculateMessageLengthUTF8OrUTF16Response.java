
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
 *         &lt;element name="MPG_Calculate_Message_Length_UTF8_or_UTF16Result" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgCalculateMessageLengthUTF8OrUTF16Result"
})
@XmlRootElement(name = "MPG_Calculate_Message_Length_UTF8_or_UTF16Response")
public class MPGCalculateMessageLengthUTF8OrUTF16Response {

    @XmlElement(name = "MPG_Calculate_Message_Length_UTF8_or_UTF16Result")
    protected int mpgCalculateMessageLengthUTF8OrUTF16Result;

    /**
     * Gets the value of the mpgCalculateMessageLengthUTF8OrUTF16Result property.
     */
    public int getMPGCalculateMessageLengthUTF8OrUTF16Result() {
        return mpgCalculateMessageLengthUTF8OrUTF16Result;
    }

    /**
     * Sets the value of the mpgCalculateMessageLengthUTF8OrUTF16Result property.
     */
    public void setMPGCalculateMessageLengthUTF8OrUTF16Result(int value) {
        this.mpgCalculateMessageLengthUTF8OrUTF16Result = value;
    }

}
