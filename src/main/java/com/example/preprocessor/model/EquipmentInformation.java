package com.example.preprocessor.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class EquipmentInformation implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3989333452261627702L;
	@Id
	@NotNull
    private String id;
	private char equipmentTypeCode;
    private String equipmentCode;
    private String bookingDesignatorText;
    private String bookingModifierText;
    private String aircraftConfigurationText;

    public EquipmentInformation(){}

    /**
     * Shallow copy constructor
     * @param other
     */
    public EquipmentInformation(EquipmentInformation other)
    {
        this.equipmentTypeCode          = other.equipmentTypeCode;
        this.equipmentCode              = other.equipmentCode;
        this.bookingDesignatorText      = other.bookingDesignatorText;
        this.bookingModifierText        = other.bookingModifierText;
        this.aircraftConfigurationText  = other.aircraftConfigurationText;
    }

    /**
     *
     * @param equipmentTypeCode
     * @param equipmentCode
     * @param bookingDesignatorText
     * @param bookingModifierText
     */

    public char getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(char equipmentTypeCode)
    {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode)
    {
        this.equipmentCode = equipmentCode;
    }

    public String getBookingDesignatorText() {
        return bookingDesignatorText;
    }

    public void setBookingDesignatorText(String bookingDesignatorText)
    {
        this.bookingDesignatorText = bookingDesignatorText;
    }

    public String getBookingModifierText() {
        return bookingModifierText;
    }

    public void setBookingModifierText(String bookingModifierText)
    {
        this.bookingModifierText = bookingModifierText;
    }

    public String getAircraftConfigurationText() {
        return aircraftConfigurationText;
    }

    public void setAircraftConfigurationText(String aircraftConfigurationText)
    {
        this.aircraftConfigurationText = aircraftConfigurationText;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append(equipmentTypeCode + "/" + equipmentCode + " ");
        if (bookingDesignatorText != null && !bookingDesignatorText.isEmpty()) result.append(bookingDesignatorText);
        if (bookingModifierText != null && !bookingModifierText.isEmpty()) result.append("/" + bookingModifierText);
        if (aircraftConfigurationText != null && !aircraftConfigurationText.isEmpty()) result.append("." + aircraftConfigurationText);
        return result.toString().trim();
    }
}
