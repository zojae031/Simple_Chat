#include<iostream>

#include"ClientServer.h"


using namespace std;

int main() {
	ClientServer server;
	

	if (server.Server_Start()) {
		cout << "정상 종료" << endl;
	}
	else {
		cout << "비정상 종료" << endl;
	}

	
	return 0;
}