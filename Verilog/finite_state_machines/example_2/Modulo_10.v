//Sandeep Heera
//Modulo_10.v
//This module represents a 10-state FSM. It emulates a 
//modulo-10 counter.

module Modulo_10( Clock, w1, w0, Reset, state );
	input Clock, w1, w0, Reset;
	output [3:0]state;
	
	localparam A = 4'b0000, B = 4'b0001, C = 4'b0010, D = 4'b0011, 
				  E = 4'b0100, F = 4'b0101, G = 4'b0110, H = 4'b0111, 
				  I = 4'b1000, J = 4'b1001;	//different states also represent output
				  
	reg [3:0]y_Q = A, Y_D;		//y_Q represents current state, Y_D represents next state
	
	//assign state table
	always @(w1, w0, y_Q)
		begin: state_table
			case (y_Q)
				A: if (!w1 & !w0) Y_D = A;			//w1w0 = 00, stay in the same state
					else if (!w1 & w0) Y_D = B;	//w1w0 = 01, increment by one
					else if (w1 & !w0) Y_D = C;	//w1w0 = 10, increment by two
					else Y_D = J;						//w1w0 = 11, decrement by one
				B: if (!w1 & !w0) Y_D = B;
					else if (!w1 & w0) Y_D = C;
					else if (w1 & !w0) Y_D = D;
					else Y_D = A;
				C:	if (!w1 & !w0) Y_D = C;
					else if (!w1 & w0) Y_D = D;
					else if (w1 & !w0) Y_D = E;
					else Y_D = B;
				D:	if (!w1 & !w0) Y_D = D;
					else if (!w1 & w0) Y_D = E;
					else if (w1 & !w0) Y_D = F;
					else Y_D = C;
				E:	if (!w1 & !w0) Y_D = E;
					else if (!w1 & w0) Y_D = F;
					else if (w1 & !w0) Y_D = G;
					else Y_D = D;
				F:	if (!w1 & !w0) Y_D = F;
					else if (!w1 & w0) Y_D = G;
					else if (w1 & !w0) Y_D = H;
					else Y_D = E;
				G:	if (!w1 & !w0) Y_D = G;
					else if (!w1 & w0) Y_D = H;
					else if (w1 & !w0) Y_D = I;
					else Y_D = F;
				H:	if (!w1 & !w0) Y_D = H;
					else if (!w1 & w0) Y_D = I;
					else if (w1 & !w0) Y_D = J;
					else Y_D = G;
				I:	if (!w1 & !w0) Y_D = I;
					else if (!w1 & w0) Y_D = J;
					else if (w1 & !w0) Y_D = A;
					else Y_D = H;
				J: if (!w1 & !w0) Y_D = J;
					else if (!w1 & w0) Y_D = A;
					else if (w1 & !w0) Y_D = B;
					else Y_D = I;
				default: Y_D = 4'bxxx;
			endcase
		end //state_table
		
		always @(posedge Clock)
			begin: state_FFs
				if ( Reset ) y_Q <= Y_D;
				else y_Q <= A;
			end //state_FFs
			
		assign state[3:0] = y_Q;	//assign state to output
		
endmodule