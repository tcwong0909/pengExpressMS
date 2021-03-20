package com.tcwong.pengms.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Contact implements Serializable {
    private Integer contactid;

    private Integer fkTruckid;

    private Integer fkDriverid;

    private Truck truck;

    private Driver driver;

    private TruckTeam truckTeam;
}
