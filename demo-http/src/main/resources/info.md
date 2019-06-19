
HTTP响应头和请求头信息对照表

HTTP请求头提供了关于请求，响应或者其他的发送实体的信息。HTTP的头信息包括通用头、请求头、响应头和实体头四个部分。每个头域由一个域名，冒号（:）和域值三部分组成。

通用头标：即可用于请求，也可用于响应，是作为一个整体而不是特定资源与事务相关联。
请求头标：允许客户端传递关于自身的信息和希望的响应形式。
响应头标：服务器和于传递自身信息的响应。
实体头标：定义被传送资源的信息。即可用于请求，也可用于响应。

根据以上分类的HTTP请求头介绍可以参考此文，本工具根据请求和输出分为Request和Response两部分。


HTTP Request Method共计15种


序号|	方法|	描述
---|---|---
1|	GET|	请求指定的页面信息，并返回实体主体。
2|	HEAD|	类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头
3|	POST|	向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改。
4|	PUT|	从客户端向服务器传送的数据取代指定的文档的内容。
5|	DELETE|	请求服务器删除指定的页面。
6|	CONNECT|	HTTP/1.1协议中预留给能够将连接改为管道方式的代理服务器。
7|	OPTIONS|	允许客户端查看服务器的性能。
8|	TRACE|	回显服务器收到的请求，主要用于测试或诊断。
9|	PATCH|	实体中包含一个表，表中说明与该URI所表示的原内容的区别。
10|	MOVE|	请求服务器将指定的页面移至另一个网络地址。
11|	COPY|	请求服务器将指定的页面拷贝至另一个网络地址。
12|	LINK|	请求服务器建立链接关系。
13|	UNLINK|	断开链接关系。
14|	WRAPPED|	允许客户端发送经过封装的请求。
15|	Extension-mothed|	在不改动协议的前提下，可增加另外的方法。



Value|	Description
---|---|
1xx:| Informational - Request received, continuing process
2xx:| Success - The action was successfully received, understood, and accepted
3xx:| Redirection - Further action must be taken in order to complete the request
4xx:| Client Error - The request contains bad syntax or cannot be fulfilled
5xx:| Server Error - The server failed to fulfill an apparently valid request


Value|	Description
---|---|
100|	Continue
101|	Switching Protocols
102|	Processing
103|	Early Hints
104-199|	Unassigned
200|	OK
201|	Created
202|	Accepted
203|	Non-Authoritative Information
204|	No Content
205|	Reset Content
206|	Partial Content
207|	Multi-Status
208|	Already Reported
209-225|	Unassigned
226|	IM Used
227-299|	Unassigned
300|	Multiple Choices
301|	Moved Permanently
302|	Found
303|	See Other
304|	Not Modified
305|	Use Proxy
306|	(Unused)
307|	Temporary Redirect
308|	Permanent Redirect
309-399|	Unassigned
400|	Bad Request
401|	Unauthorized
402|	Payment Required
403|	Forbidden
404|	Not Found
405|	Method Not Allowed
406|	Not Acceptable
407|	Proxy Authentication Required
408|	Request Timeout
409|	Conflict
410|	Gone
411|	Length Required
412|	Precondition Failed
413|	Payload Too Large
414|	URI Too Long
415|	Unsupported Media Type
416|	Range Not Satisfiable
417|	Expectation Failed
418-420|	Unassigned
421|	Misdirected Request
422|	Unprocessable Entity
423|	Locked
424|	Failed Dependency
425|	Too Early
426|	Upgrade Required
427|	Unassigned
428|	Precondition Required
429|	Too Many Requests
430|	Unassigned
431|	Request Header Fields Too Large
432-450|	Unassigned
451|	Unavailable For Legal Reasons
452-499|	Unassigned

500|	Internal Server Error
501|	Not Implemented
502|	Bad Gateway
503|	Service Unavailable
504|	Gateway Timeout
505|	HTTP Version Not Supported
506|	Variant Also Negotiates
507|	Insufficient Storage
508|	Loop Detected
509|	Unassigned
510|	Not Extended
511|	Network Authentication Required
512-599|	Unassigned

HTTP请求方法对照表
根据HTTP标准，HTTP请求可以使用多种请求方法。
HTTP1.0定义了三种请求方法： GET, POST 和 HEAD方法。
HTTP1.1新增了五种请求方法：OPTIONS, PUT, DELETE, TRACE 和 CONNECT 方法。

HTTP Request Method共计15种
序号	方法	描述
1	GET	请求指定的页面信息，并返回实体主体。
2	HEAD	类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头
3	POST	向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改。
4	PUT	从客户端向服务器传送的数据取代指定的文档的内容。
5	DELETE	请求服务器删除指定的页面。
6	CONNECT	HTTP/1.1协议中预留给能够将连接改为管道方式的代理服务器。
7	OPTIONS	允许客户端查看服务器的性能。
8	TRACE	回显服务器收到的请求，主要用于测试或诊断。
9	PATCH	实体中包含一个表，表中说明与该URI所表示的原内容的区别。
10	MOVE	请求服务器将指定的页面移至另一个网络地址。
11	COPY	请求服务器将指定的页面拷贝至另一个网络地址。
12	LINK	请求服务器建立链接关系。
13	UNLINK	断开链接关系。
14	WRAPPED	允许客户端发送经过封装的请求。
15	Extension-mothed	在不改动协议的前提下，可增加另外的方法。