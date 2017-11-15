//Sandeep Heera
//DRCTC.v
//Drag race light system module.

module DRCTC( CLOCK_50, Reset, PSB, SB, PSL, SL, A1, A2, A3, G, R );
	input CLOCK_50;		//50 MHz clock
	input Reset;			//synchronous reset
	input PSB;				//pre-stage beam
	input SB;				//stage beam
	output PSL;				//pre-stage light
	output SL;				//stage light
	output A1;				//first amber light	
	output A2;				//second amber light
	output A3;				//third amber light
	output G;				//green light
	output R;				//red light
	
	localparam Idle = 3'b000, Stage = 3'b001, St_A1 = 3'b010, 
							St_A2 = 3'b011, St_A3 = 3'b100, 
							St_G = 3'b101, St_R = 3'b110;
							
	reg [2:0]pres_st = Idle, next_st;	//present state and next state
	reg Enable, R_s;							//enable for the timer module
	wire A1_s, A2_s, A3_s, G_s;			//wires that keep track of the timer
	
	//instantiate instance of DragRacerTimer module
	DragRaceTimer T0 ( CLOCK_50, A1_s, A2_s, A3_s, G_s, R_s, Enable );
	
	always @( pres_st, Reset, SB, A1_s, A2_s, A3_s, G_s ) begin: state_assignment
		case( pres_st )
			Idle: begin					//idle state
				Enable = 0;				//counter is not incrementing
				R_s = 0;
				if( SB ) begin
					next_st = Stage;	
					Enable = 0;
					R_s = 0;
				end
				else begin				//go back to idle with no red light
					next_st = Idle;
					Enable = 0;
					R_s = 0;
				end
			end
			
			Stage: begin
				if( SB & A1_s ) begin	
					Enable = 1;			
					next_st = St_A1;	//go to the next state if the
					R_s = 0;				//appropriate amount of time has passed
				end
				else if( SB & ~A1_s ) begin	
					Enable = 1;			//start the timer module
					next_st = Stage;
					R_s = 0;
				end
				else begin
					Enable = 0;
					next_st = Idle;
					R_s = 0;
				end
			end
			
			St_A1: begin
				if( SB & A2_s ) begin
					next_st = St_A2;
					Enable = 1;
					R_s = 0;
				end
				else if ( SB & ~A2_s ) begin
					next_st = St_A1;
					Enable = 1;
					R_s = 0;
				end
				else begin
					next_st = St_R;
					Enable = 0;
					R_s = 0;
				end
			end
			
			St_A2: begin
				if( SB & A3_s ) begin
					next_st = St_A3;
					Enable = 1;
					R_s = 0;
				end
				else if( SB & ~A3_s ) begin
					next_st = St_A2;
					Enable = 1;
					R_s = 0;
				end
				else begin
					next_st = St_R;
					Enable = 0;
					R_s = 0;
				end
			end
				
			St_A3: begin
				if( SB & G_s ) begin
					next_st = St_G;
					Enable = 1;
					R_s = 0;
				end
				else if( SB & ~G_s ) begin
					next_st = St_A3;
					Enable = 1;
					R_s = 0;
				end
				else begin
					next_st = St_R;
					Enable = 0;
					R_s = 0;
				end
			end
				
			St_G: begin				//stay in the green state unless reset is pressed
				next_st = St_G;
				Enable = 1;
				R_s = 0;
			end
			
			St_R: begin				//stay in the red light state unless reset is pressed
				Enable = 0;
				R_s = 1;				//send signal to the timer module
				next_st = St_R;
			end
			
			default: next_st = 3'bxxx;
		endcase
	end		//state_assignment
	
	always @( posedge CLOCK_50 ) begin: state_FFs
		if( ~Reset ) pres_st <= next_st;
		else pres_st <= Idle;
	end	//state_FFs
	
	//assign outputs
	assign PSL = PSB;
	assign SL = SB;
	assign A1 = A1_s;
	assign A2 = A2_s;
	assign A3 = A3_s;
	assign G = G_s;
	assign R = R_s;
	
endmodule