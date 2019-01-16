#pragma once

class DataBaseConnect
{
	MYSQL m_Mysql;
	MYSQL_RES *m_Res;
	MYSQL_ROW row;
public:
	DataBaseConnect();
	~DataBaseConnect();

};

