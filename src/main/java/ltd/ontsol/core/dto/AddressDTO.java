package ltd.ontsol.core.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ltd.ontsol.core.AbstractPersistableEntity;
import ltd.ontsol.core.constants.PersistenceConstants;

/**
 * Created by cn40580 at 2018-06-13 3:05 PM.
 */
@Entity
@Table(name = "ADDRESS")
public class AddressDTO extends AbstractPersistableEntity<Long> {
    //
    private String country;
    private String province;
    private String city;
    private String region;
    private String street;
//
//    @Column(name = "TYPE", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private AddressTypeConstants type;

    //Only for agrr service and agrr work
    @Column(length = PersistenceConstants.LENGTH_100)
    private String text;

    @Column(length = PersistenceConstants.LENGTH_100)
    private String subText;

    //for agrr super list display
//    private LongText translatableText;

    private String lng;
    private String lat;

    @ManyToOne
    @JoinColumn(name = "AGRR_ID")
    @JsonIgnore
    private AgrrNodeDTO agrrNode;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

//    public LongText getTranslatableText() {
//        return translatableText;
//    }
//
//    public void setTranslatableText(LongText translatableText) {
//        this.translatableText = translatableText;
//    }

    public AgrrNodeDTO getAgrrNode() {
        return agrrNode;
    }

    public void setAgrrNode(AgrrNodeDTO agrrNode) {
        this.agrrNode = agrrNode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
