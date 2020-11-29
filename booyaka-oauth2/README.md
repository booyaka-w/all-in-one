#oauth2-server
授权码模式:
http://localhost:8888/oauth/authorize?client_id=client_a&response_type=code&scope=all&redirect_uri=https://www.baidu.com/
账号密码:user/user
网关:http://localhost:9999/oauth2-server/oauth/authorize?client_id=client_a&response_type=code&scope=all&redirect_uri=https://www.baidu.com/


简化模式:
http://localhost:8888/oauth/authorize?client_id=client_b&response_type=token&scope=all&redirect_uri=https://www.baidu.com/
网关:http://localhost:9999/oauth2-server/oauth/authorize?client_id=client_b&response_type=token&scope=all&redirect_uri=https://www.baidu.com/

密码模式:
http://localhost:8888/oauth/token?client_id=client_c&client_secret=secret&grant_type=password&username=user&password=user
网关:http://localhost:9999/oauth2-server/oauth/token?client_id=client_c&client_secret=secret&grant_type=password&username=user&password=user

客户端模式:
http://localhost:8888/oauth/token?client_id=client_d&client_secret=secret&grant_type=client_credentials
网关:http://localhost:9999/oauth2-server/oauth/token?client_id=client_d&client_secret=secret&grant_type=client_credentials
