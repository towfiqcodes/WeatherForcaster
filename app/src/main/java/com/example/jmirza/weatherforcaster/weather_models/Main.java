
package com.example.jmirza.weatherforcaster.weather_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    public Double getTemp() {
        return temp;
    }

    public double getTempCelcius() {
        return temp-273.0;
    }
    public Double getTempFahrenheit() {
        return ((temp-273.0)*1.8)+32.0;
    }
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }
    public Double getTempMinCelcius() {
        return tempMin-273.0;
    }
    public Double getTempMinFahrenheit() {
        return ((tempMin-273.0)*1.8)+32.0;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }
    public Double getTempMaxCelcius() {
        return tempMax-273.0;
    }
    public Double getTempMaxFahrenheit() {
        return ((tempMax-273.0)*1.8)+32.0;
    }


    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
