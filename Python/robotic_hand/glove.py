#!/usr/bin/env python
# Sandeep Heera
# glove.py
# This program corresponds to the glove portion of the project.
# This program connects to the MCP3008 and parses the readings
# from the resistive strips in order to update the robotic hand
# via TCP socket communication.
import socket
import time

# Import SPI library (for hardware SPI) and MCP3008 library.
import Adafruit_GPIO.SPI as SPI
import Adafruit_MCP3008

# Hardware SPI configuration:
SPI_PORT   = 0
SPI_DEVICE = 0
mcp = Adafruit_MCP3008.MCP3008(spi=SPI.SpiDev(SPI_PORT, SPI_DEVICE))


TCP_IP = '10.16.20.117'
TCP_PORT = 5005
BUFFER_SIZE = 1024  # Normally 1024, but we want fast response

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)


conn, addr = s.accept()
print 'Connection address:', addr
while 1:
    data = conn.recv(BUFFER_SIZE)
    data_to_send = ""
    for i in range(0, 5):
        if i == 4:
                data_to_send += str(mcp.read_adc(i))
        else:
                 data_to_send += str(mcp.read_adc(i)) + ","
    mylist = data.split(",")
    print mylist
    #gonna write these after scaling to the buzzers
    conn.send(data_to_send)  # echo
conn.close()
