#include <bits/stdc++.h>
using namespace std;
//this is not as intuitive to read, so... have some comments.
int main (int argc, char const *argv[]){
	size_t m, n;
	while(cin >> n,cin >> m, m && n){
		vector<pair<long,long>> boss_of;
		//read array
		long t;
		for(size_t i = 1; i <= n; ++i){
			cin >> t;
			boss_of.push_back({i,t});
		}
		
		//build graph
		map<long, vector<long>> g;
		for(size_t i = 0; i < n; ++i){
			map<long, vector<long>>::iterator git;
			git = g.find(boss_of[i].second);
			if(git == g.end())
				g[boss_of[i].second] = vector<long>();
			g[boss_of[i].second].push_back(boss_of[i].first);
		}
		
		//start bfs-like minimal sum
		map<long, long> cost;
		stack<long> callback;
		callback.push(0);
		while(!callback.empty()){
			
			long target = callback.top();
			map<long, vector<long>>::iterator git;
			git = g.find(target);
			bool ww=git == g.end();
			if(ww){
				cost[target] = 1;
				callback.pop();
			}
			else{
				long how_many_needed = ceil(g[target].size() * m / 100.0);
				bool unkown = false;
				vector<long> w;
				for(size_t i = 0; i < g[target].size(); ++i){
					if(cost.find(g[target][i]) == cost.end()){
						unkown = true;
						callback.push(g[target][i]);
					}
					else{
						w.push_back(cost[g[target][i]]);
					}
				}
				if(!unkown){
					callback.pop();
					sort(w.begin(), w.end());
					long sum = 0, i=0;
					while(i<how_many_needed) sum += w[i++];
					cost[target] = sum;
				}
			}
		}
		
		//print teh solution
		cout << cost[0] << endl;
	}
	return 0;
}