//Sandeep Heera
//DragRaceDecoder.v
//This module displays the state of a signal on one
//of the hex displays of the DE2 board.

module DragRaceDecoder( lightOn, HEX );
	input lightOn;			//signal for the light
	output reg [6:0]HEX;	//hex display for output
	
	always @( lightOn ) begin: light_assign
		if( lightOn ) HEX = 7'b0000000;	//all lights on
		else HEX = 7'b1111111;				//all lights off
	end	//light_assign
	
endmodule