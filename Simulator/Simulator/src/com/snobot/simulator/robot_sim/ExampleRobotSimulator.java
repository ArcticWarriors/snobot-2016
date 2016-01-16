package com.snobot.simulator.robot_sim;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;

import edu.wpi.first.wpilibj.Encoder;

public class ExampleRobotSimulator
{

    public ExampleRobotSimulator()
    {
        EncoderWrapper enc = SensorActuatorRegistry.get().getEncoder(4, 5);
        SpeedControllerWrapper sc = SensorActuatorRegistry.get().getSpeedControllers().get(0);
        
        sc.setMotorParameters(100);
        enc.setSpeedController(sc);
        
    }
}
