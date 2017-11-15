//Sandeep Heera
//Part6.v
//Top-level module that interfaces the DE2 board with the 
//Hello_FSM.v module and uses the on-board 50 MHz clock.

module Part6( CLOCK_50, SW, HEX7, HEX6, HEX5, HEX4, HEX3, HEX2, HEX1, HEX0 );
	input [0:0]SW;  //synchronous reset
	input CLOCK_50; //50 MHz clock
	output [6:0]HEX7, HEX6, HEX5, HEX4, HEX3, HEX2, HEX1, HEX0;	//hex displays
	
	wire [31:0]Q;
	wire clk;
	
	Divider Div0 ( CLOCK_50, clk ); //turn 50 MHz clock into 1 Hz clock
	
	Hello_FSM FSM1 ( clk, SW[0], Q );	//instance of Hello_FSM module
	
	//decoders for each HEX display
	Decoder D0 ( Q[3:0],   HEX0 );
	Decoder D1 ( Q[7:4],   HEX1 );
	Decoder D2 ( Q[11:8],  HEX2 );
	Decoder D3 ( Q[15:12], HEX3 );
	Decoder D4 ( Q[19:16], HEX4 );
	Decoder D5 ( Q[23:20], HEX5 );
	Decoder D6 ( Q[27:24], HEX6 );
	Decoder D7 ( Q[31:28], HEX7 );
	
endmodule
	