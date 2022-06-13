package edu.psu.ist440.USAmeriCoin.model;

import java.io.Serializable;

/**
 * ReportKPI Object
 * Key Performance Indicators for Reporting
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

public class ReportKPI implements Serializable {
    private long assignedCount = 0;
    private float assignedAmount = 0;
    private long totalCount = 0;
    private float totalAmount = 0;
    private long unassignedCount = 0;
    private float unassignedAmount = 0;
    private long exceedingSLACount = 0;
    private float businessWalletAmount = 0;
    private float vehicleWalletAmount = 0;
    private float personalWalletAmount = 0;

    private float julWalletAmount = 0;
    private float augWalletAmount = 0;
    private float sepWalletAmount = 0;
    private float octWalletAmount = 0;
    private float novWalletAmount = 0;
    private float decWalletAmount = 0;


    public long getAssignedCount() {
        return assignedCount;
    }

    public void setAssignedCount(long assignedCount) {
        this.assignedCount = assignedCount;
    }

    public float getAssignedAmount() {
        return assignedAmount;
    }

    public void setAssignedAmount(float assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getUnassignedCount() {
        return unassignedCount;
    }

    public void setUnassignedCount(long unassignedCount) {
        this.unassignedCount = unassignedCount;
    }

    public long getExceedingSLACount() {
        return exceedingSLACount;
    }

    public void setExceedingSLACount(long exceedingSLACount) {
        this.exceedingSLACount = exceedingSLACount;
    }

    public float getUnassignedAmount() {
        return unassignedAmount;
    }

    public void setUnassignedAmount(float unassignedAmount) {
        this.unassignedAmount = unassignedAmount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public float getBusinessWalletAmount() {
        return businessWalletAmount;
    }

    public void setBusinessWalletAmount(float businessWalletAmount) {
        this.businessWalletAmount = businessWalletAmount;
    }

    public float getVehicleWalletAmount() {
        return vehicleWalletAmount;
    }

    public void setVehicleWalletAmount(float vehicleWalletAmount) {
        this.vehicleWalletAmount = vehicleWalletAmount;
    }

    public float getPersonalWalletAmount() {
        return personalWalletAmount;
    }

    public void setPersonalWalletAmount(float personalWalletAmount) {
        this.personalWalletAmount = personalWalletAmount;
    }

    public float getJulWalletAmount() {
        return julWalletAmount;
    }

    public void setJulWalletAmount(float julWalletAmount) {
        this.julWalletAmount = julWalletAmount;
    }

    public float getAugWalletAmount() {
        return augWalletAmount;
    }

    public void setAugWalletAmount(float augWalletAmount) {
        this.augWalletAmount = augWalletAmount;
    }

    public float getSepWalletAmount() {
        return sepWalletAmount;
    }

    public void setSepWalletAmount(float sepWalletAmount) {
        this.sepWalletAmount = sepWalletAmount;
    }

    public float getOctWalletAmount() {
        return octWalletAmount;
    }

    public void setOctWalletAmount(float octWalletAmount) {
        this.octWalletAmount = octWalletAmount;
    }

    public float getNovWalletAmount() {
        return novWalletAmount;
    }

    public void setNovWalletAmount(float novWalletAmount) {
        this.novWalletAmount = novWalletAmount;
    }

    public float getDecWalletAmount() {
        return decWalletAmount;
    }

    public void setDecWalletAmount(float decWalletAmount) {
        this.decWalletAmount = decWalletAmount;
    }
}
