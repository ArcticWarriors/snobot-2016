package com.snobot2016.autonomous;

import com.snobot.xlib.ISubsystem;

public interface IPositioner extends ISubsystem{
	
	public double getXPosition();
	
	public double getYPosition();
	
	public double getOrientationDegrees();
	
	public double getOrientationRadians();
	
	public void setXPosition(double inputX);
	
	public void setYposition(double inputY);
	
	public void setOrientationRadians(double inputRadians);
	
	public void setOrientationDegrees(double inputDegrees);
}
