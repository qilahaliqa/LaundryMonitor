package com.example.laundrymonitor.Model;

public class Machine2 {

    private String kolej;
    private String machineSlot;
    private String slot;
    private String id;

    public Machine2(){

    }

    public Machine2(String kolej, String machineSlot, String slot, String id) {
        this.kolej = kolej;
        this.machineSlot = machineSlot;
        this.slot = slot;
        this.id = id;
    }

    public String getKolej() {
        return kolej;
    }

    public void setKolej(String kolej) {
        this.kolej = kolej;
    }

    public String getMachineSlot() {
        return machineSlot;
    }

    public void setMachineSlot(String machineSlot) {
        this.machineSlot = machineSlot;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
