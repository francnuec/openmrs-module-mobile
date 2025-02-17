/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.mobile.utilities;

import android.text.TextUtils;

import androidx.collection.ArraySet;

import org.openmrs.mobile.models.Patient;
import org.openmrs.mobile.models.Person;
import org.openmrs.mobile.models.PersonAddress;
import org.openmrs.mobile.models.PersonName;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.collection.ArraySet;

public class PatientMerger {

    public Patient mergePatient(Patient oldPatient, Patient newPatient) {
        oldPatient = mergePatientsPerson(oldPatient, newPatient);
        oldPatient.setId(newPatient.getId());
        Set<Long> encList = new ArraySet<>();
        String oldEnc = oldPatient.getEncounters();
        if (oldEnc != null) {
            for (String s : oldEnc.split(","))
                if (!TextUtils.isEmpty(s))
                    encList.add(Long.parseLong(s));
        }
        String newEnc = newPatient.getEncounters();
        if (newEnc != null) {
            for (String s : newEnc.split(","))
                if (!TextUtils.isEmpty(s))
                    encList.add(Long.parseLong(s));
        }

        oldPatient.setEncounters("");
        for (Long enc : encList) {
            oldPatient.addEncounters(enc);
        }
        return oldPatient;
    }

    private Patient mergePatientsPerson(Patient oldPatient, Patient newPatient) {
        if (oldPatient != null && newPatient != null) {
            oldPatient.setName(mergePersonNames(oldPatient.getName(), newPatient.getName()));
            oldPatient.setAddress(mergePersonAddress(oldPatient.getAddress(), newPatient.getAddress()));
            oldPatient.setGender(getNewValueIfOldIsNull(oldPatient.getGender(), newPatient.getGender()));
            oldPatient.setBirthdate(getNewValueIfOldIsNull(oldPatient.getBirthdate(), newPatient.getBirthdate()));
        }

        return oldPatient != null ? oldPatient : newPatient;
    }

    private PersonAddress mergePersonAddress(PersonAddress oldAddress, PersonAddress newAddress) {
        if (oldAddress != null && newAddress != null) {
            oldAddress.setAddress1(getNewValueIfOldIsNull(oldAddress.getAddress1(), newAddress.getAddress1()));
            oldAddress.setAddress2(getNewValueIfOldIsNull(oldAddress.getAddress2(), newAddress.getAddress2()));
            oldAddress.setCityVillage(getNewValueIfOldIsNull(oldAddress.getCityVillage(), newAddress.getCityVillage()));
            oldAddress.setCountry(getNewValueIfOldIsNull(oldAddress.getCountry(), newAddress.getCountry()));
            oldAddress.setPostalCode(getNewValueIfOldIsNull(oldAddress.getPostalCode(), newAddress.getPostalCode()));
            oldAddress.setStateProvince(getNewValueIfOldIsNull(oldAddress.getStateProvince(), newAddress.getStateProvince()));
        }

        return oldAddress != null ? oldAddress : newAddress;
    }

    private PersonName mergePersonNames(PersonName oldName, PersonName newName) {
        if (oldName != null && newName != null) {
            oldName.setGivenName(getNewValueIfOldIsNull(oldName.getGivenName(), newName.getGivenName()));
            oldName.setMiddleName(getNewValueIfOldIsNull(oldName.getMiddleName(), newName.getMiddleName()));
            oldName.setFamilyName(getNewValueIfOldIsNull(oldName.getFamilyName(), newName.getFamilyName()));
        }

        return oldName != null ? oldName : newName;
    }

    private String getNewValueIfOldIsNull(String oldValue, String newValue) {
        if(!StringUtils.notNull(oldValue)
                || (TextUtils.isEmpty(oldValue) && StringUtils.notNull(newValue))){
            return newValue;
        }
        return oldValue;
    }
}
