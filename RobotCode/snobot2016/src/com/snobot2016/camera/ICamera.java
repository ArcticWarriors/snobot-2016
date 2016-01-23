package com.snobot2016.camera;

import edu.wpi.first.wpilibj.image.HSLImage;

/**
 *Main interface for the Axis Camera
 *
 *@author Calvin Do
 *@author Adam Donle
 */

public interface ICamera
{
   double getYaw();
   double getDistanceToTarget();
   HSLImage getImage();
   boolean getUpdateImage();
}
