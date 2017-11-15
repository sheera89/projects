/* Sandeep Heera
 * robot.ino
 * The purpose of this program is to drive the autonomous robot forward
 * until it encounters an obstacle in it's path at a distance of 6 inches at which point
 * the robot must reverse. It must then scan the field to it's immediate left and immediate right
 * and determine the least obstructed path and move in that direction.
 * In addition to the above, the robot must detect a particular color
 * (grey, white, or black) and when it encounters that color, it must determine if there is a 
 * magnet situated underneath it.
 */
 
#include <BricktronicsMotor.h>
#include <BricktronicsUltrasonic.h>
#include <BricktronicsLight.h>
#include <BricktronicsMegashield.h>
#include <TimerThree.h>
#include <Wire.h>
#include <I2Cdev.h>
#include <MPU9250.h>

const int TIMER_PERIOD = 200000;  //set period to 200000 microseconds
const int DRIVE_SPEED = -125;
const int REVERSE_SPEED = 125;
const int DISTANCE_THRESHOLD = 20;
const int DISTANCE_OFFSET = 16;
const int LIGHT_HIGH = 932;
const int LIGHT_LOW = 646;
const int TURN_ANGLE = 410;       //corresponds to a 90 degree sweep with our configuration
const int MAGNET_VALUE = 100;
const int READ_LOW = 0;           //corresponds to black
const int READ_HIGH = 20;

BricktronicsMotor m1(BricktronicsMegashield::MOTOR_1);      //initialize motor 1 object
BricktronicsMotor m2(BricktronicsMegashield::MOTOR_2);      //initialize motor 2 object
BricktronicsLight ls(BricktronicsMegashield::SENSOR_3);     //initialize light sensor object
BricktronicsUltrasonic u(BricktronicsMegashield::SENSOR_4); //initialize ultrasonic sensor object


MPU9250 accelgyro;
int16_t ax, ay, az;
int16_t gx, gy, gz;
int16_t mx, my, mz;
int done = 0;

void setup() {
  noInterrupts();
  Timer3.initialize(TIMER_PERIOD);         //set the interrupt frequency to 5 times per second
  Timer3.attachInterrupt(ISR0);
  interrupts();
  Wire.begin();
  m1.begin();
  m2.begin();
  ls.begin();
  u.begin();
  Serial.begin(19200);
  ls.setCalibrationLowValue(LIGHT_LOW);    //calibrate a low value for the light sensor
  ls.setCalibrationHighValue(LIGHT_HIGH);  //calibrate a high value for the light sensor
  accelgyro.initialize();
}

void loop() {
  int currentDistance = u.getDistance();  //get the current distance
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);  //get the values from the IMU

  if(currentDistance > DISTANCE_THRESHOLD){ //decide whether we keep moving forward or go back
     moveForward();
  }
  else{
    m1.brake();
    m2.brake();

    moveBackwards(currentDistance);         //move back until the robot is ~12 inches from the obstacle
    
    turnLeft();
    int left = u.getDistance();             //get the distance to the left of the robot
    turnRight();                            //get back to the original position
    turnRight();                            
    int right = u.getDistance();            //get the distance to the right of the robot
    if(left >= right){                      //if left is more open or equal to right, go back to the left
      turnLeft();
      turnLeft();
    }                                       //else move forward to the right of the robot
  }
}

/*
 * Interrupt service routine. This routine checks the value of the light sensor to determine
 * if we are reading our desired color from the light sensor. It also will print out the scaled
 * value of the light sensor as well as the mx, my, and mz values from the sensor for debugging
 * purposes.
 */
void ISR0(){
  int reading = ls.scaledValue(); //read from the light sensor

  //print out sensor data
  Serial.print("Current scaled light sensor reading: ");
  Serial.println(reading);
  Serial.print("m Info:\t\t");
  Serial.print("x-axis: ");
  Serial.print(mx); Serial.print("\t");
  Serial.print("y-axis: ");
  Serial.print(my); Serial.print("\t");
  Serial.print("z-axis: ");
  Serial.print(mz); Serial.print("\t");

  if(reading >= READ_LOW && reading <= READ_HIGH){  //check to see if the sensor value is within our range
       //check to see if the most recent magnet sensor readings are out of normal ranges
       if (abs(mx) > MAGNET_VALUE || abs(my) > MAGNET_VALUE || abs(mz) > MAGNET_VALUE) {  
          while(1){ //make the robot not do anything until it is reset since we've found the magnet
            noInterrupts(); //disable interrupts
            m1.brake();
            m2.brake();
          }
        }
  }
}

/* This function drives the two motors of the autonomous robot at the 
 * speed specified by DRIVE_SPEED. The direction corresponds to moving forward.
 */
void moveForward() {
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
  m1.setFixedDrive(DRIVE_SPEED);
  m2.setFixedDrive(DRIVE_SPEED);
}

/* This function drives the two motors of the autonomous robot at the 
 * speed specified by REVERSE_SPEED. The direction corresponds to moving backwards.
 * The robot will keep moving back until it is ~12 inches from the obstacle.
 */
void moveBackwards(int distance) {
  while(!(u.getDistance() >= (distance + DISTANCE_OFFSET))){
    accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
    m1.setFixedDrive(REVERSE_SPEED);
    m2.setFixedDrive(REVERSE_SPEED);
  }
  m1.brake();
  m2.brake();
}

/* Turns the robot to the left by rotating the wheels in opposite directions.
 * The angle of rotation of the robot should be ~90 degrees.
 */
void turnLeft() {
  m1.setPosition(0);
  m2.setPosition(0);

  m1.goToPosition(TURN_ANGLE);
  m2.goToPosition(-TURN_ANGLE);
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
  
  while(!m1.settledAtPosition(TURN_ANGLE) && !m2.settledAtPosition(-TURN_ANGLE)) {
    m1.update();
    m2.update();
  }
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
}

/* Turns the robot to the right by rotating the wheels in opposite directions.
 * The angle of rotation of the robot should be ~90 degrees.
 */
void turnRight() {
  m1.setPosition(0);
  m2.setPosition(0);

  m1.goToPosition(-TURN_ANGLE);
  m2.goToPosition(TURN_ANGLE);
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);

  while(!m1.settledAtPosition(-TURN_ANGLE) && !m2.settledAtPosition(TURN_ANGLE)) {
    m1.update();
    m2.update();
  }
  accelgyro.getMotion9(&ax, &ay, &az, &gx, &gy, &gz, &mx, &my, &mz);
}
