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

package org.netfleet.sdk.data.report;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class PersonPerformanceReport<T> implements Serializable {

  private T user;

  private Float scheduledTotalDistance;
  private Float scheduledTotalDuration;

  private Float mandatoryTotalDistance;
  private Float mandatoryTotalDuration;

  private Float unscheduledTotalDistance;
  private Float unscheduledTotalDuration;

  private Float totalDistance;
  private Float totalDuration;

  private Integer velocityViolationCount;

  private Integer scheduledVehicleTrackingCount;
  private Integer unscheduledVehicleTrackingCount;
  private Integer mandatoryVehicleTrackingCount;

  private Integer succeededScheduledVehicleTrackingCount;

  public PersonPerformanceReport() {
  }

  public T getUser() {
    return user;
  }

  public void setUser(T user) {
    this.user = user;
  }

  public Float getScheduledTotalDistance() {
    return scheduledTotalDistance;
  }

  public void setScheduledTotalDistance(Float scheduledTotalDistance) {
    this.scheduledTotalDistance = scheduledTotalDistance;
  }

  public Float getScheduledTotalDuration() {
    return scheduledTotalDuration;
  }

  public void setScheduledTotalDuration(Float scheduledTotalDuration) {
    this.scheduledTotalDuration = scheduledTotalDuration;
  }

  public Float getMandatoryTotalDistance() {
    return mandatoryTotalDistance;
  }

  public void setMandatoryTotalDistance(Float mandatoryTotalDistance) {
    this.mandatoryTotalDistance = mandatoryTotalDistance;
  }

  public Float getMandatoryTotalDuration() {
    return mandatoryTotalDuration;
  }

  public void setMandatoryTotalDuration(Float mandatoryTotalDuration) {
    this.mandatoryTotalDuration = mandatoryTotalDuration;
  }

  public Float getUnscheduledTotalDistance() {
    return unscheduledTotalDistance;
  }

  public void setUnscheduledTotalDistance(Float unscheduledTotalDistance) {
    this.unscheduledTotalDistance = unscheduledTotalDistance;
  }

  public Float getUnscheduledTotalDuration() {
    return unscheduledTotalDuration;
  }

  public void setUnscheduledTotalDuration(Float unscheduledTotalDuration) {
    this.unscheduledTotalDuration = unscheduledTotalDuration;
  }

  public Float getTotalDistance() {
    return totalDistance;
  }

  public void setTotalDistance(Float totalDistance) {
    this.totalDistance = totalDistance;
  }

  public Float getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(Float totalDuration) {
    this.totalDuration = totalDuration;
  }

  public Integer getVelocityViolationCount() {
    return velocityViolationCount;
  }

  public void setVelocityViolationCount(Integer velocityViolationCount) {
    this.velocityViolationCount = velocityViolationCount;
  }

  public Integer getScheduledVehicleTrackingCount() {
    return scheduledVehicleTrackingCount;
  }

  public void setScheduledVehicleTrackingCount(Integer scheduledVehicleTrackingCount) {
    this.scheduledVehicleTrackingCount = scheduledVehicleTrackingCount;
  }

  public Integer getUnscheduledVehicleTrackingCount() {
    return unscheduledVehicleTrackingCount;
  }

  public void setUnscheduledVehicleTrackingCount(Integer unscheduledVehicleTrackingCount) {
    this.unscheduledVehicleTrackingCount = unscheduledVehicleTrackingCount;
  }

  public Integer getMandatoryVehicleTrackingCount() {
    return mandatoryVehicleTrackingCount;
  }

  public void setMandatoryVehicleTrackingCount(Integer mandatoryVehicleTrackingCount) {
    this.mandatoryVehicleTrackingCount = mandatoryVehicleTrackingCount;
  }

  public Integer getSucceededScheduledVehicleTrackingCount() {
    return succeededScheduledVehicleTrackingCount;
  }

  public void setSucceededScheduledVehicleTrackingCount(Integer succeededScheduledVehicleTrackingCount) {
    this.succeededScheduledVehicleTrackingCount = succeededScheduledVehicleTrackingCount;
  }
}