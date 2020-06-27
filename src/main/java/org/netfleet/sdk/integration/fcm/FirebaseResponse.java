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
package org.netfleet.sdk.integration.fcm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
  fieldVisibility = JsonAutoDetect.Visibility.ANY,
  getterVisibility = JsonAutoDetect.Visibility.NONE,
  setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FirebaseResponse implements Serializable {

  @JsonProperty("multicast_id")
  private String multicastId;

  @JsonProperty("success")
  private Integer success;

  @JsonProperty("failure")
  private Integer failure;

  @JsonProperty("canonical_ids")
  private Integer canonicalIds;

  @JsonProperty("results")
  private List<String> results;

  public FirebaseResponse() {
  }

  public FirebaseResponse(String multicastId, Integer success, Integer failure, Integer canonicalIds, List<String> results) {
    this.multicastId = multicastId;
    this.success = success;
    this.failure = failure;
    this.canonicalIds = canonicalIds;
    this.results = results;
  }

  @Override
  public String toString() {
    return "FirebaseResponse [multicastId=" + multicastId + ", success=" + success + ", failure=" + failure+ ", canonicalIds=" + canonicalIds + ", results=" + results + "]";
  }

  public String getMulticastId() {
    return multicastId;
  }

  public void setMulticastId(String multicastId) {
    this.multicastId = multicastId;
  }

  public Integer getSuccess() {
    return success;
  }

  public void setSuccess(Integer success) {
    this.success = success;
  }

  public Integer getFailure() {
    return failure;
  }

  public void setFailure(Integer failure) {
    this.failure = failure;
  }

  public Integer getCanonicalIds() {
    return canonicalIds;
  }

  public void setCanonicalIds(Integer canonicalIds) {
    this.canonicalIds = canonicalIds;
  }

  public List<String> getResults() {
    return results;
  }

  public void setResults(List<String> results) {
    this.results = results;
  }
}
