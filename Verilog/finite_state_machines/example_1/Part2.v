//Sandeep Heera
//Part2.v
//This module interfaces the DE2 board with the FSM.v module.

module Part2(SW, KEY, LEDG, LEDR);
	input [1:0]SW;		//for synchronous reset and for input w
	input [0:0]KEY;	//clock
	output [3:0]LEDR;	//will represent the state
	output [0:0]LEDG;	//will represent output z
	
	FSM F0 ( SW, ~KEY[0], LEDR, LEDG );	//instantiate instance of 9-state FSM

endmodule