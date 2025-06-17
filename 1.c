#include<stdio.h>

int main(){
	char *str = "OnePiece";
	printf("OR 127:\n");
	for(int i = 0;str[i]!= '\0';i++){
		char c = str[i];
		printf("%c [ASCII : %d] -> %c [ASCII : %d]\n",c,c,c|127,c|127);
	}
	printf("\nAND 127:\n");
	for(int i = 0;str[i]!= '\0';i++){
		char c = str[i];
		printf("%c [ASCII : %d] -> %c [ASCII : %d]\n",c,c,c&127,c&127);
	}
	printf("\nXOR 127:\n");
	for(int i = 0;str[i]!= '\0';i++){
		char c = str[i];
		printf("%c [ASCII : %d] -> %c [ASCII : %d]\n",c,c,c^127,c^127);
	}
}
