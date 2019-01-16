#include "DataBaseConnect.h"
#include"ClientServer.h"
#include"mysql.h"

#define DB_ADDRESS "localhost"
#define DB_ID "root"
#define DB_PASS "8845"
#define DB_NAME "exam"

extern MYSQL_ROW row;
extern MYSQL_RES *m_res;
extern MYSQL mysql;

DataBaseConnect::DataBaseConnect()
{
	mysql_init(&m_Mysql);
	if (mysql_real_connect(&m_Mysql, DB_ADDRESS, DB_ID, DB_PASS, DB_NAME, 3306, NULL, 0) == NULL) {
		return;
	}
	
}


DataBaseConnect::~DataBaseConnect()
{
}
