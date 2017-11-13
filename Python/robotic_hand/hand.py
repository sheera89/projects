#!/usr/bin/env python
# Sandeep Heera
# hand.py
# This program corresponds to the robotic hand. It connects to the
# MCP3008 and used haptic feedback sensors to update the status of
# what the robotic hand feels around its fingertips to the robotic glove
# via TCP sockets. It also updates the Arduino microcontroller via serial
# communication. The Arduino drives the servos which control the contraction
# of the fingers of the robotic hand.
import socket
import time
import serial

# Import SPI library (for hardware SPI) and MCP3008 library.
import Adafruit_GPIO.SPI as SPI
import Adafruit_MCP3008

# Hardware SPI configuration:
SPI_PORT   = 0
SPI_DEVICE = 0
mcp = Adafruit_MCP3008.MCP3008(spi=SPI.SpiDev(SPI_PORT, SPI_DEVICE))
ser = serial.Serial('/dev/ttyACM2', 9600)

TCP_IP = '10.16.20.117'
TCP_PORT = 5005
BUFFER_SIZE = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))
s.send(MESSAGE)
while 1:
        data_to_send = ""
	data = s.recv(BUFFER_SIZE)
        time.sleep(0.300)
	ser.write(data + '\n');
	#gonna write these to the motors after calculations!
	for i in range(0, 5):
		if i == 4:
			data_to_send += str(mcp.read_adc(i))
		else:
			data_to_send += str(mcp.read_adc(i)) + ","
	s.send(data_to_send)
s.close()
