#include "ClientServer.h"
#include"ClientThread.h"
#pragma warning(disable:4996)

ClientServer::ClientServer()
{
	if (WSAStartup(MAKEWORD(2, 2), &wsdata) != 0) {
		cout << "WS2_32.DLL �� �ʱ�ȭ �ϴµ� ����." << endl;
		WSACleanup();
	}
	serverSocket = socket(AF_INET, SOCK_STREAM, 0);
	ZeroMemory(&serverAddress, sizeof(serverAddress));

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = htonl(INADDR_ANY);
	

	if (::bind(serverSocket, (SOCKADDR *)&serverAddress, sizeof(serverAddress)) == SOCKET_ERROR) {
		cout << "ServerSocket�� IP�� Port�� �ο��ϴµ� �����Ͽ����ϴ�." << endl;
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
		cout << "Ŭ���̾�Ʈ ���� �����" << endl;
		listen(serverSocket, SOMAXCONN);
		if ((clientSocket = accept(serverSocket, NULL, NULL)) == INVALID_SOCKET) {
			cout << "Ŭ���̾�Ʈ�� �����͸� �ְ� �޴� Socket�� ���� �� �� ����." << endl;
			WSACleanup();
			return false;
		}
		cout << "Ŭ���̾�Ʈ ����" << endl;
		ClientThread client(clientSocket);
		std::thread t(&ClientThread::run, &client);
		t.detach(); //������ �и�
		
					/* �����带 ����ų ��
		if (t.joinable()) {
			t.join();
		}
		*/
	}	

	return true;
}
