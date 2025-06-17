#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>

void encrypt(char key[],char text[],char enc[]){
	int keylen = strlen(key);
	int textlen = strlen(text);
	int j = 0;
	char c,k,e;
	for(int i = 0;i<textlen;i++){
		c = toupper(text[i]);
		k = toupper(key[(j++)%keylen]);
		e = ((c-'A') + (k - 'A'))%26 + 'A';
		enc[i] = e; 
	}
}
void decrypt(char key[],char text[],char dec[]){
	int keylen = strlen(key);
	int textlen = strlen(text);
	int j = 0;
	char c,k,e;
	for(int i = 0;i<textlen;i++){
		c = toupper(text[i]);
		k = toupper(key[(j++)%keylen]);
		e = ((c-'A') - (k - 'A') + 26)%26 + 'A';
		dec[i] = e; 
	}
}
int main(){
	char text[20],key[20],enc[20],dec[20];
	int i,j;
	printf("Enter the key:\n");
	scanf("%s",key);
	printf("Enter the text:\n");
	scanf("%s",text);
	
	for(char c = 'A';c <= 'Z';c++){
		for(i=0;i<26;i++){
			printf("%c ",((c-'A')+i)%26 + 'A');
		}
		printf("\n");
	}
	
	encrypt(key,text,enc);
	decrypt(key,enc,dec);
	printf("Encrypted: %s\n",enc);
	printf("Decrypted: %s\n",dec);
	
	return 0;
}
