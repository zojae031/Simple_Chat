#pragma once
#include<thread>
#include"ClientServer.h"

#define BUFFER_SIZE 100
class ClientThread
{
private:
	string data;
	char recvData[BUFFER_SIZE];
	SOCKET socket;

public:
	ClientThread(SOCKET socket);
	virtual ~ClientThread();
	void run();
	void sendMsg();
	void recvMsg();

};

