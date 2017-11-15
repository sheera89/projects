//Sandeep Heera
//Decoder.v
//This module takes a 4-bit input and a HEX display
//and outputs the corresponding decimal equivalent
//for a modulo 10 counter.
module Decoder( q, HEX );
	input [3:0]q;
	output reg [6:0] HEX;
	
	//assign values to the HEX segment
	always begin
		case(q)
			0: HEX = ~(7'b0111111);
			1: HEX = ~(7'b0110000);
			2: HEX = ~(7'b1011011);
			3: HEX = ~(7'b1001111);
			4: HEX = ~(7'b1100110);
			5: HEX = ~(7'b1101101);
			6: HEX = ~(7'b1111101);
			7: HEX = ~(7'b0000111);
			8: HEX = ~(7'b1111111);
			9: HEX = ~(7'b1100111);
			default: HEX = ~(7'b0111111);
		endcase
	end
	
endmodule