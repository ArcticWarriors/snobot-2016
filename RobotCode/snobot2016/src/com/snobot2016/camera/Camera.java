package com.snobot2016.camera;

import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * Main implementation for the Axis Camera
 *
 * @author Calvin Do
 * @author Adam Donle
 */

public class Camera implements ICamera
{

   private AxisCamera mCamera;
   private HSLImage mNewImage;
   private boolean mUpdateImageSuccess;
   private double mImageWidth;
   private double mDistanceToTarget;
   

    public Camera(AxisCamera aCamera)
    {
        mCamera = aCamera;
        // mNewImage = aNewImage;, HSLImage aNewImage, boolean
        // aUpdateImageSuccess
        // mUpdateImageSuccess = aUpdateImageSuccess;
    }

    @Override
    public double getYaw()
    {
        if (this.getUpdateImage() == true)
        {
            try
            {
                mImageWidth = mNewImage.getWidth();
            }
            catch (NIVisionException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // TODO Auto-generated method stub
        return 0;

    }


@Override
public double getDistanceToTarget()
{
    double cameraAngleInRadians = Math.toRadians((67/2));
    // TODO Check if this math is correct
    try
    {
        
        mDistanceToTarget =  ((mNewImage.getWidth()/2)/Math.tan(cameraAngleInRadians));
    }
    catch (NIVisionException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return mDistanceToTarget;
}


public HSLImage getImage()
{
    // TODO Auto-generated method stub
    mNewImage = null;
    try
    {
        mNewImage = mCamera.getImage();
    }
    catch (NIVisionException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   
    return mNewImage;
}

    @Override
    public boolean getUpdateImage()
    {
        // TODO Someone with greater knowledge check this out please
        if (mCamera.isFreshImage())
        {
            mUpdateImageSuccess = mCamera.getImage(mNewImage);

        }
        else
        {
            mUpdateImageSuccess = false;
            return mUpdateImageSuccess;
        }

        SmartDashboard.putBoolean(SmartDashBoardNames.sUPDATE_IMAGE_SUCCESS, mUpdateImageSuccess);
        return mUpdateImageSuccess;
    }

}
