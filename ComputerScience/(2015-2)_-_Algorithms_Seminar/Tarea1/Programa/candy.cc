#include <stdio.h>
int main (int argc, char const *argv[]){
	long m, n, i, j, dpr_1, dpr_2, dp_v1, dp_v2, t, r;
	//Me costó programarlo, debe costarte entenderlo.
	while(scanf("%ld %ld",&m,&n), m && n){
		dpr_1 = dpr_2 = dp_v1 = dp_v2 = t = r = 0;
		for(i = 0; i < m; ++i){
			for(dp_v1=0, dp_v2=0, j = 0; j < n; ++j){
				scanf("%ld",&r);
				t = dp_v1;
				dp_v1 = dp_v2 + r > dp_v1 ? dp_v2 + r : dp_v1;
				dp_v2 = t;
			}
			t = dpr_1;
			dpr_1 = dpr_2 + ((dp_v1 > dp_v2)? dp_v1 : dp_v2) > dpr_1 ? dpr_2 + ((dp_v1 > dp_v2)? dp_v1 : dp_v2) : dpr_1;
			dpr_2 = t;
		}
		printf("%ld\n", (dpr_1 > dpr_2) ? dpr_1 : dpr_2);
	}
	return 0;
}