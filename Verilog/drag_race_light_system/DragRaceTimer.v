//Sandeep Heera
//DragRaceTimer.v
//This module takes a 50 MHz clock and sets the signals
//of the DRCTC module to 1 at the appropriate intervals
//(1s/0.5s/0.5s/0.5s) by using a counter.

module DragRaceTimer( Clock, A1, A2, A3, G, R, En );
	localparam SL_COUNT = 27'd50_000_000 - 1;
	localparam A1_COUNT = SL_COUNT + 27'd25_000_000;
	localparam A2_COUNT = A1_COUNT + 27'd25_000_000;
	localparam A3_COUNT = A2_COUNT + 27'd25_000_000;
							 
	input Clock;		//50 MHz signal
	input En;			//enable signal
	input R;				//red light signal
	output reg A1;		//first amber light
	output reg A2;		//second amber light
	output reg A3;		//third amber light
	output reg G;		//green light
	
	reg [26:0]count = 27'd0;//initialize counter to 0
	
	always @( posedge Clock ) begin
		if( En ) begin: count_sequence
			case( count )
				SL_COUNT: begin
								A1 <= 1;	//turn on A1
								count = count + 27'd1;
							 end
				A1_COUNT: begin
								A1 <= 0;	//turn off A1
								A2 <= 1;	//turn on A2
								count = count + 27'd1;
							 end
				A2_COUNT: begin
								A2 <= 0;	//turn off A2
								A3 <= 1; //turn on A3
								count = count + 27'd1;
							 end
				A3_COUNT: begin
								A3 <= 0;	//turn off A3
								G <= 1;	//turn on the green light
							 end
				default: count = count + 27'd1;
			endcase
		end	//count_sequence
		
		else if( ~En ) begin: reset_counter_and_lights
			if( R ) begin	
				A1 <= 0;			//turn off the other lights
				A2 <= 0;
				A3 <= 0;
				G <= 0;
			end	
			else begin
				count = 27'd0;	//reset count
				A1 <= 0;			//turn off all lights
				A2 <= 0;
				A3 <= 0;
				G <= 0;
			end	//end reset_counter_and_lights
		end
	end
	
endmodule