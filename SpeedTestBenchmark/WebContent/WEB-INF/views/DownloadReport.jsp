
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.btn {
  background-color: DodgerBlue;
  border: none;
  color: white;
  padding: 12px 30px;
  cursor: pointer;
  font-size: 20px;
}


.btn:hover {
  background-color : RoyalBlue;
}
</style>
</head>
<body>

<h2>SpeedTest Benchmark</h2>


<p>Please Download daily Average Upload speeds, Download speeds and Ping results </p>
<form:form action="submitDownload" method="post">
<button class="btn" style="width:100%"><i class="fa fa-download"></i> Download</button>
</form:form>

</body>
</html>
