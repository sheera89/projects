//TCES 330
//Sandeep Heera
//This module is a testbench for the module Part2.

`timescale 1ns/1ns
module test_part2;
	reg [1:0]w_and_Reset;	//inputs to the DUT
	reg [0:0]Clock;	//clock for the DUT
	wire [3:0]present_state;
	wire [0:0]z;

	//module under test:
	Part2 DUT( w_and_Reset, ~Clock, z, present_state );

	//50 MHz clock
	always 
		begin: clock_gen
			Clock = 0;
			#10;
			Clock = 1;
			#10;
		end  //clock_gen

	initial //Test stimulus
		begin: test_stimulus
			w_and_Reset[1] = 0;
			w_and_Reset[0] = 1;
			#55 w_and_Reset[1] = 1;
			#20 w_and_Reset[1] = 0;
			#80 w_and_Reset[1] = 1;
			#100 w_and_Reset[1] = 0;
			#40 $stop;
		end //test_stimulus

endmodule