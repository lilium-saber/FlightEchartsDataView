package com.mhdx.spbhello.EFtable.FlightEF;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DelayedFlights")
public class FlightDelayed {
    @Id
    private int id;

    private String Year;
    private String Month;
    private String DayofMonth;
    private String DayOfWeek;
    private String DepTime;
    private String CRSDepTime;
    private String ArrTime;
    private String CRSArrTime;
    private String UniqueCarrier;
    private String FlightNum;
    private String TailNum;
    private String ActualElapsedTime;
    private String CRSElapsedTime;
    private String AirTime;
    private String ArrDelay;
    private String DepDelay;
    private String Origin;
    private String Dest;
    private String Distance;
    private String TaxiIn;
    private String TaxiOut;
    private String Cancelled;
    private String CancellationCode;
    private String Diverted;
    private String CarrierDelay;
    private String WeatherDelay;
    private String NASDelay;
    private String SecurityDelay;
    private String LateAircraftDelay;

    public FlightDelayed() {
    }

    public FlightDelayed(int id, String Year, String Month, String DayofMonth, String DayOfWeek, String DepTime, String CRSDepTime, String ArrTime, String CRSArrTime, String UniqueCarrier, String FlightNum, String TailNum, String ActualElapsedTime, String CRSElapsedTime, String AirTime, String ArrDelay, String DepDelay, String Origin, String Dest, String Distance, String TaxiIn, String TaxiOut, String Cancelled, String CancellationCode, String Diverted, String CarrierDelay, String WeatherDelay, String NASDelay, String SecurityDelay, String LateAircraftDelay) {
        this.id = id;
        this.Year = Year;
        this.Month = Month;
        this.DayofMonth = DayofMonth;
        this.DayOfWeek = DayOfWeek;
        this.DepTime = DepTime;
        this.CRSDepTime = CRSDepTime;
        this.ArrTime = ArrTime;
        this.CRSArrTime = CRSArrTime;
        this.UniqueCarrier = UniqueCarrier;
        this.FlightNum = FlightNum;
        this.TailNum = TailNum;
        this.ActualElapsedTime = ActualElapsedTime;
        this.CRSElapsedTime = CRSElapsedTime;
        this.AirTime = AirTime;
        this.ArrDelay = ArrDelay;
        this.DepDelay = DepDelay;
        this.Origin = Origin;
        this.Dest = Dest;
        this.Distance = Distance;
        this.TaxiIn = TaxiIn;
        this.TaxiOut = TaxiOut;
        this.Cancelled = Cancelled;
        this.CancellationCode = CancellationCode;
        this.Diverted = Diverted;
        this.CarrierDelay = CarrierDelay;
        this.WeatherDelay = WeatherDelay;
        this.NASDelay = NASDelay;
        this.SecurityDelay = SecurityDelay;
        this.LateAircraftDelay = LateAircraftDelay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public String getDayofMonth() {
        return DayofMonth;
    }

    public void setDayofMonth(String DayofMonth) {
        this.DayofMonth = DayofMonth;
    }

    public String getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(String DayOfWeek) {
        this.DayOfWeek = DayOfWeek;
    }

    public String getDepTime() {
        return DepTime;
    }

    public void setDepTime(String DepTime) {
        this.DepTime = DepTime;
    }

    public String getCRSDepTime() {
        return CRSDepTime;
    }

    public void setCRSDepTime(String CRSDepTime) {
        this.CRSDepTime = CRSDepTime;
    }

    public String getArrTime() {
        return ArrTime;
    }

    public void setArrTime(String ArrTime) {
        this.ArrTime = ArrTime;
    }

    public String getCRSArrTime() {
        return CRSArrTime;
    }

    public void setCRSArrTime(String CRSArrTime) {
        this.CRSArrTime = CRSArrTime;
    }

    public String getUniqueCarrier() {
        return UniqueCarrier;
    }

    public void setUniqueCarrier(String UniqueCarrier) {
        this.UniqueCarrier = UniqueCarrier;
    }

    public String getFlightNum() {
        return FlightNum;
    }

    public void setFlightNum(String FlightNum) {
        this.FlightNum = FlightNum;
    }

    public String getTailNum() {
        return TailNum;
    }

    public void setTailNum(String TailNum) {
        this.TailNum = TailNum;
    }

    public String getActualElapsedTime() {
        return ActualElapsedTime;
    }

    public void setActualElapsedTime(String ActualElapsedTime) {
        this.ActualElapsedTime = ActualElapsedTime;
    }

    public String getCRSElapsedTime() {
        return CRSElapsedTime;
    }

    public void setCRSElapsedTime(String CRSElapsedTime) {
        this.CRSElapsedTime = CRSElapsedTime;
    }

    public String getAirTime() {
        return AirTime;
    }

    public void setAirTime(String AirTime) {
        this.AirTime = AirTime;
    }

    public String getArrDelay() {
        return ArrDelay;
    }

    public void setArrDelay(String ArrDelay) {
        this.ArrDelay = ArrDelay;
    }

    public String getDepDelay() {
        return DepDelay;
    }

    public void setDepDelay(String DepDelay) {
        this.DepDelay = DepDelay;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String Dest) {
        this.Dest = Dest;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public String getTaxiIn() {
        return TaxiIn;
    }

    public void setTaxiIn(String TaxiIn) {
        this.TaxiIn = TaxiIn;
    }

    public String getTaxiOut() {
        return TaxiOut;
    }

    public void setTaxiOut(String TaxiOut) {
        this.TaxiOut = TaxiOut;
    }

    public String getCancelled() {
        return Cancelled;
    }

    public void setCancelled(String Cancelled) {
        this.Cancelled = Cancelled;
    }

    public String getCancellationCode() {
        return CancellationCode;
    }

    public void setCancellationCode(String CancellationCode) {
        this.CancellationCode = CancellationCode;
    }

    public String getDiverted() {
        return Diverted;
    }

    public void setDiverted(String Diverted) {
        this.Diverted = Diverted;
    }

    public String getCarrierDelay() {
        return CarrierDelay;
    }

    public void setCarrierDelay(String CarrierDelay) {
        this.CarrierDelay = CarrierDelay;
    }

    public String getWeatherDelay() {
        return WeatherDelay;
    }

    public void setWeatherDelay(String WeatherDelay) {
        this.WeatherDelay = WeatherDelay;
    }

    public String getNASDelay() {
        return NASDelay;
    }

    public void setNASDelay(String NASDelay) {
        this.NASDelay = NASDelay;
    }

    public String getSecurityDelay() {
        return SecurityDelay;
    }

    public void setSecurityDelay(String SecurityDelay) {
        this.SecurityDelay = SecurityDelay;
    }

    public String getLateAircraftDelay() {
        return LateAircraftDelay;
    }

    public void setLateAircraftDelay(String LateAircraftDelay) {
        this.LateAircraftDelay = LateAircraftDelay;
    }

}
