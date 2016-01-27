//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.01.19 à 12:02:57 PM CET 
//


package org.tmf.dsmapi.appointment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.ManyToOne;
import org.tmf.dsmapi.commons.utils.CustomJsonDateSerializer;


/**
 * <p>Classe Java pour Schedule complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Schedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appointmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="favoriteAmpm" type="{http://orange.com/api/appointment/tmf/v0/model/business}FavoriteAmpmEnum" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketSegment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="weekNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relatedParty" type="{http://orange.com/api/appointment/tmf/v0/model/business}RelatedPartyRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productSpecification" type="{http://orange.com/api/appointment/tmf/v0/model/business}ProductSpecificationRef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedule", propOrder = {
    "id",
    "appointmentId",
    "category",
    "favoriteAmpm",
    "endDate",
    "limit",
    "marketSegment",
    "startDate",
    "weekNumber",
    "relatedParty",
    "productSpecification"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "Schedule")
@Table(name = "SCHEDULE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(length = 127)
public class Schedule
    implements Serializable
{

    private final static long serialVersionUID = 11L;
    protected String id;
    protected String appointmentId;
    protected String category;
    protected FavoriteAmpmEnum favoriteAmpm;
    @XmlElement(type = String.class)
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    @XmlSchemaType(name = "dateTime")
    protected Date endDate;
    protected String limit;
    protected String marketSegment;
    @XmlElement(type = String.class)
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    @XmlSchemaType(name = "dateTime")
    protected Date startDate;
    protected String weekNumber;
    protected List<RelatedPartyRef> relatedParty;
    protected AddressRef address;
    protected List<ProductSpecificationRef> productSpecification;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété appointmentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "APPOINTMENT_ID", length = 255)
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * Définit la valeur de la propriété appointmentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppointmentId(String value) {
        this.appointmentId = value;
    }

    /**
     * Obtient la valeur de la propriété category.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "CATEGORY", length = 255)
    public String getCategory() {
        return category;
    }

    /**
     * Définit la valeur de la propriété category.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Obtient la valeur de la propriété favoriteAmpm.
     * 
     * @return
     *     possible object is
     *     {@link FavoriteAmpmEnum }
     *     
     */
    @Basic
    @Column(name = "FAVORITE_AMPM", length = 255)
    @Enumerated(EnumType.STRING)
    public FavoriteAmpmEnum getFavoriteAmpm() {
        return favoriteAmpm;
    }

    /**
     * Définit la valeur de la propriété favoriteAmpm.
     * 
     * @param value
     *     allowed object is
     *     {@link FavoriteAmpmEnum }
     *     
     */
    public void setFavoriteAmpm(FavoriteAmpmEnum value) {
        this.favoriteAmpm = value;
    }

    /**
     * Obtient la valeur de la propriété endDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Définit la valeur de la propriété endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    /**
     * Obtient la valeur de la propriété limit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "LIMIT_", length = 255)
    public String getLimit() {
        return limit;
    }

    /**
     * Définit la valeur de la propriété limit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimit(String value) {
        this.limit = value;
    }

    /**
     * Obtient la valeur de la propriété marketSegment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "MARKET_SEGMENT", length = 255)
    public String getMarketSegment() {
        return marketSegment;
    }

    /**
     * Définit la valeur de la propriété marketSegment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketSegment(String value) {
        this.marketSegment = value;
    }

    /**
     * Obtient la valeur de la propriété startDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Définit la valeur de la propriété startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    /**
     * Obtient la valeur de la propriété weekNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "WEEK_NUMBER", length = 255)
    public String getWeekNumber() {
        return weekNumber;
    }

    /**
     * Définit la valeur de la propriété weekNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeekNumber(String value) {
        this.weekNumber = value;
    }

    /**
     * Gets the value of the relatedParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedPartyRef }
     * 
     * 
     */
    @OneToMany(targetEntity = RelatedPartyRef.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "RELATED_PARTY_SCHEDULE_ID")
    public List<RelatedPartyRef> getRelatedParty() {
        if (relatedParty == null) {
            relatedParty = new ArrayList<RelatedPartyRef>();
        }
        return this.relatedParty;
    }

    /**
     * 
     * 
     */
    public void setRelatedParty(List<RelatedPartyRef> relatedParty) {
        this.relatedParty = relatedParty;
    }

    /**
     * Obtient la valeur de la propriété address.
     * 
     * @return
     *     possible object is
     *     {@link AddressRef }
     *     
     */
    @ManyToOne(targetEntity = AddressRef.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "ADDRESS_APPOINTMENT_HJID")
    public AddressRef getAddress() {
        return address;
    }

    /**
     * Définit la valeur de la propriété address.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressRef }
     *     
     */
    public void setAddress(AddressRef value) {
        this.address = value;
    }

    /**
     * Gets the value of the productSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductSpecificationRef }
     * 
     * 
     */
    @OneToMany(targetEntity = ProductSpecificationRef.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "PRODUCT_SPECIFICATION_SCHEDU_0")
    public List<ProductSpecificationRef> getProductSpecification() {
        if (productSpecification == null) {
            productSpecification = new ArrayList<ProductSpecificationRef>();
        }
        return this.productSpecification;
    }

    /**
     * 
     * 
     */
    public void setProductSpecification(List<ProductSpecificationRef> productSpecification) {
        this.productSpecification = productSpecification;
    }

}
