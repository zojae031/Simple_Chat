#include "ClientServer.h"
#include"ClientThread.h"
#pragma warning(disable:4996)

ClientServer::ClientServer()
{
	if (WSAStartup(MAKEWORD(2, 2), &wsdata) != 0) {
		cout << "WS2_32.DLL 을 초기화 하는데 실패." << endl;
		WSACleanup();
	}
	serverSocket = socket(AF_INET, SOCK_STREAM, 0);
	ZeroMemory(&serverAddress, sizeof(serverAddress));

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	

	if (::bind(serverSocket, (SOCKADDR *)&serverAddress, sizeof(serverAddress)) == SOCKET_ERROR) {
		cout << "ServerSocket에 IP와 Port를 부여하는데 실패하였습니다." << endl;
		WSACleanup();
	}
}


ClientServer::~ClientServer()
{
	closesocket(serverSocket);
	WSACleanup();
}

bool ClientServer::Server_Start()
{
	while (true) {
		cout << "클라이언트 접속 대기중" << endl;
		listen(serverSocket, SOMAXCONN);
		if ((clientSocket = accept(serverSocket, NULL, NULL)) == INVALID_SOCKET) {
			cout << "클라이언트와 데이터를 주고 받는 Socket을 생성 할 수 없음." << endl;
			WSACleanup();
			return false;
		}
		cout << "클라이언트 접속" << endl;
		ClientThread client(clientSocket);
		std::thread t(&ClientThread::run, &client);
		t.detach(); //스레드 분리
		
					/* 스레드를 대기시킬 때
		if (t.joinable()) {
			t.join();
		}
		*/
	}	

	return true;
}
