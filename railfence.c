#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>


void remspaces(char text[]) {
    int in = 0;
    for (int i = 0; text[i]; i++) {
        if (text[i] != ' ') {
            text[in++] = text[i];
        }
    }
    text[in] = '\0';
}

void printmat(char mat[][100], int rail, int textlen) {
    for (int i = 0; i < rail; i++) {
        for (int j = 0; j < textlen; j++) {
            if (mat[i][j] != 0)
                printf("%c ", mat[i][j]);
            else
                printf("  ");
        }
        printf("\n");
    }
    printf("\n");
}

void encrypt(int rail, char text[], char enc[]) {
    int textlen = strlen(text);
    if (rail == 1) {
        strcpy(enc, text);
        return;
    }
    char railmat[rail][100];
    int j = 0, direction = 1, in = 0;
    memset(railmat, 0, sizeof(railmat));

    for (int i = 0; i < textlen; i++) {
        railmat[j][i] = text[i];
        if (j == 0) direction = 1;
        else if (j == rail - 1) direction = -1;
        j += direction;
    }

    printmat(railmat, rail, textlen);

    for (int i = 0; i < rail; i++) {
        for (int j = 0; j < textlen; j++) {
            if (railmat[i][j] != 0) {
                enc[in++] = railmat[i][j];
            }
        }
    }

    enc[textlen] = '\0';
}

void decrypt(int rail, char text[], char dec[]) {
    int textlen = strlen(text);
    if (rail == 1) {
        strcpy(dec, text);
        return;
    }

    char railmat[rail][100];
    memset(railmat, 0, sizeof(railmat));

    // Mark zigzag pattern with '*'
    int row = 0, dir = 1;
    for (int i = 0; i < textlen; i++) {
        railmat[row][i] = '*';
        if (row == 0) dir = 1;
        else if (row == rail - 1) dir = -1;
        row += dir;
    }
	printmat(railmat, rail, textlen);
    // Fill the matrix with encrypted text
    int index = 0;
    for (int i = 0; i < rail; i++) {
        for (int j = 0; j < textlen; j++) {
            if (railmat[i][j] == '*' && index < textlen) {
                railmat[i][j] = text[index++];
            }
        }
    }
	printmat(railmat, rail, textlen);
    // Read zigzag to form decrypted string
    row = 0; dir = 1; index = 0;
    for (int i = 0; i < textlen; i++) {
        dec[index++] = railmat[row][i];
        if (row == 0) dir = 1;
        else if (row == rail - 1) dir = -1;
        row += dir;
    }
	
    dec[textlen] = '\0';
}


int main() {
    char text[100], clean[100], enc[100], dec[100];
    int rails;

    printf("Enter the number of rails (depth):\n");
    scanf("%d", &rails);
    getchar();
    printf("Enter the text:\n");
    fgets(text,sizeof(text),stdin);

	remspaces(text);
    encrypt(rails, text, enc);
    decrypt(rails, enc, dec);

    printf("Encrypted: %s\n", enc);
    printf("Decrypted: %s\n", dec);

    return 0;
}

