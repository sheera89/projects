//Sandeep Heera
//Register.v
//A module that represents a 4-bit register. Code is
//largely a rewrite of the Register code found in the
//Lecture 11 powerpoint.

module Register( Data, Enable, Clock, Q ); 
	input [3:0]Data;		//data to shift in
	input Enable;			//enable shift in
	input Clock;			//clock input
	
	output reg [3:0]Q; 	//register output
	
	always @(posedge Clock) 
		begin 
			if (Enable)			//if enable = 1
				begin
					Q <= Data;	//shift input into Q
			end  
		end
		
endmodule