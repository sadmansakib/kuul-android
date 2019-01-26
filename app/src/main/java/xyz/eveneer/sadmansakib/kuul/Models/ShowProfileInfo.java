package xyz.eveneer.sadmansakib.kuul.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowProfileInfo {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("nofify_social")
    @Expose
    private Boolean nofifySocial;
    @SerializedName("nofify_contacts")
    @Expose
    private Boolean nofifyContacts;
    @SerializedName("respondee_value")
    @Expose
    private Integer respondeeValue;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getNofifySocial() {
        return nofifySocial;
    }

    public void setNofifySocial(Boolean nofifySocial) {
        this.nofifySocial = nofifySocial;
    }

    public Boolean getNofifyContacts() {
        return nofifyContacts;
    }

    public void setNofifyContacts(Boolean nofifyContacts) {
        this.nofifyContacts = nofifyContacts;
    }

    public Integer getRespondeeValue() {
        return respondeeValue;
    }

    public void setRespondeeValue(Integer respondeeValue) {
        this.respondeeValue = respondeeValue;
    }
}
