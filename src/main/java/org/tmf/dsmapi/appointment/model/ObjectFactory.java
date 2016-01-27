//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.01.19 à 12:02:57 PM CET 
//


package org.tmf.dsmapi.appointment.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tmf.dsmapi.appointment.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RelatedObject_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "RelatedObject");
    private final static QName _AddressRef_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "AddressRef");
    private final static QName _Appointment_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "Appointment");
    private final static QName _RelatedPartyRef_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "RelatedPartyRef");
    private final static QName _Schedule_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "Schedule");
    private final static QName _ProductSpecificationRef_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "ProductSpecificationRef");
    private final static QName _CalendarEntryAttachment_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "CalendarEntryAttachment");
    private final static QName _Note_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "Note");
    private final static QName _SlotSearchOut_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "Slot_search_out");
    private final static QName _FreeSlot_QNAME = new QName("http://orange.com/api/appointment/tmf/v0/model/business", "FreeSlot");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tmf.dsmapi.appointment.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductSpecificationRef }
     * 
     */
    public ProductSpecificationRef createProductSpecificationRef() {
        return new ProductSpecificationRef();
    }

    /**
     * Create an instance of {@link Schedule }
     * 
     */
    public Schedule createSchedule() {
        return new Schedule();
    }

    /**
     * Create an instance of {@link RelatedPartyRef }
     * 
     */
    public RelatedPartyRef createRelatedPartyRef() {
        return new RelatedPartyRef();
    }

    /**
     * Create an instance of {@link Appointment }
     * 
     */
    public Appointment createAppointment() {
        return new Appointment();
    }

    /**
     * Create an instance of {@link AddressRef }
     * 
     */
    public AddressRef createAddressRef() {
        return new AddressRef();
    }

    /**
     * Create an instance of {@link RelatedObject }
     * 
     */
    public RelatedObject createRelatedObject() {
        return new RelatedObject();
    }

    /**
     * Create an instance of {@link SlotSearchOut }
     * 
     */
    public SlotSearchOut createSlotSearchOut() {
        return new SlotSearchOut();
    }

    /**
     * Create an instance of {@link Note }
     * 
     */
    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link CalendarEntryAttachment }
     * 
     */
    public CalendarEntryAttachment createCalendarEntryAttachment() {
        return new CalendarEntryAttachment();
    }

    /**
     * Create an instance of {@link FreeSlot }
     * 
     */
    public FreeSlot createFreeSlot() {
        return new FreeSlot();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "RelatedObject")
    public JAXBElement<RelatedObject> createRelatedObject(RelatedObject value) {
        return new JAXBElement<RelatedObject>(_RelatedObject_QNAME, RelatedObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "AddressRef")
    public JAXBElement<AddressRef> createAddressRef(AddressRef value) {
        return new JAXBElement<AddressRef>(_AddressRef_QNAME, AddressRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Appointment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "Appointment")
    public JAXBElement<Appointment> createAppointment(Appointment value) {
        return new JAXBElement<Appointment>(_Appointment_QNAME, Appointment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedPartyRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "RelatedPartyRef")
    public JAXBElement<RelatedPartyRef> createRelatedPartyRef(RelatedPartyRef value) {
        return new JAXBElement<RelatedPartyRef>(_RelatedPartyRef_QNAME, RelatedPartyRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Schedule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "Schedule")
    public JAXBElement<Schedule> createSchedule(Schedule value) {
        return new JAXBElement<Schedule>(_Schedule_QNAME, Schedule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSpecificationRef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "ProductSpecificationRef")
    public JAXBElement<ProductSpecificationRef> createProductSpecificationRef(ProductSpecificationRef value) {
        return new JAXBElement<ProductSpecificationRef>(_ProductSpecificationRef_QNAME, ProductSpecificationRef.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarEntryAttachment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "CalendarEntryAttachment")
    public JAXBElement<CalendarEntryAttachment> createCalendarEntryAttachment(CalendarEntryAttachment value) {
        return new JAXBElement<CalendarEntryAttachment>(_CalendarEntryAttachment_QNAME, CalendarEntryAttachment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Note }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "Note")
    public JAXBElement<Note> createNote(Note value) {
        return new JAXBElement<Note>(_Note_QNAME, Note.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SlotSearchOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "Slot_search_out")
    public JAXBElement<SlotSearchOut> createSlotSearchOut(SlotSearchOut value) {
        return new JAXBElement<SlotSearchOut>(_SlotSearchOut_QNAME, SlotSearchOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FreeSlot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/appointment/tmf/v0/model/business", name = "FreeSlot")
    public JAXBElement<FreeSlot> createFreeSlot(FreeSlot value) {
        return new JAXBElement<FreeSlot>(_FreeSlot_QNAME, FreeSlot.class, null, value);
    }

}
