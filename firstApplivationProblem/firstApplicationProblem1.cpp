/***Implement the solution in a language of your choice.**
+
+You are on a infinite 2D coordinate system.
+
+The only thing you know is that x increases to the right and decreases to the left, while y increases downwards and decreases upwards.
+
+You know your current position: `(current-x, current-y)`.
+
+You are given a string of the form:
+
+`">v<>>>v^~><><~><><"` where each arrow represents a direction.
+
+Your job is to follow the directions of the arrow and output the final position that you have arrived, starting from your own.
+
+There is one special symbol that you should take care of - **the warp symbol `~`**
+
+When you get the warp symbol, the following effect triggers:
+
+* Moving on X axis is reversed. If `x` is increasing to the right, after the warp symbol,`x` starts to increase to the left and decrease to the right. And vice versa.
+* Moving on Y axis is reversed. If `y` is increasing downwards, after the warp symbol, `y` starts to increase upwards and decrease downwards. And vice versa.
+
+Here are few examples:
+
+Input:
+
+```
+Starting point: (0, 0)
+>>><<<~>>>~^^^
+```
+
+Output:
+
+```
+(-3, -3)
+```*/



#include<iostream>
using namespace std;

int main(){
int i=0,x,currentx,y,currenty;
string str;
bool flag=false;

cout<<"Enter string(example:>>><<<~>>>~^^^)"<<endl;
cin>>str;
cout<<"Enter x:";
cin>>x;
cout<<"Enter y:";
cin>>y;

for(i=0;i<str.size();i++){
	if(!flag){
		if(str[i]=='>')x++;
		if(str[i]=='<')x--;
		if(str[i]=='v')y++;
		if(str[i]=='^')y--;
		if(str[i]=='~')flag=true;

	}
	else{
		if(str[i]=='>')x--;
		if(str[i]=='<')x++;
		if(str[i]=='v')y--;
		if(str[i]=='^')y++;
		if(str[i]=='~')flag=false;
	}
}

cout<<"final position:("<<x<<","<<y<<")"<<endl;

return 0;

}
