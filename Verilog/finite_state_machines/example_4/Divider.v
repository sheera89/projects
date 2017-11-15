//TCES 330 Spring, 2016
//Sandeep Heera
//Lab 4, Part4
//
//This module takes a 50MHz clock signal and converts it to a 1Hz
//signal via division.
module Divider( clk, Q );
	input clk;
	output reg Q;
	reg [25:0]count;
	
	always @ (posedge clk ) begin
		if( count == 26'd49_999_999 ) begin	//if we have reached max count
			count <= 26'd0;
			Q <= 1;	//enable the counter Q is connected to
			end
		else begin 
			count <= count + 1;
			Q <= 0;
			end				
	end
	 
endmodule