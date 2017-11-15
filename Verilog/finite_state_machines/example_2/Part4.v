//Sandeep Heera
//Part4.v
//This module interfaces the module Modulo_10 to the DE2 board.

module Part4( SW, KEY, HEX0 );
	input [2:0]SW;		//inputs w1w0 and synchronous reset
	input [0:0]KEY;	//Clock
	output [6:0]HEX0;	//Hex display
	wire [3:0]state;	//state variable
	
	Modulo_10 M1 ( ~KEY[0], SW[2], SW[1], SW[0], state );	//instantiate instance of Modulo_10 counter
	
	Decoder D1 ( state, HEX0 );	//send value to decoder to display on HEX[0]

endmodule