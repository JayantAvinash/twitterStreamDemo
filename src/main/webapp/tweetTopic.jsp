<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Discussed Topics</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
</style>
</head>
<body>
<div id = "topics" width = "50%"></div>
<script>
var jsonArray = <%= request.getParameter("message") %>;
var myHTMLStr = '<table><tr><th>TOPIC</th><th>No of Tweets</th></tr>';
for(var i in jsonArray) {
myHTMLStr+='<tr><td>' + jsonArray[i]['topicName'] + '</td><td>' + jsonArray[i]['frequency'] +   '</td></tr>';

}
myHTMLStr+='</table>';
document.getElementById('topics').innerHTML = myHTMLStr;
</script>
</body>
</html>