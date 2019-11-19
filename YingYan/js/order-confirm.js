$(document).ready(function() {
	//验证是否已登录
	//	$.getJSON("http://192.168.121.1:8080/login/islogin",GetRequest(), function(data) {
	//		if(data == true){
	//			$.getJSON("http://192.168.121.1:8080/login/getuser",GetRequest(), function(data) {
	//				console.log(data);
	//			})
	//		}
	//	})
	//	
	$.getJSON("http://192.168.121.1:8080/shop/getmovietticket", GetRequest(), function(data) {
		//电影名
		$(".movie-name").html(data.chipFormation.movie.movieMainTitle);
		//时间
		$(".showtime").html(formatDate(data.chipFormation.startTime));
		//影院
		$(".cinema-name").html(data.cinema.cinemaName);
		//影厅
		$(".hall").html(data.chipFormation.cinemaHall.cinemaHallName);
		//座位
		$(".seats>div").empty(); //清空
		
		$(data.orderSeatList).each(function(term) { //绑定
			
			$(".seats>div").append("<span class=''>" + data.orderSeatList[term].seat.seatName + "</span>");
		});
		//价格
		$(".price").html(data.ticketPrice);
		//计时

		//	   var hours = parseInt((mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		//       var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));

		var timestamp = Date.parse(new Date()); //当前时间戳
		
		var mss = parseInt(data.foundTime) + 600000 - timestamp;
		
		var  seconds = (mss % (1000 * 60)) / 1000;

		var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));
		
		var second = seconds % 60;
		
		//分
		$(".minute").html(minutes);
		//秒
		$(".second").html(second);
		
		myVar = setInterval(function() {
			$(".second").text(parseInt(second));
			parseInt(second--);
			if(parseInt(second)==0) {
				if(minutes==0 && second==0){
					myStopFunction();
				}
				minutes--;
				$(".minute").html(minutes);
				second = 60;
			}
		}, 1000)

	});
});

function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if(url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

var myVar;

function myStopFunction() {
	clearInterval(myVar);
}

function formatDate(date) {
  var date = new Date(date);
  var YY = date.getFullYear() + '-';
  var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
  var DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
  var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
  var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
  var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
  return YY + MM + DD +" "+hh + mm + ss;
}