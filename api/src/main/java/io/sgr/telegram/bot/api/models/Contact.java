/*
 * Copyright 2017-2020 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.api.models;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a phone contact.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {

    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final Long userId;
    private final String vCard;

    /**
     * @param phoneNumber Contact's phone number
     * @param firstName   Contact's first name
     * @param lastName    Optional. Contact's last name
     * @param userId      Optional. Contact's user identifier in Telegram
     * @param vCard       Optional. Additional data about the contact in the form of a vCard.
     */
    @JsonCreator
    public Contact(
            @JsonProperty("phone_number") String phoneNumber,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("user_id") Long userId,
            @JsonProperty("vcard") final String vCard) {
        checkArgument(!isNullOrEmpty(phoneNumber), "Phone number should be provided.");
        this.phoneNumber = phoneNumber;
        checkArgument(!isNullOrEmpty(firstName), "First name should be provided.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.vCard = vCard;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("user_id")
    public Long getUserId() {
        return userId;
    }

    @JsonProperty("vcard")
    public String getVCard() {
        return vCard;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
