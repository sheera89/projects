//Sandeep Heera
//MT1.v
//This module interfaces the DE2 board with the PWM module.

module MT1( CLOCK_50, SW, LEDR, LEDG );
	input CLOCK_50; //50 MHz cloCK
	input [7:0]SW; //switches
	output [17:0]LEDR; //red LEDs
	output [7:0]LEDG; //green LEDs
	
	wire Y;	//will switch LEDs on and off
	
	PWM one ( CLOCK_50, SW, Y );	//instantiate instance of PWM
	
	assign LEDR[0] = Y;	//assign output of PWM to LEDR
	assign LEDR[1] = Y;	//assign output of PWM to LEDR
	assign LEDR[2] = Y;	//assign output of PWM to LEDR
	assign LEDR[3] = Y;	//assign output of PWM to LEDR
	assign LEDR[4] = Y;	//assign output of PWM to LEDR
	assign LEDR[5] = Y;	//assign output of PWM to LEDR
	assign LEDR[6] = Y;	//assign output of PWM to LEDR
	assign LEDR[7] = Y;	//assign output of PWM to LEDR
	assign LEDR[8] = Y;	//assign output of PWM to LEDR
	assign LEDR[9] = Y;	//assign output of PWM to LEDR
	assign LEDR[10] = Y;	//assign output of PWM to LEDR
	assign LEDR[11] = Y;	//assign output of PWM to LEDR
	assign LEDR[12] = Y;	//assign output of PWM to LEDR
	assign LEDR[13] = Y;	//assign output of PWM to LEDR
	assign LEDR[14] = Y;	//assign output of PWM to LEDR
	assign LEDR[15] = Y;	//assign output of PWM to LEDR
	assign LEDR[16] = Y;	//assign output of PWM to LEDR
	assign LEDR[17] = Y;	//assign output of PWM to LEDR
	
	assign LEDG[0] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[1] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[2] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[3] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[4] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[5] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[6] = ~Y;	//assign output of PWM to LEDG
	assign LEDG[7] = ~Y;	//assign output of PWM to LEDG

endmodule
