<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>123fix 值得信赖的手机维修平台</title>
    <link rel="stylesheet"  type="text/css" href="/css/fix_css/main.css" />
    <link rel="stylesheet"  type="text/css" href="/css/fix_css/animate.css" />
    <link rel="stylesheet"  type="text/css" href="/css/fix_css/animate.min.css" />
    <script type="text/javascript" src="/js/fix_js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/fix_js/toggle.js"></script>
    <script type="text/javascript" src="/js/fix_js/jquery-1.7.2.min.js" ></script>
    <script type="text/javascript" src="/js/fix_js/index.js"></script>
</head>

<body style="height:100%;background: #f6f6f6">
	
	<!-- head -->
	  <%@ include file="head/fix_head.jsp" %>
	<!-- head  -->
	
	<!-- banner start -->
	   <div class="banner"></div>
	<!-- banner end -->
	
	<!-- main1 -->
	<section id="main">
        <div style="width:100%;height:590px; background-color: #ffffff">
        <div class="wrap">

            <div class="col col-1">
      
                <dl class="i_tag animated fadeInUp">
                    <dt>值得信赖的手机维修平台</dt>
                    <dd>解决头痛的“售后”问题，体验高品质手机维修服务</dd>
                </dl>
            	<%@ include file="common/main1.jsp" %>
            
        </div>
        </div>
    	</div>
    <!-- main1 -->   

        <article class="wrap" style="padding: 0; margin: 0; height: 0">
           <div class="col col-1"></div>
        </article>
	
	<!-- main2  流程 --> 
	 <div class="pingjia  flow1">
        <div class="pingjia_box animated">
       
              <%@ include file="common/main2.jsp" %>
            </div>
    </div>
	<!-- main2 --> 
	
	<!-- foot  -->
		<%@ include file="foot/fix_foot.jsp" %>
	<!-- foot  -->
	</section>
</body>
</html>