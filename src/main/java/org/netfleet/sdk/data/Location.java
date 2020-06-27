/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright (C) 2020 Terra Yazılım Ltd Şti - All Rights Reserved
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Please contact Terra Yazılım, Konya Teknoloji Geliştirme Bölgesi
 *  Büyük Kayacık Mah. 101. Cad. No:2 42250 - Selçuklu Konya or visit
 *  www.terrayazilim.com.tr if you need additional information or have
 *  any questions.
 *
 */
package org.netfleet.sdk.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.netfleet.sdk.data.json.JacksonLocationDeserializer;
import org.netfleet.sdk.data.json.JacksonLocationSerializer;
import org.netfleet.sdk.geom.crs.CrsIdentifier;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonSerialize(using = JacksonLocationSerializer.class)
@JsonDeserialize(using = JacksonLocationDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {

  private Long id;
  private Long index;

  private Long vehicleTrackingId;

  private String vehiclePlate;
  private Long vehicleId;

  private String driverName;
  private Long driverId;

  private String coordinate;
  private CrsIdentifier crsId = CrsIdentifier.EPSG4326;

  private Double distance;
  private Double duration;
  private Double velocity;
  private Boolean velocityViolation = false;
  private Long time;


  // ~~~ device properties ~~~ //
  private String localDateTime;
  private String deviceId;
  private Float speed;
  private Float course;
  private String locale;

  public Location() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIndex() {
    return index;
  }

  public void setIndex(Long index) {
    this.index = index;
  }

  public Long getVehicleTrackingId() {
    return vehicleTrackingId;
  }

  public void setVehicleTrackingId(Long vehicleTrackingId) {
    this.vehicleTrackingId = vehicleTrackingId;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Long getDriverId() {
    return driverId;
  }

  public void setDriverId(Long driverId) {
    this.driverId = driverId;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }

  public CrsIdentifier getCrsId() {
    return crsId;
  }

  public void setCrsId(CrsIdentifier crsId) {
    this.crsId = crsId;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }

  public Double getVelocity() {
    return velocity;
  }

  public void setVelocity(Double velocity) {
    this.velocity = velocity;
  }

  public Boolean getVelocityViolation() {
    return velocityViolation;
  }

  public void setVelocityViolation(Boolean velocityViolation) {
    this.velocityViolation = velocityViolation;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String getDriverName() {
    return driverName;
  }

  public void setVehiclePlate(String vehiclePlate) {
    this.vehiclePlate = vehiclePlate;
  }

  public String getVehiclePlate() {
    return vehiclePlate;
  }

  public String getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(String localDateTime) {
    this.localDateTime = localDateTime;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public Float getSpeed() {
    return speed;
  }

  public void setSpeed(Float speed) {
    this.speed = speed;
  }

  public Float getCourse() {
    return course;
  }

  public void setCourse(Float course) {
    this.course = course;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
