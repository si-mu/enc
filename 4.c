#include<stdio.h>
#include<ctype.h>
#include<string.h>

int main(){
	int a1[3][3] = {
		{6,11,6},
		{11,6,6},
		{6,6,11}
	};
	int b1[3][3] = {
		{16,11,16},
		{11,16,16},
		{16,16,11}
	};
	int a2[2][2] = {
		{1,2},
		{2,1}
	};
	int b2[2][2] = {
		{17,18},
		{18,17}
	};
	
	char msg[100],c[100]={0},d[100]={0};
	int i,j,t=0,n;
	printf("Message:\n");
	scanf("%s",msg);
	
	int len = strlen(msg);
	for(i=0;i<len;i++){
		msg[i] = toupper(msg[i]);
	}
	printf("Enter n:\n");
	scanf("%d",&n);
	
	if(n==3){
		for(i=len;1;i++){
			if(len%3!=0){
				msg[i]='X';
				len++;
			}
			else{
				break;
			}
		}
		for(i=0;i<len;i++){
			c[i] = msg[i] -65;
		}
		for(int i = 0;i<len;i+=n){
			for(j = 0;j<n;j++){
				t=0;
				for(int k = 0;k<n;k++){
					t+= a1[j][k] * c[i+k];
				}
				d[i+j] = t%26;
			}
		}
		printf("\nEncrypted text: ");
		for (i = 0; i < len; i++) {
		    printf("%c", d[i] + 'A');
		}
		
		
		
		for(int i = 0;i<len;i+=n){
			for(j = 0;j<n;j++){
				t=0;
				for(int k = 0;k<n;k++){
					t+= b1[j][k] * d[i+k];
				}
				c[i+j] = t%26;
			}
		}
		printf("\nDecrypted text: ");
		for (i = 0; i < len; i++) {
		    printf("%c", c[i] + 'A');
		}
	}else if(n==2){
		for(i=len;1;i++){
			if(len%3!=0){
				msg[i]='X';
				len++;
			}
			else{
				break;
			}
		}
		for(i=0;i<len;i++){
			c[i] = msg[i] -65;
		}
		for(int i = 0;i<len;i+=n){
			for(j = 0;j<n;j++){
				t=0;
				for(int k = 0;k<n;k++){
					t+= a2[j][k] * c[i+k];
				}
				d[i+j] = t%26;
			}
		}
		printf("\nEncrypted text: ");
		for (i = 0; i < len; i++) {
		    printf("%c", d[i] + 'A');
		}
		
		
		
		for(int i = 0;i<len;i+=n){
			for(j = 0;j<n;j++){
				t=0;
				for(int k = 0;k<n;k++){
					t+= b2[j][k] * d[i+k];
				}
				c[i+j] = t%26;
			}
		}
		printf("\nDecrypted text: ");
		for (i = 0; i < len; i++) {
		    printf("%c", c[i] + 'A');
		}
	}

return 0;
}
