#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int solution(vector<vector<int>> lines) {
    int answer = 0;
    map<int, int> m;
    for(const auto &line: lines) {
        for(int i=line[0];i<line[1];i++) {
            m[i]++;
        }
    }
    for(const auto &i: m) {
        if(i.second>1) {
            answer++;
        }
    }
    return answer;
}