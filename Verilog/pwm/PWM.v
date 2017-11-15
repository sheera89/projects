//Sandeep Heera
//PWM.v
//This module emulates a Pulse Width Modulation circuit by implementing a counter
//and comparing the value of the counter versus that of Duty.

module PWM( Clock, Duty, Y );
	input Clock;	//50 MHz clock
	input [7:0] Duty;	//the switches [7:0]
	output reg Y;
	reg [7:0] counter = 8'b0;
	
	always @(posedge Clock) begin
		counter <= counter + 8'b00000001;	//increment counter
		if(counter < Duty) begin
			Y <= 1; 
			end
		else begin
			Y <= 0;
			end
		end
	
endmodule