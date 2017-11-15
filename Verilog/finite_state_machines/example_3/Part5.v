//Sandeep Heera
//Part5.v
//Top-level module that interfaces the DE2 board with the 
//Hello_FSM.v module.

module Part5( KEY, SW, HEX7, HEX6, HEX5, HEX4, HEX3, HEX2, HEX1, HEX0 );
	input [0:0]SW;  //synchronous reset
	input [0:0]KEY; //Clock
	output [6:0]HEX7, HEX6, HEX5, HEX4, HEX3, HEX2, HEX1, HEX0;	//hex displays
	
	wire [31:0]Q;
	
	Hello_FSM FSM1 ( ~KEY[0], SW[0], Q );	//instance of Hello_FSM module
	
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
	