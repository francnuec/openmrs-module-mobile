/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.mobile.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.openmrs.mobile.utilities.ImageUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person extends Resource implements Serializable {

    @SerializedName("names")
    @Expose
    private List<PersonName> names = new ArrayList<>();
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("birthdateEstimated")
    @Expose
    private boolean birthdateEstimated;
    @SerializedName("addresses")
    @Expose
    private List<PersonAddress> addresses = new ArrayList<>();
    @SerializedName("attributes")
    @Expose
    private List<PersonAttribute> attributes = new ArrayList<>();

    private Bitmap photo;

    /**
     * 
     * @return
     *     The names
     */
    public List<PersonName> getNames() {
        return names;
    }

    public PersonName getName() {
        if (!getNames().isEmpty()) {
            return getNames().get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param names
     *     The names
     */
    public void setNames(List<PersonName> names) {
        this.names = names;
    }

    public void setName(PersonName personName) {
        names = getNames() != null ? getNames() : new ArrayList<>();
        if (names.isEmpty())
            names.add(personName);
        else
            names.set(0, personName);
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     *     The birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * 
     * @param birthdate
     *     The birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     *
     * @return
     *     The birthdateEstimated
     */
    public boolean getBirthdateEstimated() {
        return birthdateEstimated;
    }

    /**
     *
     * @param birthdateEstimated
     *     The birthdate
     */
    public void setBirthdateEstimated(boolean birthdateEstimated) {
        this.birthdateEstimated = birthdateEstimated;
    }


    /**
     * 
     * @return
     *     The addresses
     */
    public List<PersonAddress> getAddresses() {
        return addresses;
    }

    public PersonAddress getAddress() {
        if (!getAddresses().isEmpty()) {
            return getAddresses().get(0);
        } else {
            return null;
        }
    }

    public PersonAttribute getAttribute() {
        if (!getAttributes().isEmpty()) {
            return getAttributes().get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param addresses
     *     The addresses
     */
    public void setAddresses(List<PersonAddress> addresses) {
        this.addresses = addresses;
    }

    public void setAddress(PersonAddress personAddress) {
        addresses = getAddresses() != null ? getAddresses() : new ArrayList<>();
        if (addresses.isEmpty())
            addresses.add(personAddress);
        else
            addresses.set(0, personAddress);
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public List<PersonAttribute> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     *     The attributes
     */
    public void setAttributes(List<PersonAttribute> attributes) {
        this.attributes = attributes;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public Bitmap getResizedPhoto() {
        return ImageUtils.resizePhoto(this.photo);
    }

    public void setPhoto(Bitmap patientPhoto) {
        this.photo = patientPhoto;
    }
}
