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
package org.netfleet.sdk.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.netfleet.sdk.annotation.Experimental;
import org.netfleet.sdk.annotation.Unstable;
import org.netfleet.sdk.commons.unit.DirectionUnit;
import org.netfleet.sdk.commons.unit.DistanceUnit;
import org.netfleet.sdk.data.notification.DeliveryMode;
import org.netfleet.sdk.data.notification.EventDestination;
import org.netfleet.sdk.geom.crs.CrsIdentifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Unstable
@Experimental
public class MobileProtocol implements BaseProtocol {

  private Map<String, String> content;

  private EventDestination source;
  private EventDestination destination;
  private DeliveryMode deliveryMode = DeliveryMode.PERSISTENT;

  private Long id;
  private Long index;

  private Long vehicleTrackingId;
  private Long vehicleId;
  private String vehiclePlate;
  private Long driverId;
  private String driverName;

  private String coordinate;
  private CrsIdentifier crsId = CrsIdentifier.EPSG4326;

  private Double distance;
  private DistanceUnit distanceUnit = DistanceUnit.METER;

  private Double duration;
  private TimeUnit durationUnit = TimeUnit.SECONDS;

  private Double velocity;
  private String velocityUnit;
  private Boolean velocityViolation;
  private DirectionUnit direction;

  private Long time;

  @Override
  public Map<String, String> getContent() {
    if (content == null) {
      content = new HashMap<>();
    }

    return this.content;
  }

  @Override
  public String setValue(String key, String value) {
    return getContent().put(key, value);
  }

  @Override
  public String getValue(String key) {
    return getContent().get(key);
  }

  @Override
  public Collection<String> getKeys() {
    return getContent().keySet();
  }

  @Override
  public Collection<String> getValues() {
    return getContent().values();
  }

  public EventDestination getSource() {
    return source;
  }

  public void setSource(EventDestination source) {
    this.source = source;
  }

  public EventDestination getDestination() {
    return destination;
  }

  public void setDestination(EventDestination destination) {
    this.destination = destination;
  }

  public DeliveryMode getDeliveryMode() {
    return deliveryMode;
  }

  public void setDeliveryMode(DeliveryMode deliveryMode) {
    this.deliveryMode = deliveryMode;
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

  public String getVehiclePlate() {
    return vehiclePlate;
  }

  public void setVehiclePlate(String vehiclePlate) {
    this.vehiclePlate = vehiclePlate;
  }

  public Long getDriverId() {
    return driverId;
  }

  public void setDriverId(Long driverId) {
    this.driverId = driverId;
  }

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
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

  public DistanceUnit getDistanceUnit() {
    return distanceUnit;
  }

  public void setDistanceUnit(DistanceUnit distanceUnit) {
    this.distanceUnit = distanceUnit;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }

  public TimeUnit getDurationUnit() {
    return durationUnit;
  }

  public void setDurationUnit(TimeUnit durationUnit) {
    this.durationUnit = durationUnit;
  }

  public Double getVelocity() {
    return velocity;
  }

  public void setVelocity(Double velocity) {
    this.velocity = velocity;
  }

  public String getVelocityUnit() {
    return velocityUnit;
  }

  public void setVelocityUnit(String velocityUnit) {
    this.velocityUnit = velocityUnit;
  }

  public Boolean getVelocityViolation() {
    return velocityViolation;
  }

  public void setVelocityViolation(Boolean velocityViolation) {
    this.velocityViolation = velocityViolation;
  }

  public DirectionUnit getDirection() {
    return direction;
  }

  public void setDirection(DirectionUnit direction) {
    this.direction = direction;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }
}
