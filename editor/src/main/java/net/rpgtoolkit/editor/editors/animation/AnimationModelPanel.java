/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.editor.editors.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.rpgtoolkit.common.assets.Animation;
import net.rpgtoolkit.common.utilities.CoreProperties;
import net.rpgtoolkit.editor.ui.AbstractModelPanel;
import net.rpgtoolkit.editor.utilities.Gui;

/**
 *
 * @author Joshua Michael Daly
 */
public class AnimationModelPanel extends AbstractModelPanel {
  
  private final JComboBox soundEffectComboBox;
  private final JLabel soundEffectLabel;
  
  private final JSpinner widthSpinner;
  private final JLabel widthLabel;
  
  private final JSpinner heightSpinner;
  private final JLabel heightLabel;
  
  private final JSpinner frameRateSpinner;
  private final JLabel frameRateLabel;
  
  private final Animation animation;
  
  public AnimationModelPanel(Animation model) {
    ///
    /// super
    ///
    super(model);
    ///
    /// animation
    ///
    this.animation = model;
    ///
    /// soundEffectComboBox
    ///
    File directory = new File(System.getProperty("project.path") 
            + CoreProperties.getProperty("toolkit.directory.media") 
            + File.separator);
    String[] exts = new String[] {"wav", "mp3"};
    soundEffectComboBox = Gui.getFileListJComboBox(directory, exts, true);
    soundEffectComboBox.setSelectedItem(animation.getSoundEffect());
    soundEffectComboBox.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        animation.setSoundEffect((String)soundEffectComboBox.getSelectedItem());
      }

    });
    ///
    /// widthSpinner
    ///
    widthSpinner = getJSpinner(animation.getAnimationWidth());
    widthSpinner.setModel(new SpinnerNumberModel(animation.getAnimationWidth(), 10, 1000, 1));
    widthSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        long value = ((Double)widthSpinner.getValue()).longValue();
        
        if (value > 0 && value != animation.getAnimationWidth()) {
          animation.setAnimationWidth(value);
        } else {
          widthSpinner.setValue(((Long)animation.getAnimationWidth()).intValue());
        }
      }
    });
    ///
    /// heightSpinner
    ///
    heightSpinner = getJSpinner(animation.getAnimationHeight());
    heightSpinner.setModel(new SpinnerNumberModel(animation.getAnimationHeight(), 10, 1000, 1));
    heightSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        long value = ((Double)heightSpinner.getValue()).longValue();
        
        if (value > 0 && value != animation.getAnimationHeight()) {
          animation.setAnimationHeight(value);
        } else {
          heightSpinner.setValue(((Long)animation.getAnimationHeight()).intValue());
        }
      }
    });
    ///
    /// frameRateSpinner
    ///
    frameRateSpinner = getJSpinner(animation.getFrameRate());
    frameRateSpinner.setModel(new SpinnerNumberModel(animation.getFrameRate(), 0, 100, 0.01));
    frameRateSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        double value = (double) frameRateSpinner.getValue();
        
        if (value > 0 && value != animation.getFrameRate()) {
          animation.setFramRate(value);
        } else {
          frameRateSpinner.setValue(animation.getFrameRate());
        }
      }
    });
    ///
    /// this
    ///
    horizontalGroup.addGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(soundEffectLabel = getJLabel("Sound Effect"))
                    .addComponent(widthLabel = getJLabel("Width"))
                    .addComponent(heightLabel = getJLabel("Height"))
                    .addComponent(frameRateLabel = getJLabel("Frame Rate")));
    
    horizontalGroup.addGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(soundEffectComboBox)
                    .addComponent(widthSpinner)
                    .addComponent(heightSpinner)
                    .addComponent(frameRateSpinner));
    
    layout.setHorizontalGroup(horizontalGroup);
    
    verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(soundEffectLabel).addComponent(soundEffectComboBox));
    
    verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(widthLabel).addComponent(widthSpinner));
    
    verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(heightLabel).addComponent(heightSpinner));
    
    verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(frameRateLabel).addComponent(frameRateSpinner));
  
    layout.setVerticalGroup(verticalGroup);
  }

}
