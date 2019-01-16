#include "ClientThread.h"



ClientThread::ClientThread(SOCKET socket)
{
	this->socket = socket;
	memset(&recvData, 0, BUFFER_SIZE);
}


ClientThread::~ClientThread()
{
}
void ClientThread::run() {
	sendMsg();
	recvMsg();
	
}

void ClientThread::sendMsg()
{
	send(socket, data.c_str(), data.size(), 0);
	
}

void ClientThread::recvMsg()
{
	while (true) {
		recv(socket, recvData, BUFFER_SIZE, 0);
		data = recvData;
		cout << recvData << endl;
	}
	
}
