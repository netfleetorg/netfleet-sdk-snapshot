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
package org.netfleet.sdk.data.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.netfleet.sdk.commons.unit.DirectionUnit;
import org.netfleet.sdk.commons.unit.DistanceUnit;
import org.netfleet.sdk.geom.crs.CrsIdentifier;
import org.netfleet.sdk.geom.geojson.*;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
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
public class GpsEvent implements Serializable {

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

  public GpsEvent() {
  }

  public GpsEvent(Builder b) {
    this.source = b.source;
    this.destination = b.destination;
    this.deliveryMode = b.deliveryMode;
    this.vehicleTrackingId = b.vehicleTrackingId;
    this.vehicleId = b.vehicleId;
    this.driverId = b.driverId;
    this.driverName = b.driverName;
    this.vehiclePlate = b.vehiclePlate;
    this.id = b.id;
    this.index = b.index;
    this.coordinate = b.coordinate;
    this.crsId = b.crsId;
    this.distance = b.distance;
    this.distanceUnit = b.distanceUnit;
    this.duration = b.duration;
    this.durationUnit = b.durationUnit;
    this.velocity = b.velocity;
    this.velocityUnit = b.velocityUnit;
    this.direction = b.direction;
    this.time = b.time;
    this.velocityViolation = b.velocityViolation;
  }

  public static GpsEvent fromGeojsonFeature(GeojsonFeature feature) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(feature);

    JsonNode node = mapper.readTree(json);
    JsonNode propertiesNode = node.get("properties");

    return mapper.readValue(propertiesNode.toString(), GpsEvent.class);
  }

  public GeojsonFeature toGeojsonFeature() {
    if (getCoordinate() == null) {
      throw new IllegalStateException("coordinate property cannot be null.");
    }

    Map<String, Object> properties = new HashMap<>();

    Field[] fields = getClass().getDeclaredFields();
    for (Field field: fields) {
      String name = field.getName();
      Object value;
      try {
        value = field.get(this);
      } catch (IllegalAccessException e) {
        throw new IllegalStateException();
      }

      properties.put(name, value);
    }

    GeojsonFeature feature = new GeojsonFeature();
    feature.setProperties(properties);
    if (getId() != null) {
      feature.setId(getId().toString());
    }

    String[] array = getCoordinate().split(",");
    double lat = Double.valueOf(array[0]);
    double lng = Double.valueOf(array[1]);

    GeojsonCoordinate coordinate = new GeojsonCoordinate(lat, lng);
    GeojsonPoint point = new GeojsonPoint(coordinate);
    if (getCrsId() != null) {
      GeojsonCrs crs = new GeojsonCrs();
      crs.setType("name");

      GeojsonCrsProperties crsProperties = new GeojsonCrsProperties();
      crsProperties.setName(getCrsId().toUrn());

      crs.setProperties(crsProperties);
      point.setCrs(crs);
    }

    feature.setGeometry(point);

    return feature;
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

  /**
   * @version 1.0.0-RELEASE
   * @since 1.0.0-RELEASE
   */
  public static class Builder {
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

    public Builder setSource(EventDestination source) {
      this.source = source;
      return this;
    }

    public Builder setDestination(EventDestination destination) {
      this.destination = destination;
      return this;
    }

    public Builder setDeliveryMode(DeliveryMode deliveryMode) {
      this.deliveryMode = deliveryMode;
      return this;
    }

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setIndex(Long index) {
      this.index = index;
      return this;
    }

    public Builder setVehicleTrackingId(Long vehicleTrackingId) {
      this.vehicleTrackingId = vehicleTrackingId;
      return this;
    }

    public Builder setVehicleId(Long vehicleId) {
      this.vehicleId = vehicleId;
      return this;
    }

    public Builder setVehiclePlate(String vehiclePlate) {
      this.vehiclePlate = vehiclePlate;
      return this;
    }

    public Builder setDriverId(Long driverId) {
      this.driverId = driverId;
      return this;
    }

    public Builder setDriverName(String driverName) {
      this.driverName = driverName;
      return this;
    }

    public Builder setCoordinate(String coordinate) {
      this.coordinate = coordinate;
      return this;
    }

    public Builder setCrsId(CrsIdentifier crsId) {
      this.crsId = crsId;
      return this;
    }

    public Builder setDistance(Double distance) {
      this.distance = distance;
      return this;
    }

    public Builder setDistanceUnit(DistanceUnit distanceUnit) {
      this.distanceUnit = distanceUnit;
      return this;
    }

    public Builder setDuration(Double duration) {
      this.duration = duration;
      return this;
    }

    public Builder setDurationUnit(TimeUnit durationUnit) {
      this.durationUnit = durationUnit;
      return this;
    }

    public Builder setVelocity(Double velocity) {
      this.velocity = velocity;
      return this;
    }

    public Builder setVelocityUnit(String velocityUnit) {
      this.velocityUnit = velocityUnit;
      return this;
    }

    public Builder setVelocityViolation(Boolean velocityViolation) {
      this.velocityViolation = velocityViolation;
      return this;
    }

    public Builder setDirection(DirectionUnit direction) {
      this.direction = direction;
      return this;
    }

    public Builder setTime(Long time) {
      this.time = time;
      return this;
    }

    public GpsEvent build() {
      return new GpsEvent(this);
    }
  }
}
