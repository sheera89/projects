//Sandeep Heera
//Hello_FSM.v
//This module is a FSM which is designed to cycle values
//across 8 4-bit registers.

module Hello_FSM( Clock, Reset, Q );
	input Clock;	//clock input
	input Reset;	//synchronous active-low reset
	output [31:0]Q;//32-bit output which encapsulates all outputs from the registers
	
	localparam A = 4'b0000, B = 4'b0001, C = 4'b0010, D = 4'b0011, 
				  E = 4'b0100, F = 4'b0101, G = 4'b0110, H = 4'b0111, 
				  I = 4'b1000;	//different states
				  
	reg [3:0]y_Q = A, Y_D;	//y_Q represents current state, Y_D is next state
	reg [31:0]q_in;			//inputs to the registers
	reg [7:0]enable;			//enable for the registers
	
	//instantiate registers
	Register R0 ( q_in[3:0], enable[0], Clock, Q[3:0] );
	Register R1 ( q_in[7:4], enable[1], Clock, Q[7:4] );
	Register R2 ( q_in[11:8], enable[2], Clock, Q[11:8] );
	Register R3 ( q_in[15:12], enable[3], Clock, Q[15:12] );
	Register R4 ( q_in[19:16], enable[4], Clock, Q[19:16] );
	Register R5 ( q_in[23:20], enable[5], Clock, Q[23:20] );
	Register R6 ( q_in[27:24], enable[6], Clock, Q[27:24] );
	Register R7 ( q_in[31:28], enable[7], Clock, Q[31:28] );
	
	always @(y_Q, Q)
		begin: state_table
			case (y_Q)
				A:	begin
						enable[7:0] = 8'b11111111;
						q_in[31:0] = 32'b0;
						Y_D = B;	
						q_in[3:0] = B;	//input B into register R0
					end
				B: begin
						Y_D = C;
						q_in[3:0] = C;				//input C into register R0
						q_in[7:4] = Q[3:0];		//assign inputs to previous
						q_in[11:8] = Q[7:4];		//registers output
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end								
				C:	begin
						Y_D = D;
						q_in[3:0] = D;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				D:	begin							
						Y_D = E;
						q_in[3:0] = E;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				E: begin
						Y_D = F;
						q_in[3:0] = F;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				F:	begin
						Y_D = G;
						q_in[3:0] = G;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				G:	begin
						Y_D = H;
						q_in[3:0] = H;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				H: begin
						Y_D = I;
						q_in[3:0] = I;	
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
					end
				I:	begin
						Y_D = I;
						q_in[7:4] = Q[3:0];
						q_in[11:8] = Q[7:4];
						q_in[15:12] = Q[11:8];
						q_in[19:16] = Q[15:12];
						q_in[23:20] = Q[19:16];
						q_in[27:24] = Q[23:20];
						q_in[31:28] = Q[27:24];
						q_in[3:0] = Q[31:28];	//set input of first register R0
					end								//to output of last register
				default: Y_D = 4'bxxx;
			endcase
		end	//state_table
		
	always @(posedge Clock)
		begin: state_FFs
			if(Reset) y_Q <= Y_D;
			else y_Q <= A;
		end	//state_FFs
		
endmodule