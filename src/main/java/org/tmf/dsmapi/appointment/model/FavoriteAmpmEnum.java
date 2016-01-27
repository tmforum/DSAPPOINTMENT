//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.01.19 à 12:02:57 PM CET 
//


package org.tmf.dsmapi.appointment.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FavoriteAmpmEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FavoriteAmpmEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AM"/>
 *     &lt;enumeration value="PM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FavoriteAmpmEnum")
@XmlEnum
public enum FavoriteAmpmEnum {

    AM,
    PM;

    public String value() {
        return name();
    }

    public static FavoriteAmpmEnum fromValue(String v) {
        return valueOf(v);
    }

}
