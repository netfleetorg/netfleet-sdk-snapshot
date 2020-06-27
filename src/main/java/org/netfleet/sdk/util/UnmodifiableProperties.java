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
package org.netfleet.sdk.util;

import org.netfleet.sdk.annotation.Immutable;
import org.netfleet.sdk.annotation.ReadOnly;
import org.netfleet.sdk.annotation.ThreadSafe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@ThreadSafe
@Immutable
@ReadOnly
public class UnmodifiableProperties extends Properties {

  private static final long serialVersionUID = -8273685623752222L;
  private final Properties wrapper;

  public UnmodifiableProperties(final Properties properties) {
    super();

    Assert.requireNonNull(properties);
    this.wrapper = properties;
  }

  @Override
  public synchronized boolean contains(final Object value) {
    return wrapper.contains(value);
  }

  @Override
  public boolean containsKey(final Object key) {
    return this.wrapper.containsKey(key);
  }

  @Override
  public boolean containsValue(final Object value) {
    return this.wrapper.containsValue(value);
  }

  @Override
  public Enumeration<Object> elements() {
    return this.wrapper.elements();
  }

  @Override
  public boolean equals(final Object o) {
    return this.wrapper.equals(o);
  }

  @Override
  public Object get(final Object key) {
    return this.wrapper.get(key);
  }

  @Override
  public String getProperty(final String key,
                            final String defaultValue) {
    return this.wrapper.getProperty(key, defaultValue);
  }

  @Override
  public String getProperty(final String key) {
    return this.wrapper.getProperty(key);
  }

  @Override
  public int hashCode() {
    return this.wrapper.hashCode();
  }

  @Override
  public boolean isEmpty() {
    return this.wrapper.isEmpty();
  }

  @Override
  public Enumeration<Object> keys() {
    return this.wrapper.keys();
  }

  @Override
  public void list(final PrintStream out) {
    this.wrapper.list(out);
  }

  @Override
  public void list(final PrintWriter out) {
    this.wrapper.list(out);
  }

  @Override
  public Enumeration<?> propertyNames() {
    return this.wrapper.propertyNames();
  }

  @Override
  public int size() {
    return this.wrapper.size();
  }

  @Override
  public void store(final OutputStream out,
                    final String comments) throws IOException {
    this.wrapper.store(out, comments);
  }

  @Override
  public void storeToXML(final OutputStream os,
                         final String comment,
                         final String encoding) throws IOException {
    this.wrapper.storeToXML(os, comment, encoding);
  }

  @Override
  public void storeToXML(final OutputStream os,
                         final String comment) throws IOException {
    this.wrapper.storeToXML(os, comment);
  }

  @Override
  public String toString() {
    return this.wrapper.toString();
  }

  @Override
  public Collection<Object> values() {
    return this.wrapper.values();
  }

  @Override
  public synchronized void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Set<Entry<Object, Object>> entrySet() {
    return Collections.unmodifiableSet(super.entrySet());
  }

  @Override
  public Set<Object> keySet() {
    return Collections.unmodifiableSet(super.keySet());
  }

  @Override
  public synchronized void load(final InputStream inStream) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized void loadFromXML(final InputStream in) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized Object put(final Object key,
                                 final Object value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized void putAll(final Map<? extends Object, ? extends Object> t) {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized Object remove(final Object key) {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized Object setProperty(final String key, final String value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized Object clone() {
    return new UnmodifiableProperties(this.wrapper);
  }
}
