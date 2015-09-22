
package com.pmrodrigues.sms.mobipronto.webservice;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


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
 *         &lt;element name="MPG_CreditsResult" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="v_st_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mpgCreditsResult",
        "vStStatus"
})
@XmlRootElement(name = "MPG_CreditsResponse")
public class MPGCreditsResponse {

    @XmlElement(name = "MPG_CreditsResult", required = true)
    protected BigDecimal mpgCreditsResult;
    @XmlElement(name = "v_st_Status")
    protected String vStStatus;

    /**
     * Gets the value of the mpgCreditsResult property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getMPGCreditsResult() {
        return mpgCreditsResult;
    }

    /**
     * Sets the value of the mpgCreditsResult property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setMPGCreditsResult(BigDecimal value) {
        this.mpgCreditsResult = value;
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
