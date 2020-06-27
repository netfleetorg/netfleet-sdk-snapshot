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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.netfleet.sdk.data.json.JacksonBoundingBoxListDeserializer;
import org.netfleet.sdk.data.json.JacksonBoundingBoxListSerializer;

import java.io.Serializable;
import java.util.*;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonSerialize(using = JacksonBoundingBoxListSerializer.class)
@JsonDeserialize(using = JacksonBoundingBoxListDeserializer.class)
public class BoundingBoxList implements Serializable {

  private List<BoundingBox> boundingBoxes;

  public BoundingBoxList() {
    this(null);
  }

  public BoundingBoxList(List<BoundingBox> boundingBoxes) {
    this.boundingBoxes = boundingBoxes;
  }

  public static BoundingBoxList of(Collection<BoundingBox> boundingBoxes) {
    if (boundingBoxes == null)
      return new BoundingBoxList();
    else
      return new BoundingBoxList(new ArrayList<>(boundingBoxes));
  }

  public boolean addBoundingBox(BoundingBox boundingBox) {
    if (boundingBoxes == null)
      boundingBoxes = new ArrayList<>();

    return boundingBoxes.add(boundingBox);
  }

  public boolean removeBoundingBox(BoundingBox boundingBox) {
    if (boundingBoxes == null)
      return false;

    return boundingBoxes.remove(boundingBox);
  }

  public boolean containsBoundingBox(BoundingBox boundingBox) {
    if (boundingBoxes == null)
      return false;

    return boundingBoxes.contains(boundingBox);
  }

  public Iterator<BoundingBox> iterator() {
    if (boundingBoxes == null)
      return Collections.emptyIterator();

    return boundingBoxes.iterator();
  }

  public void setBoundingBoxes(List<BoundingBox> boundingBoxes) {
    this.boundingBoxes = boundingBoxes;
  }

  public List<BoundingBox> getBoundingBoxes() {
    return boundingBoxes;
  }

}
