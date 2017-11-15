//Sandeep Heera
//test_Modulo_10.v
//This is a test bench for the 
//Modulo_10 module

`timescale 1ns/1ns
module test_Modulo_10();
   reg w1, w0, Reset, Clock;   //inputs to the DUT
   wire [3:0]state;   //output of the FSM (state)
	
   //module under test:
   //reference: Modulo_10( Clock , w1, w0, Reset );
   Modulo_10 DUT( Clock, w1, w0, Reset, state );
	
   //generate a 50MHz clock
   always
      begin: clock_gen
         Clock = 0;
	 #10;
	 Clock = 1;
	 #10;
      end   //clock_gen
	
   initial   //test stimulus
      begin: test_stimulus
         Reset = 1;
	 w1 = 0;
	 w0 = 1;       //increment counter by 1
	 #55 w1 = 1;   //decrement counter by 1
	 #80 w0 = 0;   //increment counter by 2
	 #60 w1 = 0;   //keep current state
	 #40 Reset = 0;//reset to 0
	 #40 $stop;
      end   //test_stimulus

endmodule
	
