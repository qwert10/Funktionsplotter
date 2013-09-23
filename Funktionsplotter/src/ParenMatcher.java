import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ParenMatcher{
  private String inputString, outputString;
  	
  private boolean match(char c, char d){
    switch(c){
      case '(': return(d==')');
      case '[': return(d==']');
      case '{': return(d=='}');
      default : return false;
    }
  }
  	
  public void parenMatch(){
    
    int n=inputString.length();
    Stack parenStack=new Stack(n);
    int i=0;
    char c,d;
    while(i<n){
      d=inputString.charAt(i);
      if(d=='('||d=='['||d=='{'){          //šffnende Klammer
      
    	  parenStack.push(d);

      }else if(d==')'||d==']'||d=='}'){
        if(parenStack.empty()){
        	output("Falsch");
        	return;
            //Stack leer, was nun?

        }else{
          c=((Character)parenStack.pop()).charValue();
          if(!match(c,d)){
            //keine Ÿbereinstimmung von d und dem top vom Stack
        	  output("Falsch");
        	  return;
            //hier ergŠnzen

          }
        }
      }
      ++i;
    }
    if(parenStack.empty()){
    	
    		output("Richtig");
    		return;

    }else{
   
    	while(!parenStack.empty()&&(i<n))
    	{
    		
    		c=((Character)parenStack.pop()).charValue();
    		d=inputString.charAt(i);
    		if(c==d)
    			;
    		else
    			{
    			
    			output("Falsch");
    			return;
    					}
    		
    	}
   	  output("Falsch");

    }
  }
  	
  private void output(String s){
    outputString=s;
  }
  	
  public void setInput(String input){
    inputString=input;
  }

  public String getOutput(){
    return outputString;
  }
}
