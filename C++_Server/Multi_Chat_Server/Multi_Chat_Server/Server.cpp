#include<iostream>

#include"ClientServer.h"


using namespace std;

int main() {
	ClientServer server;
	

	if (server.Server_Start()) {
		cout << "���� ����" << endl;
	}
	else {
		cout << "������ ����" << endl;
	}

	
	return 0;
}