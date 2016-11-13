<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Discussed Topics</title>
</head>
<body>
<div align = "centre">
<h1>Pick a date for getting list of topics discussed in Tweets</h1>
	<form action="http://localhost:8081/twitterStreamDemo/api/twitterService/TwitterTopics/topicDesc">
		<input type="date" name="date" /><br> <br>
		<input type="submit" value="Submit" />
	</form>
	</div>
</body>
</html>
