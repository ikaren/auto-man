#
# Created by Auto-Man v1.0.0 on ${.now}
#
# MySQL数据库配置

db.jdbc.driver=com.mysql.jdbc.Driver
# ${env}
db.jdbc.connection.url=jdbc:mysql://${host}:${port}/${databaseName}?useUnicode=true&amp;characterEncoding=UTF-8&allowMultiQueries=true
db.jdbc.username=${username}
db.jdbc.password=${password}

druid.initialSize=1
druid.maxActive=3
druid.minIdle=1
druid.maxWait=5000
druid.removeAbandoned=true
druid.removeAbandonedTimeout=120000
druid.timeBetweenEvictionRunsMillis=60000
druid.minEvictableIdleTimeMillis=40000
druid.testWhileIdle=true
druid.testOnBorrow=true
druid.testOnReturn=true
druid.poolPreparedStatements=true
druid.maxPoolPreparedStatementPerConnectionSize=10
druid.filters=stat