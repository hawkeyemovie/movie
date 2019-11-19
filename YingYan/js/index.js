$(document).ready(function() {
	$(".search").bind("input", function() {
		
		$.getJSON("http://192.168.121.1:8080/el/elCinema?search=" + $(this).val(), function(data) {
			if(data==""){
				$(".suggest-container .suggest-body:eq(2)").hide();
			}
			var num = 0;
			$(".suggest-container .suggest-body:eq(2) .suggest-detail-list").empty();
			$(data).each(function(item) {
				
				$(".suggest-container .suggest-body:eq(2)").show();
				console.log(data[item]);
				if(num==3){return;}
				$(".suggest-container .suggest-body:eq(2) .suggest-detail-list").append("<a class='suggest-detail' href="+data[item].id+"><span class='detail-namedetail-name-small'>"+data[item].cinemaName+"</span></a>");
				num++;
			})
		});
		$.getJSON("http://192.168.121.1:8080/el/elMovies?search=" + $(this).val(), function(data) {
			if(data==""){
				$(".suggest-container .suggest-body:eq(0)").hide();
			}
			var num = 0;
			$(".suggest-container .suggest-body:eq(0) .suggest-detail-list").empty();
			$(data).each(function(item) {
				
				$(".suggest-container .suggest-body:eq(0)").show();
				console.log(data[item]);
				if(num==3){return;}
				$(".suggest-container .suggest-body:eq(0) .suggest-detail-list").append("<a class='suggest-detail' href="+data[item].id
				+" target='_blank' data-act='' data-val=''><img class='detail-img' src="+data[item].defaultImg+"><span class='detail-name'>"+data[item].movieMainTitle+"</span></a>");
				num++;
			})
		});
		$.getJSON("http://192.168.121.1:8080/el/elFilmmaker?search=" + $(this).val(), function(data) {
			if(data==""){
				$(".suggest-container .suggest-body:eq(1)").hide();
			}
			var num = 0;
			$(".suggest-container .suggest-body:eq(1) .suggest-detail-list").empty();
			$(data).each(function(item) {
				
				$(".suggest-container .suggest-body:eq(1)").show();
				console.log(data[item]);
				if(num==3){return;}
				$(".suggest-container .suggest-body:eq(1) .suggest-detail-list").append("<a class='suggest-detail' href="+data[item].id+" target='_blank' data-act='' data-val=''><img class='detail-img' src="+data[item].defaultImg+"><span class='detail-name'>"+data[item].filmmakerName+"</span></a>");
				num++;
			})
		});
	}).click(function(){
		$(".suggest-container").show();
	}).mouseout(function(){
		$(".suggest-container").hide();
	});
})