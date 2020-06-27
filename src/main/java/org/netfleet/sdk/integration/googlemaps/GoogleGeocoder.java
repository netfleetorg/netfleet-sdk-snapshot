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
package org.netfleet.sdk.integration.googlemaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.netfleet.sdk.geom.Position;
import org.netfleet.sdk.geom.SimplePosition;
import org.netfleet.sdk.geom.crs.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class GoogleGeocoder implements Serializable {

  private final GeocodeParameters parameters;
  private GeoApiContext context;

  public GoogleGeocoder(GeocodeParameters parameters) {
    this.parameters = parameters;
  }

  public Set<Position> geocode()
      throws InterruptedException, ApiException, IOException {
    if (context == null) {
      context = new GeoApiContext.Builder().apiKey(parameters.getApiKey()).build();
    }

    GeocodingResult[] results = GeocodingApi.geocode(context, parameters.getAddress()).await();
    Set<Position> positions = new HashSet<>();
    for (GeocodingResult result: results) {
      LatLng location = result.geometry.location;
      Position position = new SimplePosition(location.lat, location.lng);
      positions.add(position);
    }

    return positions;
  }

  public Set<String> reverse()
      throws InterruptedException, ApiException, IOException {
    if (context == null) {
      context = new GeoApiContext.Builder().apiKey(parameters.getApiKey()).build();
    }

    String coordinate = parameters.getCoordinate();
    String[] coords = coordinate.split(",");
    double lat = Double.parseDouble(coords[1]);
    double lng = Double.parseDouble(coords[0]);
    Position position = new SimplePosition(lat, lng);

    Set<String> addresses = new HashSet<>();
    GeodeticLatitudeAxis latitudeAxis = CoordinateSystemAxes.GEODETIC_LATITUDE;
    GeodeticLongitudeAxis longitudeAxis = CoordinateSystemAxes.GEODETIC_LONGITUDE;
    double latitude = position.getOrdinate(latitudeAxis.getDirection().getOrder());
    double longitude = position.getOrdinate(longitudeAxis.getDirection().getOrder());

    LatLng latLng = new LatLng();
    latLng.lat = latitude;
    latLng.lng = longitude;
    GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latLng).await();

    for (GeocodingResult result: results) {
      addresses.add(result.formattedAddress);
    }

    return addresses;
  }
}
