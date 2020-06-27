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
package org.netfleet.sdk.util.logging;

import org.slf4j.LoggerFactory;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class Logger<T> {

  private final org.slf4j.Logger printf;

  /**
   * Private default constructor.
   */
  private Logger() {
    this.printf = LoggerFactory.getLogger(getClass());
  }

  /**
   * @param classType the class, can be {@literal null}.
   */
  public Logger(final Class<T> classType) {
    if (classType == null) {
      this.printf = LoggerFactory.getLogger(getClass());
    } else {
      this.printf = LoggerFactory.getLogger(classType);
    }
  }

  /**
   * @param buff the Logger, can be {@literal null}.
   */
  public Logger(final org.slf4j.Logger buff) {
    if (buff == null) {
      this.printf = LoggerFactory.getLogger(getClass());
    } else {
      this.printf = buff;
    }
  }

  /**
   * @param <T>       the generic sign.
   * @param classType the class type.
   * @return a new {@link Logger} with given {@link org.slf4j.Logger}.
   */
  public static <T> Logger<T> of(final Class<T> classType) {
    return new Logger<>(classType);
  }

  /**
   * @param <T>    the generic sign.
   * @param logger the slf4j Logger.
   * @return a new {@link Logger} with given {@link org.slf4j.Logger}.
   */
  public static <T> Logger<T> of(final org.slf4j.Logger logger) {
    return new Logger<>(logger);
  }

  /**
   * Method for avoiding {@link NullPointerException}.
   *
   * @param o the object to be stringfy.
   * @return {@link String} if object is present, {@code ""} if object is
   * {@literal null}.
   */
  protected String toBlankIfNull(Object o) {
    return (o == null) ? "" : o.toString();
  }

  /**
   * @param format the format String.
   * @see org.slf4j.Logger#debug(java.lang.String)
   * @see org.slf4j.Logger#isDebugEnabled()
   */
  public void debugIfEnabled(final Object format) {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(toBlankIfNull(format));
    }
  }

  /**
   * @param format the format String.
   * @param arg0   additional first argument to apply format.
   * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
   * @see org.slf4j.Logger#isDebugEnabled()
   */
  public void debugIfEnabled(final Object format, final Object arg0) {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(toBlankIfNull(format), arg0);
    }
  }

  /**
   * @param format    the format String.
   * @param throwable the throwable object.
   * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
   * @see org.slf4j.Logger#isDebugEnabled()
   */
  public void debugIfEnabled(final Object format, final Throwable throwable) {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(toBlankIfNull(format), throwable);
    }
  }

  /**
   * @param format the format String.
   * @param arg0   additional argument to apply format.
   * @param arg1   additional argument to apply format.
   * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
   * @see org.slf4j.Logger#isDebugEnabled()
   */
  public void debugIfEnabled(final Object format, final Object arg0, final Object arg1) {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(toBlankIfNull(format), arg0, arg1);
    }
  }

  /**
   * @param format the format String.
   * @param args   a list of 3 or more arguments.
   * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object...)
   * @see org.slf4j.Logger#isDebugEnabled()
   */
  public void debugIfEnabled(final Object format, final Object... args) {
    if (getLogger().isDebugEnabled()) {
      getLogger().debug(toBlankIfNull(format), args);
    }
  }

  /**
   * @param format the format String.
   * @see org.slf4j.Logger#info(java.lang.String)
   */
  public void info(final Object format) {
    getLogger().info(toBlankIfNull(format));
  }

  /**
   * @param format the format String.
   * @param arg0   additional first argument to apply format.
   * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
   */
  public void info(final Object format, final Object arg0) {
    getLogger().info(toBlankIfNull(format), arg0);
  }

  /**
   * @param format    the format String.
   * @param throwable the throwable object.
   * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
   */
  public void info(final Object format, final Throwable throwable) {
    getLogger().info(toBlankIfNull(format), throwable);
  }

  /**
   * @param format the format String.
   * @param arg0   additional argument to apply format.
   * @param arg1   additional argument to apply format.
   * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
   */
  public void info(final Object format, final Object arg0, final Object arg1) {
    getLogger().info(toBlankIfNull(format), arg0, arg1);
  }

  /**
   * @param format the format String.
   * @param args   a list of 3 or more arguments.
   * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object...)
   */
  public void info(final Object format, final Object... args) {
    getLogger().info(toBlankIfNull(format), args);
  }

  /**
   * @param format the format String.
   * @see org.slf4j.Logger#error(java.lang.String)
   */
  public void error(final Object format) {
    getLogger().error(toBlankIfNull(format));
  }

  /**
   * @param format the format String.
   * @param arg0   additional first argument to apply format.
   * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
   */
  public void error(final Object format, final Object arg0) {
    getLogger().error(toBlankIfNull(format), arg0);
  }

  /**
   * @param format    the format String.
   * @param throwable the throwable object.
   * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
   */
  public void error(final Object format, final Throwable throwable) {
    getLogger().error(toBlankIfNull(format), throwable);
  }

  /**
   * @param format the format String.
   * @param arg0   additional argument to apply format.
   * @param arg1   additional argument to apply format.
   * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
   */
  public void error(final Object format, final Object arg0, final Object arg1) {
    getLogger().error(toBlankIfNull(format), arg0, arg1);
  }

  /**
   * @param format the format String.
   * @param args   a list of 3 or more arguments.
   * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object...)
   */
  public void error(final Object format, final Object... args) {
    getLogger().error(toBlankIfNull(format), args);
  }

  /**
   * @param format the format String.
   * @see org.slf4j.Logger#warn(java.lang.String)
   */
  public void warn(final Object format) {
    getLogger().warn(toBlankIfNull(format));
  }

  /**
   * @param format the format String.
   * @param arg0   additional first argument to apply format.
   * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
   */
  public void warn(final Object format, final Object arg0) {
    getLogger().warn(toBlankIfNull(format), arg0);
  }

  /**
   * @param format    the format String.
   * @param throwable the throwable object.
   * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
   */
  public void warn(final Object format, final Throwable throwable) {
    getLogger().warn(toBlankIfNull(format), throwable);
  }

  /**
   * @param format the format String.
   * @param arg0   additional argument to apply format.
   * @param arg1   additional argument to apply format.
   * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
   */
  public void warn(final Object format, final Object arg0, final Object arg1) {
    getLogger().warn(toBlankIfNull(format), arg0, arg1);
  }

  /**
   * @param format the format String.
   * @param args   a list of 3 or more arguments.
   * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object...)
   */
  public void warn(final Object format, final Object... args) {
    getLogger().warn(toBlankIfNull(format), args);
  }

  public void printf(final LogLevel level, final Object format) {
    switch (level) {
      case DEBUG:
        debugIfEnabled(format);
        break;
      case INFO:
        info(format);
        break;
      case ERROR:
        error(format);
        break;
      case WARN:
        warn(format);
      default:
        break;
    }
  }

  public void printf(final LogLevel level, final Object format, final Object arg0) {
    switch (level) {
      case DEBUG:
        debugIfEnabled(format, arg0);
        break;
      case INFO:
        info(format, arg0);
        break;
      case ERROR:
        error(format, arg0);
        break;
      case WARN:
        warn(format, arg0);
      default:
        break;
    }
  }

  public void printf(final LogLevel level, final Object format, final Throwable throwable) {
    switch (level) {
      case DEBUG:
        debugIfEnabled(format, throwable);
        break;
      case INFO:
        info(format, throwable);
        break;
      case ERROR:
        error(format, throwable);
        break;
      case WARN:
        warn(format, throwable);
      default:
        break;
    }
  }

  public void printf(final LogLevel level,
                     final Object format,
                     final Object arg0,
                     final Object arg1) {
    switch (level) {
      case DEBUG:
        debugIfEnabled(format, arg0, arg1);
        break;
      case INFO:
        info(format, arg0, arg1);
        break;
      case ERROR:
        error(format, arg0, arg1);
        break;
      case WARN:
        warn(format, arg0, arg1);
      default:
        break;
    }
  }

  public void printf(final LogLevel level, final Object format, final Object... args) {
    switch (level) {
      case DEBUG:
        debugIfEnabled(format, args);
        break;
      case INFO:
        info(format, args);
        break;
      case ERROR:
        error(format, args);
        break;
      case WARN:
        warn(format, args);
      default:
        break;
    }
  }

  /**
   * @return org.slf4j.Logger, cannot be {@literal null}.
   */
  public final org.slf4j.Logger getLogger() {
    return printf;
  }

}
