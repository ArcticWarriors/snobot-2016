package com.snobot.simulator.robot_sim;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;

public class ExampleRobotSimulator
{

    public ExampleRobotSimulator()
    {
        EncoderWrapper enc = SensorActuatorRegistry.get().getEncoder(4, 5);
        SpeedControllerWrapper sc = SensorActuatorRegistry.get().getSpeedControllers().get(0);
        
        sc.setMotorParameters(7000);
        enc.setSpeedController(sc);
        
    }
}
