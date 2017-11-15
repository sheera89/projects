//Sandeep Heera
//Decoder.v
//This module takes a 4-bit input and a HEX display
//and outputs the corresponding letter equivalent
//for a HELLO output.

module Decoder( q, HEX );
	input [3:0]q;
	output reg [6:0] HEX;
	
	//assign values to the HEX segment
	always begin
		case(q)
			1: HEX = ~(7'b1110110);	//H
			2: HEX = ~(7'b1111001);	//E
			3: HEX = ~(7'b0111000);	//L
			4: HEX = ~(7'b0111000);	//L
			5: HEX = ~(7'b0111111);	//O
			6: HEX = ~(7'b0000000);	//_
			7: HEX = ~(7'b0000000);	//_
			8: HEX = ~(7'b0000000);	//_
			9: HEX = ~(7'b0000000);	//_
			default: HEX = ~(7'b0000000);
		endcase
	end
	
endmodule