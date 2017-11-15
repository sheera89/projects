//Sandeep Heera
//DragRace.v
//Interfaces the DE2 board to the DRCTC.v module which emulates a
//drag race light system.

module DragRace( CLOCK_50, KEY, SW, HEX0, HEX1, HEX2, HEX3, HEX4,
					  HEX5, HEX6, HEX7, GPIO );
	input CLOCK_50;	//50 MHz clock signal
	input [0:0]KEY;	//synchronous reset
	input [1:0]SW;		//pre-stage beam and stage beam
	output [6:0]HEX0, HEX1, HEX2, HEX3, 
				  HEX4, HEX5, HEX6, HEX7;//hex displays for the lights
	output [13:1]GPIO;
	localparam Z = 1'b0;
				  
	wire Rst, PSB, SB, Clock, PSL, SL, A1, A2, A3, G, R;
	
	assign Rst = ~KEY[0];	
	assign PSB = SW[0];
	assign SB = SW[1];
	assign GPIO = { R, Z, G, Z, A3, Z, A2, Z, A1, Z, SL, Z, PSL };
	
	//instantiate instance of DRCTC module
	DRCTC U0 ( CLOCK_50, Rst, PSB, SB, PSL, SL, A1, A2, A3, G, R );
	
	//decoders for the hex displays
	DragRaceDecoder U1 ( PSL, HEX7 );	//HEX7 represents the pre-stage light
	DragRaceDecoder U2 ( SL,  HEX6 );	//HEX6 represents the stage light
	DragRaceDecoder U3 ( A1,  HEX5 );	//HEX5 represents the first amber light
	DragRaceDecoder U4 ( A2,  HEX4 );	//HEX4 represents the second amber light
	DragRaceDecoder U5 ( A3,  HEX3 );	//HEX3 represents the third amber light
	DragRaceDecoder U6 ( G,   HEX2 );	//HEX2 represents the green light
	DragRaceDecoder U7 ( R,   HEX1 );	//HEX1 represents the red light
	DragRaceDecoder U8 ( R,   HEX0 );	//HEX0 represents the red light

endmodule