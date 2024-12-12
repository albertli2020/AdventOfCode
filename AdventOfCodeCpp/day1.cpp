#include <iostream>
#include <vector>
#include <string>
using namespace std;


void setIO(string file = "")
{
  // speed up
  cin.tie(0)->sync_with_stdio(0);
  if ((int)(file.size()))
  {
    freopen((file + ".in").c_str(), "r", stdin);
    freopen((file + ".out").c_str(), "w", stdout);
  }
}

int main(){
    setIO("test");   
}