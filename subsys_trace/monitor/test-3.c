#include<stdio.h>

int main() {
    double x = 0;
    int i, j, k;
    for (i=0; i < 1000; i++) {
        for (j=0; j < 1000; j++) {
            for (k=0; k < 1000; k++) {
                x = (float)i * (float)j * (float)k;
            }
        }
    }
    printf("%lf\n", x);
    return 0;
}
