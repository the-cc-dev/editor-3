/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.editor.ui.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

/**
 * Lazy-loading and caching resource provider.
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public final class Icons {

  public enum Size {
    SMALL,
    LARGE
  };

  private static final Map<Integer, ImageIcon> cache;

  static {
    cache = new HashMap<>();
  }

  public static final ImageIcon getIcon(String name, Size size) {
    int key = 0;
    key ^= name.hashCode();
    key ^= size.hashCode() * 31;
    if (!cache.containsKey(key)) {
      cache.put(key, getImageIcon(name, size));
    }
    return cache.get(key);
  }

  public static final ImageIcon getIcon(String name) {
    return getIcon(name, Size.SMALL);
  }

  public static final ImageIcon getSmallIcon(String name) {
    return getIcon(name, Size.SMALL);
  }

  public static final ImageIcon getLargeIcon(String name) {
    return getIcon(name, Size.LARGE);
  }

  private static ImageIcon getImageIcon(String key, Size size) {
    final String path = String.format("/editor/%s/%s.png",
            size.name().toLowerCase(), key);
    final ImageIcon icon = new ImageIcon(Icons.class.getResource(path));
    return icon;
  }
}
