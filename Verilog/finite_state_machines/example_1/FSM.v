//Sandeep Heera
//FSM.v
//This module emulates a 9-state FSM that adheres to the behavior described 
//in Homework 3 Part 2.

module FSM( w_and_reset, Clock, state, z_out );
	input [1:0]w_and_reset;		//input w and reset
	input Clock;					//clock
	output [3:0]state;			//current state
	output z_out;					//output z
	wire w, Reset;
	reg z = 0;
	
	localparam A = 4'b0000, B = 4'b0001, C = 4'b0010, D = 4'b0011, E = 4'b0100,
				  F = 4'b0101, G = 4'b0110, H = 4'b0111, I = 4'b1000;	//state assignments
				  
	reg [3:0]y_Q = A, Y_D; //y_Q represents current state, Y_D represents next state
	
	assign w = w_and_reset[1];			//assign input w
	assign Reset = w_and_reset[0];	//assign Reset
	
	//assign state table based on figure 2
	always @(w, y_Q)
		begin: state_table
			case (y_Q)
				A: if (!w) Y_D = B;
					else Y_D = F;
				B: if (!w) Y_D = C;
					else Y_D = F;
				C: if (!w) Y_D = D;
					else Y_D = F;
				D: if (!w) Y_D = E;
					else Y_D = F;
				E: if (!w) Y_D = E;
					else Y_D = F;
				F: if (!w) Y_D = B;
					else Y_D = G;
				G: if (!w) Y_D = B;
					else Y_D = H;
				H: if (!w) Y_D = B;
					else Y_D = I;
				I: if (!w) Y_D = B;
					else Y_D = I;
				default: Y_D = 4'bxxxx;
			endcase
		end	//state_stable
		
		always @(posedge Clock)
			begin: state_FFs
				if (Reset) y_Q <= Y_D;	//if Reset is high, current state equals the next state
				else y_Q <= A;				//if Reset is low, go back to the reset state
			end	//end state_FFs
		
		always @(y_Q)
			begin: set_z
				case (y_Q)
					E: z = 1;	//set z if present state is E or I
					I: z = 1;
					default: z = 0;
				endcase
			end	//end set_z
			
		assign state[3:0] = y_Q;	//assign output to current state
		assign z_out = z;				//assign output
			
endmodule