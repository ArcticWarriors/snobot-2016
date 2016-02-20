'''
Created on Jan 29, 2016

@author: 1335draco
'''

import numpy
import time
import os
import matplotlib.pyplot as plt
import Tkinter, tkFileDialog
from read_csv_file import load_csv_file


def parse_datetime(time_string):

    # strptime has no option for milliseconds, so ignore the last 3 characters
    time_struct = time.strptime(time_string[:-3], "%Y%m%d_%H%M%S")

    hour = time_struct.tm_hour
    minute = time_struct.tm_min
    sec = time_struct.tm_sec
    msec = int(time_string[-3:])  # ms are the last three columns

    return (msec + (sec * 1000) + (minute * 60000) + (hour * 3600000)) / 1000.0


def parse_drivemode(value):

    if value == "Tank":
        return 0
    else:
        return 1


def plot_driver_joysticks(converted_dict):

    print converted_dict.keys()

    tank_right = converted_dict["RightMotorSpeed"]
    tank_left = converted_dict["LeftMotorSpeed"]

    x_axis = numpy.arange(len(tank_right))

    plt.subplot(2, 1, 1)
    plt.title('Driver Joysticks')
    plt.ylabel('Tank Right')
    plt.scatter(x_axis, tank_right, c=tank_right, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors='None')
    plt.colorbar()

    plt.subplot(2, 1, 2)
    plt.ylabel('Tank Left')
    plt.scatter(x_axis, tank_left, c=tank_left, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors = "None")
    plt.colorbar()
    
def plot_encoders(converted_dict):
    left_encoder = converted_dict["LeftEncoderDistance"]
    right_encoder = converted_dict["RightEncoderDistance"]
    
    x_axis = numpy.arange(len(left_encoder))
    
    plt.subplot(2,1,1)
    plt.title("Encoder Distances")
    plt.ylabel("Left Encoder")
    plt.scatter(x_axis, left_encoder, c = "g", marker = "o", edgecolors = 'None')
    
    plt.subplot(2,1,2)
    plt.ylabel("Right Encoder")
    plt.scatter(x_axis, right_encoder, c = "g", marker = "o", edgecolors = 'None')

def plot_scale_tilt(converted_dict):
    scale_tilt_motor_speed = converted_dict["ScaleTiltMotorSpeed"]
    scale_tilt_angle = converted_dict["ScaleTiltAngle"]
    
    x_axis = numpy.arange(len(scale_tilt_motor_speed))
#     x_axis2 = numpy.arange(90)
    
    plt.subplot(4,1,1)
    plt.title("Scaling")
    plt.ylabel("Scaling Motor Speed")
    plt.scatter(x_axis, scale_tilt_motor_speed, c=scale_tilt_motor_speed, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors = "None")
    
    plt.subplot(4,1,2)
    plt.ylabel("Angle")
    plt.scatter(x_axis, scale_tilt_angle, c=scale_tilt_angle, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors = "None")
    
    plot_scaling_mechanism(converted_dict)

def plot_extension(converted_dict):
#     extension_motor_speed = converted_dict["ScaleExtensionMotorSpeed"]
    extension_percentage = converted_dict["PercentageExtended"]
    extension_pot_voltage = converted_dict["ExtensionPotVoltage"]
    
    x_axis = numpy.arange(len(extension_pot_voltage))
    
    plt.subplot(3,1,1)
    plt.title("Extension")
    plt.ylabel("Percentage")
    plt.scatter(x_axis, extension_percentage, c=extension_percentage, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors = "None")
    
    plt.subplot(3,1,2)
    plt.ylabel("Voltage")
    plt.scatter(x_axis, extension_pot_voltage, c=extension_pot_voltage, marker="o", cmap=plt.get_cmap("gist_rainbow_r"), edgecolors = "None")
    
    
#     plt.subplot(3,1,3)
    
def plot_scaling_mechanism(converted_dict):

    scaling_mechanism_up = converted_dict["IsScalingMechanismUp"]
    scaling_mechanism_down = converted_dict["IsScalingMechanismDown"]

#     limit_switch_activated = numpy.logical_or(scaling_mechanism_up, scaling_mechanism_down)

    plt.subplot(4, 1, 3)
    plt.ylabel('Scaling Up')
    plt.plot(scaling_mechanism_up, c='b', marker=".")

    plt.subplot(4, 1, 4)
    plt.ylabel('Scaling Down')
    plt.plot(scaling_mechanism_down, c='b', marker=".")
    plt.plot

#     plt.subplot(4, 2, 7)
#     plt.ylabel('A Switch Hit')
#     plt.plot(limit_switch_activated, c='b', marker="o")


def plot_dt(converted_dict):

    the_time = converted_dict['Date and Time']
    dt = the_time[1:] - the_time[:-1]

    plt.title('delta time')
    plt.plot(dt, c='b', marker=".")


def main():
    root = Tkinter.Tk()
    root.withdraw()
    file_name = tkFileDialog.askopenfilename(filetypes = (("All files", "*"), ("Template files", "*.type")))

#     file_name = "Team 174 Robot Logs - RobotLog_20150210_054531870_log.csv"
    converted_dict = load_csv_file(file_name, {'Date and Time': parse_datetime})

    image_dir = "images/"
    if not os.path.exists(image_dir):
        os.mkdir(image_dir)

    plt.figure(1)
    plot_driver_joysticks(converted_dict)
    plt.savefig(image_dir + "DriverSticks.png")

    plt.figure(2)
    plot_encoders(converted_dict)
    plt.savefig(image_dir + "Encoders.png")
    
    plt.figure(3)
    plot_scale_tilt(converted_dict)
    plt.savefig(image_dir + "Scaling.png")
    
    plt.figure(4)
    plot_extension(converted_dict)
    plt.savefig(image_dir + "Extension.png")

    plt.show()


if __name__ == "__main__":
    main()
