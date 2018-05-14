package com.example.preprocessor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

/**
 * This class will have the Leg and EquipmentLine Information from SSM/SSIM content
 */
public class LegInformation implements Serializable{

    // NOTE: Each of the LocalTime instances are only minute-accurate.

    /**
	 * 
	 */
	private static final long serialVersionUID = 4274806273246934365L;
	
	@Id
	@NotNull
    private String id;

	private int                     order;

    private String                  departureCityCode;
    private LocalTime               departureTime;                // a.k.a. aircraft departure time
    private LocalTime               passengerDepartureTime;
    private int                     departureDateDiscrepancyNumber = 0;
    private String                  departureTerminalCode;

    private String                  arrivalCityCode;
    private LocalTime               arrivalTime;                  // a.k.a. aircraft arrival time
    private LocalTime               passengerArrivalTime;
    private int                     arrivalDateAdjustmentNumber    = 0;
    private String                  arrivalTerminalCode;
    private EquipmentInformation    equipmentInformation;
    private String                  mctCode;
    private char                    trafficRestrictionCode;

    //private String errorMessage = SKDConstants.SUCCESS;

    public LegInformation(){}

    /**
     * Shallow copy constructor
     * @param other
     */
    public LegInformation(LegInformation other)
    {
        this.order                              = other.order;
        this.departureCityCode                  = other.departureCityCode;
        this.departureTime                      = other.departureTime;
        this.passengerDepartureTime             = other.passengerDepartureTime;
        this.departureDateDiscrepancyNumber     = other.departureDateDiscrepancyNumber;
        this.departureTerminalCode              = other.departureTerminalCode;
        this.arrivalCityCode                    = other.arrivalCityCode;
        this.arrivalTime                        = other.arrivalTime;
        this.passengerArrivalTime               = other.passengerArrivalTime;
        this.arrivalDateAdjustmentNumber        = other.arrivalDateAdjustmentNumber;
        this.arrivalTerminalCode                = other.arrivalTerminalCode;
        this.equipmentInformation               = other.equipmentInformation;
        this.mctCode                            = other.mctCode;
        this.trafficRestrictionCode             = other.trafficRestrictionCode;
    }

    public EquipmentInformation getEquipmentInformation() {
        return equipmentInformation;
    }

    public void setEquipmentInformation(EquipmentInformation equipmentInformation) {
        this.equipmentInformation = equipmentInformation;
    }

    public String getDepartureCityCode() {
        return departureCityCode;
    }

    public String getArrivalCityCode() {
        return arrivalCityCode;
    }

    @JsonIgnore
    public String getCityPair() { return departureCityCode + arrivalCityCode; }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getPassengerDepartureTime() {
        return passengerDepartureTime;
    }

    public LocalTime getPassengerArrivalTime() {
        return passengerArrivalTime;
    }

    public String getDepartureTerminalCode() {
        return departureTerminalCode;
    }

    public String getArrivalTerminalCode() {
        return arrivalTerminalCode;
    }

    public int getDepartureDateDiscrepancyNumber() {
        return departureDateDiscrepancyNumber;
    }

    public int getArrivalDateAdjustmentNumber() {
        return arrivalDateAdjustmentNumber;
    }

    public char getTrafficRestrictionCode() {
        return trafficRestrictionCode;
    }

    public String getMctCode() {
        return mctCode;
    }

    public int getOrder() {
        return order;
    }

    private static DateTimeFormatter hmmTimeFormatter  = DateTimeFormatter.ofPattern("hmma");

    public static String timeToString(LocalTime localTime, int discrepancy) {

        StringBuilder result = new StringBuilder();

        result.append(localTime.format(hmmTimeFormatter).toLowerCase());
        result.deleteCharAt(result.length()-1);                 // delete the "m" from am/pm

        if (discrepancy == 0) {}
        else if (discrepancy < 0) result.append('-');
        else if (discrepancy == 1) result.append('+');
        else result.append(discrepancy);

        return result.toString();
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s-%s", departureCityCode, arrivalCityCode));
        if (departureTime != null) result.append(" " + timeToString(departureTime, departureDateDiscrepancyNumber));
        if (arrivalTime != null) result.append(" " + timeToString(arrivalTime, arrivalDateAdjustmentNumber));
        if (equipmentInformation != null) result.append(" " + equipmentInformation.toString());
        return result.toString();
    }

}
