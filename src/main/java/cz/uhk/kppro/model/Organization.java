package cz.uhk.kppro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "organizations")
public class Organization {

    @Id
    private String id;

    @Field("ICO")
    private String ico;

    @Field("OKRESLAU")
    private String okresLau;

    @Field("DDATVZN")
    private String dateEstablished;

    @Field("DDATZAN")
    private String dateTerminated;

    @Field("ZPZAN")
    private String terminationReason;

    @Field("DDATPAKT")
    private String lastUpdateDate;

    @Field("FORMA")
    private String legalForm;

    @Field("ROSFORMA")
    private String extendedLegalForm;

    @Field("KATPO")
    private String entrepreneurCategory;

    @Field("NACE")
    private String nace;

    @Field("ICZUJ")
    private String territorialUnit;

    @Field("FIRMA")
    private String name;

    @Field("CISS2010")
    private String ciss2010;

    @Field("KODADM")
    private String addressCode;

    @Field("TEXTADR")
    private String fullAddress;

    @Field("PSC")
    private String postalCode;

    @Field("OBEC_TEXT")
    private String city;

    @Field("COBCE_TEXT")
    private String district;

    @Field("ULICE_TEXT")
    private String street;

    @Field("TYPCDOM")
    private String houseNumberType;

    @Field("CDOM")
    private String houseNumber;

    @Field("COR")
    private String orientationNumber;

    @Field("DATPLAT")
    private String validityDate;

    @Field("PRIZNAK")
    private String flag;

    @GeoSpatialIndexed
    private GeoJsonPoint location;


    public List<String> getMemberUserIds() {
        return memberUserIds;
    }

    public void setMemberUserIds(List<String> memberUserIds) {
        this.memberUserIds = memberUserIds;
    }

    private List<String> memberUserIds = new ArrayList<>();


    public String getExtendedLegalForm() {
        return extendedLegalForm;
    }

    public void setExtendedLegalForm(String extendedLegalForm) {
        this.extendedLegalForm = extendedLegalForm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getOkresLau() {
        return okresLau;
    }

    public void setOkresLau(String okresLau) {
        this.okresLau = okresLau;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getDateTerminated() {
        return dateTerminated;
    }

    public void setDateTerminated(String dateTerminated) {
        this.dateTerminated = dateTerminated;
    }

    public String getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getEntrepreneurCategory() {
        return entrepreneurCategory;
    }

    public void setEntrepreneurCategory(String entrepreneurCategory) {
        this.entrepreneurCategory = entrepreneurCategory;
    }

    public String getNace() {
        return nace;
    }

    public void setNace(String nace) {
        this.nace = nace;
    }

    public String getTerritorialUnit() {
        return territorialUnit;
    }

    public void setTerritorialUnit(String territorialUnit) {
        this.territorialUnit = territorialUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCiss2010() {
        return ciss2010;
    }

    public void setCiss2010(String ciss2010) {
        this.ciss2010 = ciss2010;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumberType() {
        return houseNumberType;
    }

    public void setHouseNumberType(String houseNumberType) {
        this.houseNumberType = houseNumberType;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getOrientationNumber() {
        return orientationNumber;
    }

    public void setOrientationNumber(String orientationNumber) {
        this.orientationNumber = orientationNumber;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
