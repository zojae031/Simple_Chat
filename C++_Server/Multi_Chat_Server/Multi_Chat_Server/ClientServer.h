#pragma once
#pragma comment (lib,"ws2_32.lib")

#include<iostream>
#include<WinSock2.h>

#define PORT 5050

using namespace std;

class ClientServer
{
private :
	WSADATA wsdata;
	SOCKADDR_IN serverAddress;
	SOCKET serverSocket;
	SOCKET clientSocket;

public:
	ClientServer();
	~ClientServer();
	bool Server_Start();
};

